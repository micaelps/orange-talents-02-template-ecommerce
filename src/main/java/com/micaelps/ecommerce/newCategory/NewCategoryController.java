package com.micaelps.ecommerce.newCategory;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class NewCategoryController {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @PostMapping(path = "/categories")
    public void save(@RequestBody @Valid NewCategoryRequest request) {
        Category category = request.hasSuperiorCategory() ? request.toModel(em) : request.toModel();
        em.persist(category);
    }
}
