/*
package com.barley;

import com.barley.dao.MongoDb;
import com.barley.fs.FileResource;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileResource fs = new FileResource();
        String str = fs.getStringFromFile("make.txt");
        String[] strings = str.split(",");
        MongoDatabase db = MongoDb.getInstance().getDb();
        MongoCollection<Document> makes = db.getCollection("car_makes");
        List<Document> docs = new ArrayList<>();
        for (String string : strings) {
            docs.add(new Document("make", string)
                    .append("company", string)
                    .append("url", "https://www." + string + ".com"));
        }
        makes.insertMany(docs);
    }
}
*/
