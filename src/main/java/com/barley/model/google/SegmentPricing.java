package com.barley.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SegmentPricing {
    private String kind;
    private String fareId;
    private String segmentId;
    private List<FreeBaggageOption> freeBaggageOption;

    public SegmentPricing() {
    }

    @Builder
    public SegmentPricing(String kind, String fareId, String segmentId, List<FreeBaggageOption> freeBaggageOption) {
        this.kind = kind;
        this.fareId = fareId;
        this.segmentId = segmentId;
        this.freeBaggageOption = freeBaggageOption;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getFareId() {
        return fareId;
    }

    public void setFareId(String fareId) {
        this.fareId = fareId;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public List<FreeBaggageOption> getFreeBaggageOption() {
        return freeBaggageOption;
    }

    public void setFreeBaggageOption(List<FreeBaggageOption> freeBaggageOption) {
        this.freeBaggageOption = freeBaggageOption;
    }
}
