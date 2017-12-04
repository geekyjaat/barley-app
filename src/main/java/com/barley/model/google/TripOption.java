package com.barley.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripOption {
    private String kind;
    private String saleTotal;
    private String id;
    private List<Slice> slice;
    private List<Pricing> pricing;

    public TripOption() {
    }

    @Builder
    public TripOption(String kind, String saleTotal, String id, List<Slice> slice, List<Pricing> pricing) {
        this.kind = kind;
        this.saleTotal = saleTotal;
        this.id = id;
        this.slice = slice;
        this.pricing = pricing;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(String saleTotal) {
        this.saleTotal = saleTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Slice> getSlice() {
        return slice;
    }

    public void setSlice(List<Slice> slice) {
        this.slice = slice;
    }

    public List<Pricing> getPricing() {
        return pricing;
    }

    public void setPricing(List<Pricing> pricing) {
        this.pricing = pricing;
    }
}
