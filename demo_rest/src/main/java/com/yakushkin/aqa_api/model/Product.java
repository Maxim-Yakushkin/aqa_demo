package com.yakushkin.aqa_api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Integer id;
    private String key;
    private String name;
    @JsonAlias("full_name")
    private String fullName;
    @JsonAlias("name_prefix")
    private String namePrefix;
}
