package com.micaelps.ecommerce.newOpinionProduct;

import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newUserSystem.UserSystem;

import javax.validation.constraints.*;

public class NewOpinionProductRequest {

    @Max(5)
    @Min(1)
    private int nps;
    @NotBlank
    private String title;
    @Size(max = 500)
    @NotBlank
    private String description;

    public NewOpinionProductRequest(@Size(min = 1, max = 5) Integer nps, @NotBlank String title, @Size(max = 500) @NotBlank String description) {
        this.nps = nps;
        this.title = title;
        this.description = description;
    }

    public int getNps() {
        return nps;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public OpinionProduct toModel(UserSystem userLogged, Product product) {
        return new OpinionProduct(this.nps, this.title, this.description, userLogged, product);
    }
}
