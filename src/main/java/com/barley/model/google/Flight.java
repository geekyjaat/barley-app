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
public class Flight {
    private String carrier;
    private String number;

    public Flight() {
    }

    @Builder
    public Flight(String carrier, String number) {
        this.carrier = carrier;
        this.number = number;
    }

}