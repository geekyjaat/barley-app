package com.barley.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Query {
    private String ip;
    private String email;
    private String name;
    private String query;
    private Date dateCreated;
    private Date dateAnswered;
    private String response;
    private State state;

    public enum State {
        NEW,
        ANSWERED;
    }
}
