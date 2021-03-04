package com.micaelps.ecommerce.newCategory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micaelps.ecommerce.newCategory.Category;
import com.micaelps.ecommerce.newCategory.NewCategoryRequest;
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
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class NewCategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @WithUserDetails("m@email.com")
    @DisplayName("Should create new category without superior category")
    void create_new_category_without_superior_category() throws Exception {
        NewCategoryRequest newUserRequest = new NewCategoryRequest("drama",null);

        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newUserRequest)))
                .andExpect(status().isOk());

        List<Category> categories = entityManager.createQuery("from Category", Category.class).getResultList();
        Category category = categories.get(0);

        assertEquals(categories.size(), 1);
        assertEquals("drama", category.getName());
    }


    @Test
    @WithUserDetails("m@email.com")
    @DisplayName("Should create new category with all arguments")
    void create_new_category_all_arguments() throws Exception {
        entityManager.persist(new NewCategoryRequest("Nacional").toModel());
        NewCategoryRequest newUserRequest = new NewCategoryRequest("drama",1l);

        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newUserRequest))).andExpect(status().isOk());

        List<Category> categories = entityManager.createQuery("from Category", Category.class).getResultList();
        assertEquals(categories.size(), 2);

    }

    @Test
    @WithUserDetails("m@email.com")
    @DisplayName("Shouldn't created category with invalid superior category")
    void create_new_category_with_superior_category_invalid() throws Exception {
        NewCategoryRequest newUserRequest = new NewCategoryRequest("drama",2l);

        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newUserRequest))).andExpect(status().isBadRequest());

        List<Category> categories = entityManager.createQuery("from Category", Category.class).getResultList();
        assertEquals(categories.size(), 0);
    }


    private String toJson(NewCategoryRequest newUserRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(newUserRequest);
    }

}