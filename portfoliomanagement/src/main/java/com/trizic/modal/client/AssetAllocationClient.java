package com.trizic.modal.client;


import javax.validation.constraints.NotNull;

public class AssetAllocationClient {

    @NotNull
    private String symbol;
    @NotNull
    private Integer percentage;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
