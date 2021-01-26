Feature: Feedback
  
  @Smoke
#  @debug
   @Regression
  Scenario: Create a feedback
    Given User is logged in
    When I create feedback with description "good"
    Then Feedback should be created with description "good"

