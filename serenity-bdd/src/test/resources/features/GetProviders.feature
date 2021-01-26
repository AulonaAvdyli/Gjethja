Feature: GetProviders

#  @debug
  @Regression
  Scenario: Get all providers
    Given User is logged in the web
    When User requests to view all providers
    Then A list of all providers will show

