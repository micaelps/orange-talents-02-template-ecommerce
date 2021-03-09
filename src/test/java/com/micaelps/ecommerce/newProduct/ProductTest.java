package com.micaelps.ecommerce.newProduct;

import com.micaelps.ecommerce.newCategory.Category;
import com.micaelps.ecommerce.newUserSystem.UserSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class ProductTest {

    @DisplayName("Should create new product with attributes > 3")
    @ParameterizedTest
    @MethodSource("argumentsStreamOK")
    void create_product_with_all_attributes(List<AttributeRequest> attributes){

        Category category = new Category("Geral");
        UserSystem userSystem = new UserSystem("m@email.com","12345");
        new Product("carro", BigDecimal.valueOf(180000),1,attributes,"descricao",category,userSystem );
    }

    static Stream<Arguments> argumentsStreamOK(){
        return Stream.of(
            Arguments.of(
                    List.of(
                            new AttributeRequest("tamanho", "compacto"),
                            new AttributeRequest("cor", "azul compacto"),
                            new AttributeRequest("velocidade", "compacta")
                    )
            ),
                Arguments.of(
                        List.of(
                                new AttributeRequest("cor", "verde"),
                                new AttributeRequest("tamanho", "bom"),
                                new AttributeRequest("porta-malas", "compacto"),
                                new AttributeRequest("velocidade", "rápido")
                        )
                )
        );
    }

    @DisplayName("Should throw exception with < 3 attributes")
    @ParameterizedTest
    @MethodSource("argumentsStreamException")
    void create_product_with_few_attributes(List<AttributeRequest> attributes){

        Category category = new Category("Geral");
        UserSystem userSystem = new UserSystem("m@email.com","12345");
        Assertions.assertThrows(IllegalArgumentException.class, ()->  new Product("carro", BigDecimal.valueOf(180000),1,attributes,"descricao",category,userSystem ));

    }

    static Stream<Arguments> argumentsStreamException(){
        return Stream.of(
                Arguments.of(
                        List.of(
                                new AttributeRequest("tamanho", "compacto"),
                                new AttributeRequest("cor", "azul compacto")
                        )
                ),
                Arguments.of(
                        List.of(
                                new AttributeRequest("cor", "verde")
                        )
                )
        );
    }
    @DisplayName("Should not decrease the stock if the order for greater than the quantity in stock")
    @ParameterizedTest
    @CsvSource({"1,1,true","1,2,false","4,2,true","1,5, false"})
    void decrease_product_inventary(int inventary, int quantity, boolean expectedResult){
        List<AttributeRequest> attributes = new ArrayList<>();
        attributes.add(new AttributeRequest("key1","value1"));
        attributes.add(new AttributeRequest("key2","value2"));
        attributes.add(new AttributeRequest("key3","value3"));

        Category category = new Category("geral");
        UserSystem userSystem = new UserSystem("m@email.com","123456");
        Product product = new Product("nome",BigDecimal.TEN, inventary, attributes,"aaaa", category, userSystem);
        boolean result = product.decreaseInvetory(quantity);
        Assertions.assertEquals(expectedResult, result);
    }

    @DisplayName("Should throws exception for invalid quantity")
    @ParameterizedTest
    @CsvSource({"0","-1","-100"})
    void throw_illegalArgumentException_decrease_inventory(int quantity) throws Exception{
        List<AttributeRequest> attributes = new ArrayList<>();
        attributes.add(new AttributeRequest("key1","value1"));
        attributes.add(new AttributeRequest("key2","value2"));
        attributes.add(new AttributeRequest("key3","value3"));

        Category category = new Category("geral");
        UserSystem userSystem = new UserSystem("m@email.com","123456");
        Product product = new Product("nome",BigDecimal.TEN, 10, attributes,"aaaa", category, userSystem);
        Assertions.assertThrows(IllegalArgumentException.class, ()->product.decreaseInvetory(quantity));
    }
}