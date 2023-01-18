Feature: test

  Scenario Outline: Login Page
    Given Browser is Open
    And a valid loaded page
    And a valid details submitted to login
    And a User adds item in the cart
    And a user enters details to clear cart
    Then a user validate text to complete the test
    
