package com.micaelps.ecommerce.newProduct;

import com.micaelps.ecommerce.newUserSystem.UserSystem;
import com.micaelps.ecommerce.security.LoggedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String save(@RequestBody @Valid NewProductRequest request, @AuthenticationPrincipal LoggedUser loggedUser) {
        UserSystem user = loggedUser.get();
        Product model = request.toModel(entityManager, user);
        entityManager.persist(model);
        return request.toString();

    }
}
