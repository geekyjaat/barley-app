package com.barley.model;

import lombok.*;
import org.bson.Document;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String id;
    private String make;
    private String model;
    private String year;
    private boolean is7Seater;
    private boolean feelSpacious;
    private boolean likeElectronicFeatures;
    private boolean likeSeats;
    private boolean likeSafetyFeatures;
    private boolean likeInterior;
    private boolean likeExterior;
    private boolean likeDriving;
    private String priceRange;
    private String comments;
    private Date dateAdded;
    private Date dateUpdated;
    private String ip;

    public Document document() {
        return new Document("make", make)
                .append("model", model)
                .append("year", year)
                .append("is7Seater", is7Seater)
                .append("feelSpacious", feelSpacious)
                .append("likeElectronicFeatures", likeElectronicFeatures)
                .append("likeSeats", likeSeats)
                .append("likeSafetyFeatures", likeSafetyFeatures)
                .append("likeInterior", likeInterior)
                .append("likeExterior", likeExterior)
                .append("likeDriving", likeDriving)
                .append("priceRange", priceRange)
                .append("comments", comments)
                .append("dateAdded", dateAdded)
                .append("dateUpdated", dateUpdated)
                .append("ip", ip);
    }
}