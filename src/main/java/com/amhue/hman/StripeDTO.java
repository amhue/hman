package com.amhue.hman;

public class StripeDTO {
    // private final Long amt;
    // private final String email;
    // private final String src;
    // private final String currency;
    // private final String desc;

    // public Map<String, Object> getCharge() {
    //     Map<String, Object> params = new HashMap<>();
    //     params.put("amount", this.amt);
    //     params.put("currency", this.currency);
    //     params.put("source", this.src);
    //     params.put("description", this.desc);
    //     params.put("receipt_email", this.email);
    //     return params;
    // }

    // public StripeDTO(Long amt, String email, String src, String currency,
    //                  String desc) {
    //     this.amt = amt;
    //     this.email = email;
    //     this.src = src;
    //     this.currency = currency;
    //     this.desc = desc;
    // }

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
