Feature: User

  @Regression
  Scenario: Create user
    Given I create user with name "Filan" and last name "Fisteku"
    Then User should be created
#    Then User should be created with user name "Filan"