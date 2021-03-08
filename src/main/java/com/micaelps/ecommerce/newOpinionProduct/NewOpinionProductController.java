package com.micaelps.ecommerce.newOpinionProduct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newUserSystem.UserSystem;
import com.micaelps.ecommerce.security.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class NewOpinionProductController {


    @Autowired
    ObjectMapper objectMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping(path = "/opinions/products/{productId}")
    public ResponseEntity<?> save(@RequestBody @Valid NewOpinionProductRequest newOpinionProductRequest,
                                       @PathVariable Long productId,
                                       @AuthenticationPrincipal LoggedUser loggedUser) {

        Product product = entityManager.find(Product.class, productId);
        UserSystem userLogged = loggedUser.get();

        if(Objects.isNull(product)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        product.associateOpinion(newOpinionProductRequest, userLogged);
        entityManager.merge(product);
        return ResponseEntity.ok().build();
    }



}
