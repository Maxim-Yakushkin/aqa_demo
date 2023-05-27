package com.yakushkin.enumiration;

public enum SearchFrameTab {
    IN_CATALOG("в каталоге"),
    IN_NEWS("в новостях"),
    IN_FLEA_MARKET("на барахолке"),
    IN_FORUM("на форуме");

    private final String name;

    SearchFrameTab(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
