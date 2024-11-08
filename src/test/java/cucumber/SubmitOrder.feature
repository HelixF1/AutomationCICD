
@tag
  Feature: Purchase the order from E-Commerce Website

    Background:
      Given I landed on E-Commerce page

    @Regression
    Scenario Outline: Positive Test of Submitting the order
      Given Logged in with username <name> and password <password>
      When I add product <productName> to cart
      And  Checkout <productName> and submit the order
      Then "thankyou for the order." message is displayed on ConfirmationPage

      Examples:
        | name               | password     | productName |
        | keremtest@test.com | Keremtest123 | ZARA COAT 3 |