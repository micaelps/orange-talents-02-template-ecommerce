package com.micaelps.ecommerce.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micaelps.ecommerce.models.User;
import com.micaelps.ecommerce.requests.NewUserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NewUserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("Shold create new user and return status 200")
    void should_create_new_user() throws Exception {
        NewUserRequest newUserRequest = new NewUserRequest("micael@email.com","123456");

        mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(newUserRequest))).andExpect(status().isOk());

        List<User> select_a_from_user = entityManager.createQuery("from User", User.class).getResultList();
        User user = select_a_from_user.get(0);

        assertEquals(select_a_from_user.size(), 1);
        assertEquals("micael@email.com", user.getLogin());
    }

    private String toJson(NewUserRequest newUserRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(newUserRequest);
    }


}