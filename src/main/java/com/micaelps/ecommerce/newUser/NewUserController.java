package com.micaelps.ecommerce.newUser;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class NewUserController {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping(path = "/users")
    public void executa(@RequestBody @Valid NewUserRequest newUserRequest){
        User model = newUserRequest.toModel();
        entityManager.persist(model);
    }
}
