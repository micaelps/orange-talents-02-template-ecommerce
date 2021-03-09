package com.micaelps.ecommerce.newProduct;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class AttributeProduct {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @ManyToOne
    private Product product;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AttributeProduct(@NotBlank String name, @NotBlank String description, @NotNull @Valid Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public AttributeProduct() {
    }
}
