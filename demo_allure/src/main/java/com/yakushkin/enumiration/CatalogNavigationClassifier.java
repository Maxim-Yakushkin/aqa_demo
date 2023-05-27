package com.yakushkin.enumiration;

public enum CatalogNavigationClassifier {

    ELECTRONICS("Электроника"),
    COMPUTERS_AND_NETWORKS("Компьютеры и сети"),
    HOUSEHOLD_APPLIANCES("Бытовая техника"),
    FOR_EVERY_DAY("На каждый день"),
    CONSTRUCTION_AND_REPAIR("Стройка и ремонт"),
    HOUSE_AND_GARDEN("Дом и сад"),
    AUTO_AND_MOTO("Авто и мото"),
    BEAUTY_AND_SPORT("Красота и спорт"),
    CHILDREN_AND_MOTHERS("Детям и мамам");

    private final String title;

    CatalogNavigationClassifier(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
