package com.micaelps.ecommerce.newProduct;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AttributeRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String description;

    public AttributeRequest(@NotBlank String name, @NotBlank String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AttributeProduct toModel(@NotNull @Valid Product product){
        return new AttributeProduct(this.name, this.description, product);


    }

    @Override
    public String toString() {
        return "AttributeRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
