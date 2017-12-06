package com.barley.dao;

import com.barley.model.Query;
import com.barley.socket.AppWebSocket;
import com.barley.util.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import spark.Request;

import java.io.IOException;
import java.util.*;

public class MongoDb {

    private static final MongoDb theInstance = new MongoDb();

    public static MongoDb getInstance() {
        return theInstance;
    }

    private final MongoClientURI mongoClientUri;
    private final MongoDatabase db;
    private ObjectMapper mapper = new ObjectMapper();

    private MongoDb() {
        this.mongoClientUri = new MongoClientURI(Utilities.dbUri("MONGODB_URI"));
        MongoClient client = new MongoClient(mongoClientUri);
        this.db = client.getDatabase(mongoClientUri.getDatabase());
    }

    public List getLinks() {
        MongoCollection<Document> linksCollection = db.getCollection("links");
        List<Map> links = new ArrayList<>();
        linksCollection.find().map(d -> {
            try {
                return mapper.readValue(d.toJson(), Map.class);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return null;
            }
        }).into(links);
        return links;
    }

    public void processVisit(String host, String contextPath, String uri, String url, String ip,
                             String userAgent, Set<String> headers, Map<String, String> cookies, Request request) {
        new Thread(() -> {
            MongoCollection<Document> visits = db.getCollection("visits");
            Document document = getContact(host, contextPath, uri, url, ip, userAgent, headers, cookies, request);

            // let wait before we present visit stats
            Utilities.halt(5000);

            // find if ip accessed any page today
            Date midnight = Utilities.midnight();
            //System.out.println(midnight);
            List<Document> noOfAccess = visits.find(Filters
                    .and(Filters.eq("ip", ip),
                            Filters.gte("dateCreated", midnight)
                    )).into(new ArrayList<>());
            boolean ipAccessed = noOfAccess.size() == 0 ? false : true;

            // insert the visit
            visits.insertOne(document);

            // let us find total visits by this ip
            List ipVisits = visits.distinct("ip", String.class).into(new ArrayList<>());
            int totalVisits = ipVisits.size();

            long count = visits.count(new Document("ip", ip));

            //if (!ipAccessed)
                AppWebSocket.sendToIp(ip, "Unique visitors: " + totalVisits + ". You have visited " + count + " times.");
        }).start();
    }

    private Document getContact(String host, String contextPath, String uri, String url, String ip, String userAgent, Set<String> headers, Map<String, String> cookies, Request request) {
        Map<String, String> headerMap = new HashMap<>();
        headers.stream().forEach(h -> headerMap.put(h, request.headers(h)));
        return new Document("host", host)
                .append("contextPath", contextPath)
                .append("uri", uri)
                .append("url", url)
                .append("ip", ip)
                .append("userAgent", userAgent)
                .append("headers", headerMap)
                .append("cookies", cookies)
                .append("dateCreated", Utilities.now());
    }

    public void insertQuery(Query message) {
        new Thread(() -> {
            MongoCollection<Document> query = db.getCollection("query");
            Document document = getQuery(message);

            // let wait before we present visit stats
            Utilities.halt(2000);

            // insert the visit
            query.insertOne(document);
            String _id = query.find(document).map(d -> d.get("_id")).first().toString();

            AppWebSocket.sendToIp(message.getIp(), String.format("Query has been recorded. We usually respond within a week. To check your queries - <a href=\"/contact/%s\">/all.</a>", _id));
        }).start();
    }

    private Document getQuery(Query query) {
        return new Document("ip", query.getIp())
                .append("email", query.getEmail())
                .append("name", query.getName())
                .append("query", query.getQuery())
                .append("dateCreated", query.getDateCreated())
                .append("dateAnswered", null)
                .append("response", null)
                .append("state", query.getState().name());
    }

    public List getQueries(String ip) {
        MongoCollection<Document> query = db.getCollection("query");
        List<Query> queries = new ArrayList<>();
        query.find(Filters.eq("ip", ip)).map(d -> {
            try {
                //return mapper.readValue(d.toJson(), Query.class);
                return Query.builder()
                        .email(d.getString("email"))
                        .name(d.getString("name"))
                        .ip(d.getString("ip"))
                        .query(d.getString("query"))
                        .dateCreated(d.getDate("dateCreated"))
                        .dateAnswered(d.getDate("dateAnswered"))
                        .response(d.getString("response"))
                        .state(Query.State.valueOf(d.getString("state")))
                        .build();
            } catch (Exception ioe) {
                ioe.printStackTrace();
                return null;
            }
        }).into(queries);
        return queries;
    }
}
