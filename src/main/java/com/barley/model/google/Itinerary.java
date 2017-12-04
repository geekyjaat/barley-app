package com.barley.model.google;

import com.barley.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Itinerary {
    private Status _status;
    private String kind;
    private Trips trips;

    public Itinerary() {
    }

    @Builder
    public Itinerary(Status _status, String kind, Trips trips) {
        this._status = _status;
        this.kind = kind;
        this.trips = trips;
    }

}
