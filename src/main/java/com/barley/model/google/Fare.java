package com.barley.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fare {
    private String kind;
    private String id;
    private String carrier;
    private String origin;
    private String destination;
    private String basisCode;
    @JsonProperty(value = "private")
    private String _private;

    public Fare() {
    }

    @Builder
    public Fare(String kind, String id, String carrier, String origin, String destination, String basisCode, String _private) {
        this.kind = kind;
        this.id = id;
        this.carrier = carrier;
        this.origin = origin;
        this.destination = destination;
        this.basisCode = basisCode;
        this._private = _private;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBasisCode() {
        return basisCode;
    }

    public void setBasisCode(String basisCode) {
        this.basisCode = basisCode;
    }

    public String get_private() {
        return _private;
    }

    public void set_private(String _private) {
        this._private = _private;
    }
}
