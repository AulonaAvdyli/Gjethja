Feature: GetSeekers

#  @debug
  @Regression
  Scenario: Get all seekers
    Given User is logged in the web
    When User requests to view all seekers
    Then A list of all seekers will show

