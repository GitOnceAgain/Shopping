
@tag
Feature: Place the order from Ecommerce website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive of submitting the order
    Given I logged in with Username <name> and Password <password>
    When I add product <ProductName> to Cart 
    And Chechout <ProductName> and submit the order
    Then "THANKYOU FOR THE ORDER." messsage is displayed on confirmation

    Examples: 
      |  name           |   password  |  ProductName   |
      | rajashree@gmail.com | Rajashree@4 |ADIDAS ORIGINAL |
    
