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
public class Passengers {
    private String kind;
    private int adultCount;
    private int childCount;
    private int infantInLapCount;
    private int infantInSeatCount;
    private int seniorCount;

    public Passengers() {
    }

    @Builder
    public Passengers(String kind, int adultCount, int childCount, int infantInLapCount, int infantInSeatCount, int seniorCount) {
        this.kind = kind;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.infantInLapCount = infantInLapCount;
        this.infantInSeatCount = infantInSeatCount;
        this.seniorCount = seniorCount;
    }

}