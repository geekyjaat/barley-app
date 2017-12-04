package com.barley.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Slice {
    private String kind;
    private int duration;
    private List<Segment> segment;
    private String origin;
    private String destination;
    private String date;
    private int maxStops;
    private int maxConnectionDuration;
    private String preferredCabin;
    private PermittedDepartureTime permittedDepartureTime;
    private List<String> permittedCarrier;
    private String alliance;
    private List<String> prohibitedCarrier;

    public Slice() {
    }

    @Builder
    public Slice(String kind, int duration, List<Segment> segment, String origin, String destination, String date, int maxStops, int maxConnectionDuration, String preferredCabin, PermittedDepartureTime permittedDepartureTime, List<String> permittedCarrier, String alliance, List<String> prohibitedCarrier) {
        this.kind = kind;
        this.duration = duration;
        this.segment = segment;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.maxStops = maxStops;
        this.maxConnectionDuration = maxConnectionDuration;
        this.preferredCabin = preferredCabin;
        this.permittedDepartureTime = permittedDepartureTime;
        this.permittedCarrier = permittedCarrier;
        this.alliance = alliance;
        this.prohibitedCarrier = prohibitedCarrier;
    }
}