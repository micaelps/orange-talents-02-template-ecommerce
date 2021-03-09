package com.micaelps.ecommerce.detailsPage;

import com.micaelps.ecommerce.newProduct.AttributeProduct;

public class DetailsProductAttrubutes {

    private String name;
    private String description;

    public DetailsProductAttrubutes(AttributeProduct attribute) {

        this.name = attribute.getName();
        this.description = attribute.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
