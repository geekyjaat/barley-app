package com.barley;

import com.barley.dao.MongoDb;
import com.barley.fs.FileResource;
import com.barley.model.google.*;
import com.barley.process.Contact;
import com.barley.process.RomanNumerals;
import com.barley.socket.AppWebSocket;
import com.barley.trip.google.QpxExpress;
import com.barley.util.Utilities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

import static spark.Spark.*;

public class App {

    private static FileResource fs = new FileResource();
    private static QpxExpress qpxExpress = new QpxExpress(Utilities.getGoogleKey());
    private static Contact contact = new Contact();

    public static void main(String[] args) throws IOException {

        webSocket("/echo", AppWebSocket.class);

        staticFiles.location("/public");
        port(getPort());

        MongoDb mongoDb = MongoDb.getInstance();

        Map greeting = fs.getFile("greeting.json");
        //Map links = fs.getFile("links.json");
        Map posts = fs.getFile("posts.json");

        before("/*", (request, response) -> {
            String uri = request.uri();
            if (uri != null && !uri.equalsIgnoreCase("/echo"))
                mongoDb.processVisit(request.host(),
                        request.contextPath(),
                        request.uri(),
                        request.url(),
                        request.ip(),
                        request.userAgent(),
                        request.headers(),
                        request.cookies(),
                        request);
        });

        get("/", ((request, response) -> {
            List links = mongoDb.getLinks();
            Map<String, Object> model = new HashMap<>();
            model.put("greeting", greeting);
            model.put("links", links);
            model.put("posts", posts);
            model.put("activeNav", "Home");
            return Utilities.render(model, "index.vm");
        }));

        // contact
        path("/contact", () -> {
            path("", () -> {
                get("", contact::handleGet);
                post("", contact::handlePost);
            });
            path("/:id", () -> get("", contact::handleRetreive));
        });

        // about
        get("/about", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("activeNav", "about");
            model.put("title", "About");
            model.put("text", "Just a small website trying to learn and teach people programming at the same time.");
            model.put("google_key", Utilities.getGoogleKey());
            return Utilities.render(model, "velocity/about.vm");
        }));

        // roman
        path("/roman", () -> {
            get("", RomanNumerals::handleGet);
            post("", RomanNumerals::handlePost);
        });

        // blog
        get("/blog", ((request, response) -> {
            Map blog = new HashMap();
            blog.put("blog", "blogs/blog1.html");
            return Utilities.render(blog, "velocity/blog.vm");
        }));

        // trip
        get("/trip", ((request, response) -> {
            Map trip = new HashMap();
            trip.put("title", "Flight Search");
            trip.put("text", "Find your next cheap flight here.");
            return Utilities.render(trip, "velocity/trip.vm");
        }));

        post("/trip", ((request, response) -> {
            Itinerary itinerary = null;

            try {
                itinerary =
                        qpxExpress.findTrips(Input.builder()
                                .request(Request.builder()
                                        .passengers(Passengers.builder()
                                                .adultCount(Integer.parseInt(request.queryParams("count")))
                                                .build()
                                        )
                                        .slice(Arrays.asList(
                                                Slice.builder()
                                                        .origin(request.queryParams("from"))
                                                        .destination(request.queryParams("to"))
                                                        .date(request.queryParams("depart"))
                                                        .maxStops(2)
                                                        .maxConnectionDuration(0)
                                                        .build()
                                        ))
                                        .refundable(false)
                                        .solutions(2)
                                        .build()
                                )
                                .build()
                        );
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(itinerary);

            Map trip = new HashMap();
            trip.put("title", "Flight Search");
            trip.put("text", "Find your next cheap flight here.");
            trip.put("itinerary", itinerary);
            return Utilities.render(trip, "velocity/trip.vm");
        }));

        exception(URISyntaxException.class, ((exception, request, response) -> {
            response.status(500);
            response.body(exception.getLocalizedMessage());
        }));

        exception(SQLException.class, ((exception, request, response) -> {
            response.status(500);
            response.body(exception.getLocalizedMessage());
        }));

        Thread sender = new Thread(() -> {
            while (true) {
                AppWebSocket.sendToAll("New blog posted - <a href=\"/newblog\">/newblog.</a> " + new Date());
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException ie) {

                }
            }
        });

        sender.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            AppWebSocket.sendToAll("Server is being stopped.");
            stop();
            System.out.println("STOPPED");
            sender.stop();
        }));

    }

    static int getPort() {
        String port = System.getenv("PORT");
        if (port != null && port.length() != 0) {
            return Integer.parseInt(port);
        } else {
            return 5000;
        }
    }
}