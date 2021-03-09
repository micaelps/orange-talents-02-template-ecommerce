package com.micaelps.ecommerce.payment;


import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newUserSystem.UserSystem;
import com.micaelps.ecommerce.security.LoggedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class InitialPaymentController {


    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping(path = "/payment")
    public String initPay(@RequestBody @Valid NewPaymentInitialRequest request,
                          @AuthenticationPrincipal LoggedUser loggedUser,
                          UriComponentsBuilder uriBuilder) throws BindException {

        Product productPresent = entityManager.find(Product.class, request.getProductId());
        boolean decrease = productPresent.decreaseInvetory(request.getQuantity());
        UserSystem buyer = loggedUser.get();
        GatewayPayment gateway = request.getGatewayPayment();

        if(decrease){
            Purchase purchase = new Purchase(productPresent,
                    buyer,
                    request.getQuantity(),
                    gateway);
            entityManager.persist(purchase);

            return gateway.urlReturn(purchase, uriBuilder);
        }

        BindException errorInventory = new BindException(request, "NewPaymentInitialRequest");
        errorInventory.reject(null, "Erro in inventory");
        throw errorInventory;
    }
}
