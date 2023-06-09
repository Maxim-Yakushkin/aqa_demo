package com.yakushkin.aqa_api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yakushkin.aqa_api.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

    private Product product;
}
