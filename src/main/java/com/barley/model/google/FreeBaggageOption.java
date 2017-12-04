package com.barley.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FreeBaggageOption {
    private String kind;
    private List<BagDescriptor> bagDescriptor;
    private int kilos;
    private int kilosPerPiece;
    private int pieces;
    private int pounds;

    public FreeBaggageOption() {
    }

    @Builder
    public FreeBaggageOption(String kind, List<BagDescriptor> bagDescriptor, int kilos, int kilosPerPiece, int pieces, int pounds) {
        this.kind = kind;
        this.bagDescriptor = bagDescriptor;
        this.kilos = kilos;
        this.kilosPerPiece = kilosPerPiece;
        this.pieces = pieces;
        this.pounds = pounds;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<BagDescriptor> getBagDescriptor() {
        return bagDescriptor;
    }

    public void setBagDescriptor(List<BagDescriptor> bagDescriptor) {
        this.bagDescriptor = bagDescriptor;
    }

    public int getKilos() {
        return kilos;
    }

    public void setKilos(int kilos) {
        this.kilos = kilos;
    }

    public int getKilosPerPiece() {
        return kilosPerPiece;
    }

    public void setKilosPerPiece(int kilosPerPiece) {
        this.kilosPerPiece = kilosPerPiece;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public int getPounds() {
        return pounds;
    }

    public void setPounds(int pounds) {
        this.pounds = pounds;
    }
}
