package com.yakushkin.aqa_api.service;

import com.yakushkin.aqa_api.enumiration.SushiType;
import com.yakushkin.aqa_api.model.Product;
import com.yakushkin.aqa_api.response.ProductListResponse;
import com.yakushkin.aqa_api.response.ProductResponse;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
@Slf4j
public class ProductService {

    @Value("${onliner.sushivesla.general}")
    private String generalEndpoint;
    @Value("${onliner.sushivesla.filtered.by_type_pattern}")
    private String filteredByTypePattern;

    public ProductListResponse getAll() {
        log.info("\nENDPOINT: {}", generalEndpoint);
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get(generalEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ProductListResponse.class);
    }

    public ProductResponse getByJsonIndex(int jsonIndex) {
        log.info("\nENDPOINT: {}", generalEndpoint);
        final Product product = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get(generalEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getObject(String.format("products[%s]", jsonIndex), Product.class);

        return new ProductResponse(product);
    }

    public ProductListResponse getProductListBySushiType(SushiType sushiType) {
        final String filtered_endpoint = String.format(filteredByTypePattern, sushiType.name().toLowerCase());
        log.info("\nENDPOINT: {}\nSUSHI_TYPE: {}\nSUSHI_PREFIX: {}", filtered_endpoint, sushiType.name(), sushiType.getNamePrefix());

        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get(filtered_endpoint)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ProductListResponse.class);
    }
}
