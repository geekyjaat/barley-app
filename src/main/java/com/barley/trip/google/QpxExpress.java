package com.barley.trip.google;

import com.barley.model.google.Input;
import com.barley.model.google.Itinerary;
import com.barley.util.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QpxExpress {

    private static final ObjectMapper mapper = new ObjectMapper();
    public final String URL;

    private String key;

    public QpxExpress(String key) {
        this.key = key;
        this.URL = "https://googleapis.com/qpxExpress/v1/trips/search?key=" + key;
    }

    public Itinerary findTrips(Input input) throws Exception {
        return mapper.readValue(Utilities.post(URL, mapper.writeValueAsString(input)), Itinerary.class);
    }
}