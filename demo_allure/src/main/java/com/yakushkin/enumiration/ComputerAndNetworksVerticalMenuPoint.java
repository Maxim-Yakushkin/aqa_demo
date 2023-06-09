package com.yakushkin.enumiration;

public enum ComputerAndNetworksVerticalMenuPoint {

    LAPTOPS_COMPUTERS_MONITORS("Ноутбуки, компьютеры, мониторы"),
    ACCESSORIES("Комплектующие"),
    PRINTING_AND_DESIGN_EQUIPMENT("Техника для печати и дизайна"),
    CASH_REGISTERS_AND_COMMERCIAL_EQUIPMENT("Кассовые аппараты и торговое оборудование"),
    MANIPULATORS_AND_INPUT_DEVICES("Манипуляторы и устройства ввода"),
    DATA_STORAGE("Хранение данных"),
    MULTIMEDIA_PERIPHERALS("Мультимедиа периферия"),
    NETWORK_EQUIPMENT("Сетевое оборудование"),
    ACCESSORIES_FOR_LAPTOPS_AND_COMPUTERS("Аксессуары к ноутбукам и компьютерам"),
    POWER_SUPPLY("Электропитание"),
    GAMES_AND_SOFTWARE("Игры и программное обеспечение");

    private final String name;

    ComputerAndNetworksVerticalMenuPoint(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
