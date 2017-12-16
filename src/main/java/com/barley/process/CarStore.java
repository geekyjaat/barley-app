package com.barley.process;

import com.barley.dao.MongoDb;
import com.barley.model.Car;
import com.barley.model.Make;
import com.barley.util.Utilities;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import spark.Request;
import spark.Response;

import java.util.*;

public class CarStore {
    public static Object get(Request request, Response response) {
        Map map = defaultProps();
        map.put("action", "Go ahead add a car you test driven.");
        return Utilities.render(map, "velocity/cars.vm");
    }

    public static Object getId(Request request, Response response) {
        Map map = defaultProps();
        List<Car> cars = (List<Car>) map.get("cars");
        Optional<Car> carOptional = cars.stream().filter(car -> car.getId().equalsIgnoreCase(request.params("id"))).findFirst();
        Car formCar = carOptional.orElse(new Car());
        map.put("formcar", formCar);
        map.put("action", "Edit the car now.");
        map.put("method", "put");
        return Utilities.render(map, "velocity/cars.vm");
    }

    public static Object post(Request request, Response response) {
        storeCar(request);
        return render("Go ahead add a car you test driven.");
    }

    public static Object put(Request request, Response response) {
        updateCar(request);
        return render("Go ahead add a car you test driven.");
    }

    public static Object delete(Request request, Response response) {
        delete(request);
        return render("Go ahead add a car you test driven.");
    }

    private static Object delete(Request request) {
        getCarCollection().deleteOne(Filters.eq("_id", request.queryParams("id")));
        return render("Go ahead add a car you test driven.");
    }

    private static Object render(String action) {
        Map map = defaultProps();
        map.put("action", action);
        return Utilities.render(defaultProps(), "velocity/cars.vm");
    }

    private static Map defaultProps() {
        Map map = new HashMap();
        List<Car> cars = getCars();
        List<Make> makes = getMakes();
        List<String> years = Arrays.asList("2019", "2018", "2017", "2016", "2015", "2014");
        map.put("title", "Cars");
        map.put("text", "Help you with car buying!!");
        map.put("method", "post");
        map.put("count", cars.size());
        map.put("cars", cars);
        map.put("makes", makes);
        map.put("years", years);
        return map;
    }

    private static void storeCar(Request request) {
        getCarCollection().insertOne(extractCar(request, false).document());
    }

    private static void updateCar(Request request) {
        getCarCollection().updateOne(
                Filters.eq("_id", request.queryParams("id")),
                extractCar(request, true).document());
    }

    private static MongoCollection<Document> getCarCollection() {
        MongoDatabase db = MongoDb.getInstance().getDb();
        return db.getCollection("cars");
    }

    private static Car extractCar(Request request, boolean isUpdate) {
        Car car = Car.builder()
                .make(request.queryParams("make"))
                .model(request.queryParams("model"))
                .year(request.queryParams("year"))
                .is7Seater(request.queryParams("is7Seater") != null ? true : false)
                .feelSpacious(request.queryParams("feelSpacious") != null ? true : false)
                .likeElectronicFeatures(request.queryParams("likeElectronicFeatures") != null ? true : false)
                .likeSeats(request.queryParams("likeSeats") != null ? true : false)
                .likeSafetyFeatures(request.queryParams("likeSafetyFeatures") != null ? true : false)
                .likeInterior(request.queryParams("likeInterior") != null ? true : false)
                .likeExterior(request.queryParams("likeExterior") != null ? true : false)
                .likeDriving(request.queryParams("likeDriving") != null ? true : false)
                .priceRange(request.queryParams("price"))
                .comments(request.queryParams("comments"))
                .ip(request.ip())
                .build();
        if (isUpdate)
            car.setDateUpdated(new Date());
        else
            car.setDateAdded(new Date());
        return car;
    }

    public static List<Car> getCars() {
        MongoCollection<Document> carsDb = getCarCollection();
        List<Car> cars = new ArrayList<>();
        carsDb.find().map(d -> Car.builder()
                .id(d.getObjectId("_id").toString())
                .make(d.getString("make"))
                .model(d.getString("model"))
                .year(d.getString("year"))
                .is7Seater(d.getBoolean("is7Seater"))
                .feelSpacious(d.getBoolean("feelSpacious"))
                .likeElectronicFeatures(d.getBoolean("likeElectronicFeatures"))
                .likeSeats(d.getBoolean("likeSeats"))
                .likeSafetyFeatures(d.getBoolean("likeSafetyFeatures"))
                .likeInterior(d.getBoolean("likeInterior"))
                .likeExterior(d.getBoolean("likeExterior"))
                .likeDriving(d.getBoolean("likeDriving"))
                .priceRange(d.getString("price"))
                .comments(d.getString("comments"))
                .dateAdded(d.getDate("dateAdded"))
                .dateUpdated(d.getDate("dateUpdated"))
                .build())
                .into(cars);
        return cars;
    }

    public static List<Make> getMakes() {
        MongoDatabase db = MongoDb.getInstance().getDb();
        MongoCollection<Document> car_makes = db.getCollection("car_makes");
        List<Make> makes = new ArrayList<>();
        car_makes.find().map(d -> Make.builder()
                .id(d.getObjectId("_id").toString())
                .make(d.getString("make"))
                .company(d.getString("company"))
                .url(d.getString("url"))
                .build())
                .into(makes);
        return makes;
    }
}
