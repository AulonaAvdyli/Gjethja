Feature: CurrentUser

#@debug
@Regression
  Scenario: Get current user
    Given user should be logged-in
    Then you should see the current user