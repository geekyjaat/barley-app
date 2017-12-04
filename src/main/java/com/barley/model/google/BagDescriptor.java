package com.barley.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BagDescriptor {
    private String kind;
    private String commercialName;
    private int count;
    private List<String> description;
    private String subcode;

    public BagDescriptor() {
    }

    @Builder
    public BagDescriptor(String kind, String commercialName, int count, List<String> description, String subcode) {
        this.kind = kind;
        this.commercialName = commercialName;
        this.count = count;
        this.description = description;
        this.subcode = subcode;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }
}
