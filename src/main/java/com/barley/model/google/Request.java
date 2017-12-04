package com.barley.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Request {
    private Passengers passengers;
    private List<Slice> slice;
    private String maxPrice;
    private String saleCountry;
    private String ticketingCountry;
    private boolean refundable;
    private int solutions;

    public Request() {
    }

    @Builder
    public Request(Passengers passengers, List<Slice> slice, String maxPrice, String saleCountry, String ticketingCountry, boolean refundable, int solutions) {
        this.passengers = passengers;
        this.slice = slice;
        this.maxPrice = maxPrice;
        this.saleCountry = saleCountry;
        this.ticketingCountry = ticketingCountry;
        this.refundable = refundable;
        this.solutions = solutions;
    }
}