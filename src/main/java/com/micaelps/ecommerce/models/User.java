package com.micaelps.ecommerce.models;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Email
    @NotBlank
    private String login;
    @NotBlank
    @Size(min = 6)
    private String password;
    @NotNull
    @PastOrPresent
    private LocalDate createdAt = LocalDate.now();

    
    public User(@Email @NotBlank String login, @NotBlank @Size(min = 6) String password) {
        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public User() {
    }

    public String getLogin() {
        return this.login;
    }
}
