package com.barley.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private String kind;
    private List<Airport> airport;
    private List<City> city;
    private List<Carrier> carrier;

    public Data() {
    }

    @Builder
    public Data(String kind, List<Airport> airport, List<City> city, List<Carrier> carrier) {
        this.kind = kind;
        this.airport = airport;
        this.city = city;
        this.carrier = carrier;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<Airport> getAirport() {
        return airport;
    }

    public void setAirport(List<Airport> airport) {
        this.airport = airport;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    public List<Carrier> getCarrier() {
        return carrier;
    }

    public void setCarrier(List<Carrier> carrier) {
        this.carrier = carrier;
    }
}
