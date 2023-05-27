package com.yakushkin.aqa_api.enumiration;

import org.apache.commons.lang3.StringUtils;

public enum SushiType {

    SALADS(StringUtils.EMPTY),
    SET("Сет"),
    SOUCE("Соус"),
    GARNIER(StringUtils.EMPTY),
    ROLL("Роллы"),
    DESERT(StringUtils.EMPTY);

    private final String namePrefix;

    SushiType(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getNamePrefix() {
        return namePrefix;
    }
}
