package com.micaelps.ecommerce.newQuestionProduct;

import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newUserSystem.UserSystem;
import com.micaelps.ecommerce.security.LoggedUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class QuestionProduct {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne
    private UserSystem user;
    @ManyToOne
    private Product product;


    public QuestionProduct() {
    }


    public QuestionProduct(String title, UserSystem user, Product product) {
        this.title = title;
        this.user = user;
        this.product = product;
    }

}
