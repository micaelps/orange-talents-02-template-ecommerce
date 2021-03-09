package com.micaelps.ecommerce.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPaymentResponse {

    @JsonProperty
    private Long idPurchase;
    @JsonProperty
    private String domain;

    @JsonCreator
    public NewPaymentResponse(String domain, Long idPurchase) {
        this.idPurchase = idPurchase;
        this.domain = domain;
    }
}
