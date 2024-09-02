
@tag
Feature: Error Validation	
  I want to use this template for my feature file


  @ErrorValidations
  Scenario Outline: Title of your scenario outline
Given I landed on Ecommerce Page
    When I logged in with Username <name> and Password <password>
    Then "Incorrect email or password." message is displayed

   
    Examples: 
      |  name               |   password  | 
      | rajashree@gmail.com | Rajashree4  |