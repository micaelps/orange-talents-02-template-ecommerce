package com.micaelps.ecommerce.newProduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micaelps.ecommerce.newCategory.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NewProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @PersistenceContext
    EntityManager entityManager;


    @Test
    @WithUserDetails("m@email.com")
    @DisplayName("Should create new product and return status 200")
    void create_new_product() throws Exception {
        List<Category> categories = entityManager.createQuery("from Category", Category.class).getResultList();
        List<AttributeRequest> attributeRequests = new ArrayList<>();
        attributeRequests.add(new AttributeRequest("beleza", "muito boa"));
        attributeRequests.add(new AttributeRequest("qualidade", "muito boa"));
        attributeRequests.add(new AttributeRequest("som", "sensacional"));

        NewProductRequest newProductRequest = new NewProductRequest("violao", BigDecimal.TEN,1, attributeRequests, "muito bom", 1l);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newProductRequest)))
                .andExpect(status().isOk());

        List<Product> products = entityManager.createQuery("from Product", Product.class).getResultList();
        assertEquals(products.size(), 1);
    }

    @Test
    @WithUserDetails("m@email.com")
    @DisplayName("Shouldn't create new product with attributes < 3, return status 400")
    void create_new_product_invalid() throws Exception {
        List<AttributeRequest> attributeRequests = new ArrayList<>();
        attributeRequests.add(new AttributeRequest("beleza", "muito boa"));

        NewProductRequest newProductRequest = new NewProductRequest("violao", BigDecimal.TEN,1, attributeRequests, "muito bom", 1l);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newProductRequest)))
                .andExpect(status().isBadRequest());
    }

    private String toJson(NewProductRequest newUserRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(newUserRequest);
    }
}