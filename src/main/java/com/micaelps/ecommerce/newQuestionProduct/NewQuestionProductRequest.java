package com.micaelps.ecommerce.newQuestionProduct;

import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newUserSystem.UserSystem;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class NewQuestionProductRequest {

    @NotBlank
    private String title;

    public QuestionProduct toModel(UserSystem user, Product product) {
        return new QuestionProduct(this.title, user, product);
    }

    public String getTitle() {
        return title;
    }
}
