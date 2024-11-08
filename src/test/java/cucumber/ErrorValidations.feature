@tag
  Feature: Error Validation

    @ErrorValidation
    Scenario Outline:
      Given I landed on E-Commerce page
      When Logged in with username <name> and password <password>
      Then "Incorrect email or password." message is displayed

      Examples:
        | name               | password   |  |
        | keremtest@test.com | Keremtest1 |  |