Feature: As an open web user
  I want to add any product from Catalog section to shopping cart

  @Scenario_1
  Scenario Outline: Shopping cart should contains selected product by click on 'В корзину' button
    Given the user opened any product page "<link>"
    When the user clicks on "В корзину" button from any supplier
    And the user close recommended sidebar if it displayed
    And the user click on shopping cart icon
    Then the shopping cart contains the previously selected product
    Then the product page contains button with "В корзине" name for previously selected supplier

    Examples:
      | link                                                           |
      | https://catalog.onliner.by/videocard/gigabyte/gvn407teagleoc12 |
      | https://catalog.onliner.by/mobile/xiaomi/mi1312256gllg         |