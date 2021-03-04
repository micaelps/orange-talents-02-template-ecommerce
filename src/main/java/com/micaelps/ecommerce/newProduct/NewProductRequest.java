package com.micaelps.ecommerce.newProduct;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micaelps.ecommerce.newCategory.Category;
import com.micaelps.ecommerce.newUserSystem.UserSystem;
import com.micaelps.ecommerce.validators.ExistsId;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NewProductRequest {

    @NotBlank
    @JsonProperty
    private String name;

    @NotNull
    @Min(value = 1)
    @JsonProperty
    private BigDecimal price;

    @NotNull
    @Min(value = 0)
    @JsonProperty
    private Integer amount;

    @Size(min = 3)
    @Valid
    @JsonProperty
    List<AttributeRequest> attributes = new ArrayList<>();

    @Size(max = 500)
    @JsonProperty
    private String description;

    @ExistsId(domainClass = Category.class)
    @JsonProperty
    private Long category;

    @JsonCreator
    public NewProductRequest(@NotBlank String name, @NotNull @Min(value = 1) BigDecimal price, @NotNull @Min(value = 0) Integer amount, @Size(min = 3) List<AttributeRequest> attributes, @Size(max = 500) String description, Long category) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.attributes.addAll(attributes);
        this.description = description;
        this.category = category;
    }

    public List<AttributeRequest> getAttributes() {
        return attributes;
    }


    @Override
    public String toString() {
        return "NewProductRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", attributes=" + attributes +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }

    public Product toModel(EntityManager entityManager, UserSystem user) {
        Category categoryModel = entityManager.find(Category.class, category);
        return new Product(name, price, amount, attributes, description, categoryModel, user);
    }
}
