package com.micaelps.ecommerce.requests;


import com.micaelps.ecommerce.models.User;
import com.micaelps.ecommerce.validators.UniqueValue;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewUserRequest {

    @Email
    @NotBlank
    @UniqueValue(fieldName = "login", domainClass = User.class)
    private String login;
    @NotBlank
    @Size(min = 6)
    private String password;


    public NewUserRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User toModel() {
        String encodedPassword = new BCryptPasswordEncoder().encode(this.password);
        return new User(this.login, encodedPassword);
    }
}
