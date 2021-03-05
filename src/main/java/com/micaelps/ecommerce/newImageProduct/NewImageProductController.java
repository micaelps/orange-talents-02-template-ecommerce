package com.micaelps.ecommerce.newImageProduct;


import com.micaelps.ecommerce.integrationUtils.Uploader;
import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newUserSystem.UserSystem;
import com.micaelps.ecommerce.security.LoggedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@RestController
public class NewImageProductController {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping(path = "/new-image/{productId}")
    public ResponseEntity<?> executa(@Valid NewImageProductRequest request, @PathVariable("productId") Long id, @AuthenticationPrincipal LoggedUser loggedUser) {

        Product product = entityManager.find(Product.class, id);
        UserSystem userLogged = loggedUser.get();

        if(!product.belongedtoUserLogged(userLogged)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        List<String> links = Uploader.DefaultUploadImage(request.getImages());
        product.associaImagesLinks(links);
        entityManager.merge(product);
        return ResponseEntity.ok().build();
    }
}
