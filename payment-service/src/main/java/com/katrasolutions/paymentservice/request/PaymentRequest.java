package com.katrasolutions.paymentservice.request;

import javax.validation.constraints.NotNull;

public class PaymentRequest {

    @NotNull(message = "amount must not be null")
    private Double amount;

    private String key;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
