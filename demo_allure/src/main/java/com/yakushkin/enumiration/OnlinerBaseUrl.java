package com.yakushkin.enumiration;

public enum OnlinerBaseUrl {

    MAIN_PAGE("https://www.onliner.by/", "Onlíner"),
    CATALOG_PAGE("https://catalog.onliner.by/", "Каталог Onlíner");

    private final String url;
    private final String title;

    OnlinerBaseUrl(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
