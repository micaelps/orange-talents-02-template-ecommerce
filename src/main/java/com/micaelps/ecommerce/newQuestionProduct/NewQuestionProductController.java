package com.micaelps.ecommerce.newQuestionProduct;

import com.micaelps.ecommerce.integrationUtils.SenderMail;
import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newUserSystem.UserSystem;
import com.micaelps.ecommerce.security.LoggedUser;
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
public class NewQuestionProductController {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping(path = "/questions/products/{idProduct}")
    public ResponseEntity<?> save(@RequestBody @Valid NewQuestionProductRequest request, @PathVariable Long idProduct, @AuthenticationPrincipal LoggedUser loggedUser) {
        Product product = entityManager.find(Product.class, idProduct);
        UserSystem user = loggedUser.get();

        if(Objects.isNull(product)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product id: "+idProduct+" not found");
        }

        QuestionProduct questionProduct = request.toModel(user, product);
        entityManager.persist(questionProduct);
        SenderMail.sendDefault(user.getEmail(), product.getOwnerEmail(), request.getTitle());
        return  ResponseEntity.ok().build();
    }
}
