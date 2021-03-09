package com.micaelps.ecommerce.detailsPage;


import com.micaelps.ecommerce.newProduct.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetailsPageController {


    @PersistenceContext
    EntityManager manager;

    @GetMapping(path = "/products/detais/{idProduct}")
    public DetailsProductResponse executa(@PathVariable Long idProduct) {

        Product product = manager.find(Product.class, idProduct);

        DetailsProductResponse detailsProductResponse = new DetailsProductResponse(product);
        return detailsProductResponse;

    }
}
