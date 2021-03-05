package com.micaelps.ecommerce.newProduct;

import com.micaelps.ecommerce.newCategory.Category;
import com.micaelps.ecommerce.newUserSystem.UserSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
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
}