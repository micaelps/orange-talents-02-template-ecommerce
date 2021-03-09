package com.micaelps.ecommerce.detailsPage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micaelps.ecommerce.newImageProduct.ImageProduct;
import com.micaelps.ecommerce.newOpinionProduct.OpinionProduct;
import com.micaelps.ecommerce.newProduct.Product;
import com.micaelps.ecommerce.newQuestionProduct.QuestionProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class DetailsProductResponse {

    @JsonProperty
    private  String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private  BigDecimal price;
    @JsonProperty
    private List<String> links;
    @JsonProperty
    private  List<DetailsProductAttrubutes> attributes;
    @JsonProperty
    private List<String>  questions;
    @JsonProperty
    private Double averageNotes;
    @JsonProperty
    private Integer qtdOpinions;

    @JsonProperty
    private List<Map<String,String>> opinions;



    @JsonCreator
    public DetailsProductResponse(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.attributes = product.mapAttributes(DetailsProductAttrubutes::new);
        this.links = product.mapImages(ImageProduct::getPath);
        this.questions = product.mapQuestions(QuestionProduct::getTitle);
        this.description = product.getDescription();
        this.opinions = product.mapOpinions(op ->Map.of("title",op.getTitle(), "description",op.getDescription(),"NPS",Integer.toString(op.getNps())));
        this.averageNotes = average(product);
        this.qtdOpinions =  countOpinions(product);
    }

    private Integer countOpinions(Product product) {
        return product.mapOpinions(OpinionProduct::getNps).size();

    }

    private Double average(Product product) {
        List<Integer> notes = product.mapOpinions(OpinionProduct::getNps);
        IntStream mapToInt = notes.stream().mapToInt(nota -> nota);
        OptionalDouble average = mapToInt.average();
        if (average.isPresent()) {
            return average.getAsDouble();
        } else {
            return 0.0;
        }
    }
}
