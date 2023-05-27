package com.yakushkin.aqa_api.service;

import com.yakushkin.aqa_api.enumiration.SushiType;
import com.yakushkin.aqa_api.model.Product;
import com.yakushkin.aqa_api.response.ProductListResponse;
import com.yakushkin.aqa_api.response.ProductResponse;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class ProductService {

    @Value("${onliner.sushivesla.general}")
    private String generalEndpoint;
    @Value("${onliner.sushivesla.filtered.by_type_pattern}")
    private String filteredByTypePattern;

    public ProductListResponse getAll() {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get(generalEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ProductListResponse.class);
    }

    public ProductResponse getByJsonIndex(int jsonIndex) {
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
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get(String.format(filteredByTypePattern, sushiType.name().toLowerCase()))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ProductListResponse.class);
    }
}
