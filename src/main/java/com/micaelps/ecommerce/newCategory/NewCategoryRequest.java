package com.micaelps.ecommerce.newCategory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micaelps.ecommerce.validators.ExistsId;

import javax.persistence.EntityManager;
import java.util.Optional;

public class NewCategoryRequest {

    @JsonProperty
    private String name;
    @JsonProperty
    @ExistsId(domainClass = Category.class)
    private Long superiorCategory;


    @JsonCreator
    public NewCategoryRequest(String name, Long superiorCategory) {
        this.name = name;
        this.superiorCategory = superiorCategory;
    }

    public NewCategoryRequest(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NewCategoryRequest{" +
                "name='" + name + '\'' +
                ", superiorCategory=" + superiorCategory +
                '}';
    }

    public Category toModel(EntityManager repository) {
        Category categorySearch = repository.find(Category.class, this.superiorCategory);
        return new Category(this.name, categorySearch);

    }

    public Category toModel() {
        return new Category(this.name);

    }

    public Boolean hasSuperiorCategory(){
        return this.superiorCategory != null;
    }




}
