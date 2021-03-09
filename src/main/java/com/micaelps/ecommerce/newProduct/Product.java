package com.micaelps.ecommerce.newProduct;

import com.micaelps.ecommerce.detailsPage.DetailsProductAttrubutes;
import com.micaelps.ecommerce.newCategory.Category;
import com.micaelps.ecommerce.newImageProduct.ImageProduct;
import com.micaelps.ecommerce.newOpinionProduct.NewOpinionProductRequest;
import com.micaelps.ecommerce.newOpinionProduct.OpinionProduct;
import com.micaelps.ecommerce.newQuestionProduct.NewQuestionProductRequest;
import com.micaelps.ecommerce.newQuestionProduct.QuestionProduct;
import com.micaelps.ecommerce.newUserSystem.UserSystem;
import com.micaelps.ecommerce.security.LoggedUser;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<AttributeProduct> attributes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.MERGE)
    private List<ImageProduct> images = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<QuestionProduct> questions = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<OpinionProduct> opinions = new ArrayList<>();

    @Size(max = 1000)
    private String description;
    @ManyToOne
    private Category category;
    @ManyToOne
    private UserSystem user;
    private LocalDate createdAt = LocalDate.now();


    @Deprecated
    public Product(){}

    public Product(@NotBlank String name,
                   @NotNull @Min(value = 1) BigDecimal price,
                   @NotNull @Min(value = 0) Integer amount,
                   @Size(min = 3) List<AttributeRequest> attributes,
                   @Size(max = 1000) String description,
                   Category category, UserSystem user) {

        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.category = category;
        List<AttributeProduct> attributesModels = getAttributes(attributes);
        this.attributes.addAll(attributesModels);
        this.user = user;

        Assert.isTrue(this.attributes.size() >= 3,
                "O produto só pode existir com 3 ou mais características");

    }

    private List<AttributeProduct> getAttributes(List<AttributeRequest> attributes) {
        return attributes.stream()
                .map(attributeRequest -> attributeRequest.toModel(this))
                .collect(Collectors.toList());
    }

    public boolean belongedtoUserLogged(UserSystem userLogged) {
        return this.user.getId().equals(userLogged.getId());
    }

    public void associateImagesLinks(List<String> links) {
        Stream<ImageProduct> imageProductStream = links.stream().map(link -> (new ImageProduct(link, this)));
        this.images.addAll(imageProductStream.collect(Collectors.toList()));
    }

    public Long getId() {
        return this.id;
    }

    public String getOwnerEmail() {
        return this.user.getEmail();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public <T>List<T> mapImages(Function<ImageProduct,T> functionMapper) {
        return this.images.stream().map(functionMapper).collect(Collectors.toList());
    }
    public <T>List<T> mapAttributes(Function<AttributeProduct,T> functionMapper) {
        return this.attributes.stream().map(functionMapper).collect(Collectors.toList());
    }

    public <T>List<T> mapQuestions(Function<QuestionProduct,T> functionMapper) {
        return this.questions.stream().map(functionMapper).collect(Collectors.toList());
    }

    public <T>List<T> mapOpinions(Function<OpinionProduct,T> functionMapper) {
        return this.opinions.stream().map(functionMapper).collect(Collectors.toList());
    }
}
