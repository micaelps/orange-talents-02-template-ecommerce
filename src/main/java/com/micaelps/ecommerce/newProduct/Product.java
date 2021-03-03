package com.micaelps.ecommerce.newProduct;

import com.micaelps.ecommerce.newCategory.Category;
import com.micaelps.ecommerce.validators.ExistsId;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Min(value = 1)
    private BigDecimal price;
    @NotNull
    @Min(value = 0)
    private Integer amount;
    @Size(min = 3)
    @ElementCollection
    @MapKeyColumn(name="attribute_name")
    @Column(name="attribute_description")
    private Map<String, String> attributes;
    @Size(max = 1000)
    private String description;
    @ManyToOne
    private Category category;
    private LocalDate createdAt = LocalDate.now();


    public Product(@NotBlank String name, @NotNull @Min(value = 1) BigDecimal price, @NotNull @Min(value = 0) Integer amount, @Size(min = 3) Map<String, String> attributes, @Size(max = 500) String description, Category category) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.attributes = attributes;
        this.description = description;
        this.category = category;
    }
}
