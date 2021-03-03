package com.micaelps.ecommerce.newProduct;

import com.micaelps.ecommerce.newCategory.Category;
import com.micaelps.ecommerce.validators.ExistsId;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class NewProductRequest {

    @NotBlank
    private String name;
    @NotNull
    @Min(value = 1)
    private BigDecimal price;
    @NotNull
    @Min(value = 0)
    private Integer amount;
    @Size(min = 3)
    private Map<String, String> attributes;
    @Size(max = 500)
    private String description;
    @ExistsId(domainClass = Category.class)
    private Long category;
    private LocalDate createdAt = LocalDate.now();


    public NewProductRequest(@NotBlank String name, @NotNull @Min(value = 1) BigDecimal price, @NotNull @Min(value = 0) Integer amount, @Size(min = 3) Map<String, String> attributes, @Size(max = 500) String description, Long category) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.attributes = attributes;
        this.description = description;
        this.category = category;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Product toModel(EntityManager em) {
        Category categoryModel = em.find(Category.class, this.category);
        return new Product(this.name,this.price, this.amount, this.attributes, this.description, categoryModel);
    }
}
