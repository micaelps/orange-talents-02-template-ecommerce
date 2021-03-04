package com.micaelps.ecommerce.newCategory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    @OneToOne
    private Category category;


    public Category(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Deprecated
    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }
}
