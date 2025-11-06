package com.amhue.hman.DTOs;

public class StripeDTO {
    private Long amount;
    private String currency;
    private String paymentMethodID;

    public StripeDTO(Long amount, String currency, String paymentMethodID) {
        this.amount = amount;
        this.currency = currency;
        this.paymentMethodID = paymentMethodID;
    }

    public Long getAmount() { return amount; }

    public void setAmount(Long amount) { this.amount = amount; }

    public String getCurrency() { return currency; }

    public void setCurrency(String currency) { this.currency = currency; }

    public String getPaymentMethodID() { return paymentMethodID; }

    public void setPaymentMethodID(String paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }
}
