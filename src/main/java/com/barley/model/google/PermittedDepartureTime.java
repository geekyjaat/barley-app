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
public class PermittedDepartureTime {
    private String kind;
    private String earliestTime;
    private String latestTime;

    public PermittedDepartureTime() {
    }

    @Builder
    public PermittedDepartureTime(String kind, String earliestTime, String latestTime) {
        this.kind = kind;
        this.earliestTime = earliestTime;
        this.latestTime = latestTime;
    }
}