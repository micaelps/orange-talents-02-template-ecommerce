package com.micaelps.ecommerce.newProduct;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class NewProductController {


    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping(path = "/products")
    public String save(@RequestBody @Valid Object obj) {
//        Model model = obj.toModel();
//        entityManager.persistence(model);
        return obj.toString();
    }
}
