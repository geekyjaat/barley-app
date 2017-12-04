package com.barley.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {
    private String code;
    private String message;

    public Status() {
    }

    @Builder
    public Status(String code, String message) {
        this.code = code;
        this.message = message;
    }
}