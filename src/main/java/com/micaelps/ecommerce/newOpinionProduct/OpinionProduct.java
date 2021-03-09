package com.micaelps.ecommerce.newOpinionProduct;


import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newUserSystem.UserSystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class OpinionProduct {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Max(5) @Min(1)
    private Integer nps;
    @NotBlank
    private String title;
    @Size(max = 500)
    @NotBlank
    private String description;
    @ManyToOne
    private UserSystem userLogged;
    @ManyToOne
    private Product product;

    public OpinionProduct(@Max(5) @Min(1) Integer nps,
                          @NotBlank String title,
                          @Size(max = 500) @NotBlank String description,
                          UserSystem userLogged,
                          Product product) {
        this.nps = nps;
        this.title = title;
        this.description = description;
        this.userLogged = userLogged;
        this.product = product;
    }

    @Deprecated
    public OpinionProduct() {
    }

    public Integer getNps() {
        return nps;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
