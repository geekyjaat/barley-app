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
public class Segment {
    private String kind;
    private int duration;
    private Flight flight;
    private String id;
    private String cabin;
    private String bookingCode;
    private int bookingCodeCount;
    private String marriedSegmentGroup;
    private boolean subjectToGovernmentApproval;
    private List<Leg> leg;
    private int connectionDuration;

    public Segment() {
    }

    @Builder
    public Segment(String kind, int duration, Flight flight, String id, String cabin, String bookingCode, int bookingCodeCount, String marriedSegmentGroup, boolean subjectToGovernmentApproval, List<Leg> leg, int connectionDuration) {
        this.kind = kind;
        this.duration = duration;
        this.flight = flight;
        this.id = id;
        this.cabin = cabin;
        this.bookingCode = bookingCode;
        this.bookingCodeCount = bookingCodeCount;
        this.marriedSegmentGroup = marriedSegmentGroup;
        this.subjectToGovernmentApproval = subjectToGovernmentApproval;
        this.leg = leg;
        this.connectionDuration = connectionDuration;
    }
}