package com.micaelps.ecommerce.newUserSystem;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.micaelps.ecommerce.validators.UniqueValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewUserSystemRequest {

    @Email
    @NotBlank
    @UniqueValue(fieldName = "login", domainClass = UserSystem.class)
    @JsonProperty
    private String login;
    @NotBlank
    @Size(min = 6)
    @JsonProperty
    private String password;


    public NewUserSystemRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserSystem toModel() {
        String encodedPassword = new BCryptPasswordEncoder().encode(this.password);
        return new UserSystem(this.login, encodedPassword);
    }


}
