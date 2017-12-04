package com.barley.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trips {
    private String kind;
    private String requestId;
    private Data data;
    private List<TripOption> tripOption;

    public Trips() {
    }

    @Builder
    public Trips(String kind, String requestId, Data data, List<TripOption> tripOption) {
        this.kind = kind;
        this.requestId = requestId;
        this.data = data;
        this.tripOption = tripOption;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<TripOption> getTripOption() {
        return tripOption;
    }

    public void setTripOption(List<TripOption> tripOption) {
        this.tripOption = tripOption;
    }
}
