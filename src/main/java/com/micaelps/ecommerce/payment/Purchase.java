package com.micaelps.ecommerce.payment;

import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newUserSystem.UserSystem;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Purchase {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne
    @Valid
    @NotNull
    private Product productPresent;
    @ManyToOne
    @Valid
    @NotNull
    private UserSystem buyer;
    @Positive
    @NotNull
    private int quantity;
    @Enumerated
    @NotNull
    private GatewayPayment gatewayPayment;

    public Purchase(Product productPresent, UserSystem buyer, int quantity, GatewayPayment gatwayPayment) {
        this.productPresent = productPresent;
        this.buyer = buyer;
        this.quantity = quantity;
        this.gatewayPayment = gatwayPayment;
    }

    public Long getId() {
        return id;
    }
}
