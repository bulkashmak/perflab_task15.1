Feature: Regard cart feature

  Scenario: Test adding items to cart

    Given user is on main page

    When user click motherboards in left menu
    And user click 'Intel Socket 1200' in motherboards submenu
    Then browser open page with 'Intel Socket 1200' motherboards
    And user click add to cart button of 5th motherboard on page

    When user click cases in left menu
    And click 'AEROCOOL' in cases submenu
    Then browser open page with 'AeroCool' cases
    And user click add to cart button of 4th case on page
    And user click name of 10th case on page

    Given user is on product page

    When user click add to cart button on product page
    And user click blue button open cart
    Then browser open cart page
