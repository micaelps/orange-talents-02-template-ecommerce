package com.micaelps.ecommerce.payment;

public class NewPaymentInitialRequest {

    private int quantity;
    private Long productId;
    private GatewayPayment gatewayPayment;

    public NewPaymentInitialRequest(int quantity, Long productId, GatewayPayment gatewayPayment) {
        this.quantity = quantity;
        this.productId = productId;
        this.gatewayPayment = gatewayPayment;
    }


    @Override
    public String toString() {
        return "NewPaymentInitialRequest{" +
                "quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }


    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public GatewayPayment getGatewayPayment() {
        return gatewayPayment;
    }
}
