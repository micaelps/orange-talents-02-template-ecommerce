package com.micaelps.ecommerce.newImageProduct;

import com.micaelps.ecommerce.newCategory.Category;
import com.micaelps.ecommerce.newProduct.AttributeRequest;
import com.micaelps.ecommerce.newProduct.NewProductRequest;
import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newUserSystem.UserSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class NewImageProductControllerTest {

    Category category;
    Product product;
    UserSystem user;

    @Autowired
    MockMvc mockMvc;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    void setup() {
        List<AttributeRequest> attributeRequests = new ArrayList<>();
        attributeRequests.add(new AttributeRequest("beleza", "muito boa"));
        attributeRequests.add(new AttributeRequest("qualidade", "muito boa"));
        attributeRequests.add(new AttributeRequest("som", "sensacional"));

        this.user = entityManager.createQuery("from UserSystem", UserSystem.class).getResultList().get(0);

        category = new Category("geral");
        entityManager.persist(category);
        this.category = entityManager.createQuery("from Category", Category.class).getSingleResult();

        product = new Product("Produto",BigDecimal.TEN, 1, attributeRequests,"eae",this.category, this.user);
        entityManager.persist(product);
        this.product = entityManager.createQuery("from Product", Product.class).getSingleResult();
    }


    @Test
    @WithUserDetails("m@email.com")
    @DisplayName("Should add images on the product, return 200")
    void add_new_image_in_product() throws Exception {

        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.png");
        final MockMultipartFile avatar = new MockMultipartFile("images", "test.png", "image/png", inputStream);

        mockMvc.perform(multipart("/images/products/"+product.getId()).file(avatar))
                .andExpect(status().isOk());

    }

    @Test
    @WithUserDetails("m2@email.com")
    @DisplayName("Shouldn't add images to another user product, return status 403")
    void add_new_images_to_another_user_product() throws Exception {

        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.png");
        final MockMultipartFile avatar = new MockMultipartFile("images", "test.png", "image/png", inputStream);

        mockMvc.perform(multipart("/images/products/"+product.getId()).file(avatar))
                .andExpect(status().is(403));

    }





}