Feature: Login

  @Smoke
#  @debug
  @Regression
  Scenario: Login with valid credentials
    Given the user is on login page
    When the user is logged in with email "katrasolutions@gmail.com" and password "Katra"
    Then User should be logged in
