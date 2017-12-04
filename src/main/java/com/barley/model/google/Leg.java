package com.barley.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Leg {
    private String kind;
    private String id;
    private String aircraft;
    private String arrivalTime;
    private String departureTime;
    private String origin;
    private String destination;
    private String originTerminal;
    private String destinationTerminal;
    private int duration;
    private String operatingDisclosure;
    private int onTimePerformance;
    private int mileage;
    private String meal;
    private boolean secure;
    private int connectionDuration;
    private boolean changePlane;

    public Leg() {
    }

    @Builder
    public Leg(String kind, String id, String aircraft, String arrivalTime, String departureTime, String origin, String destination, String originTerminal, String destinationTerminal, int duration, String operatingDisclosure, int onTimePerformance, int mileage, String meal, boolean secure, int connectionDuration, boolean changePlane) {
        this.kind = kind;
        this.id = id;
        this.aircraft = aircraft;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.origin = origin;
        this.destination = destination;
        this.originTerminal = originTerminal;
        this.destinationTerminal = destinationTerminal;
        this.duration = duration;
        this.operatingDisclosure = operatingDisclosure;
        this.onTimePerformance = onTimePerformance;
        this.mileage = mileage;
        this.meal = meal;
        this.secure = secure;
        this.connectionDuration = connectionDuration;
        this.changePlane = changePlane;
    }
}