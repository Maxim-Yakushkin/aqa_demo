package com.yakushkin.aqa_api.service;

import com.yakushkin.aqa_api.enumiration.SushiType;
import com.yakushkin.aqa_api.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void checkAllProductsHaveNameAndFullName() {
        final List<Product> products = productService.getAll()
                .getProducts();

        assertAll(
                () -> assertThat(products).isNotNull(),
                () -> assertThat(products).hasSize(30),
                () -> assertThat(products).noneMatch(product -> product.getName().isBlank() &&
                                                                product.getFullName().isBlank()));
    }

    @Test
    void checkGetProductByJsonIndexService() {
        final Product product = productService.getByJsonIndex(0)
                .getProduct();

        assertAll(
                () -> assertNotNull(product),
                () -> assertNotNull(product.getId()),
                () -> notBlank(product.getKey()),
                () -> notBlank(product.getName()),
                () -> notBlank(product.getFullName())
        );
    }

    @ParameterizedTest
    @EnumSource(SushiType.class)
    void checkAllProductsHaveNamePrefixCorrespondingTheirProductType(SushiType sushiType) {
        final List<Product> products = productService.getProductListBySushiType(sushiType)
                .getProducts();

        assertAll(
                () -> assertThat(products).isNotNull(),
                () -> assertThat(products).allMatch(product -> product.getNamePrefix().equals(sushiType.getNamePrefix()))
        );
    }
}