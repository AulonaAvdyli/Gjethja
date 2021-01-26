Feature: SeekerUpdate

#  @debug
  @Regression
  Scenario: Update seeker data
    Given I update existing seeker user with firstName "Ardi", lastName "Piupiu", city "Paris", address "Rruga C", phoneNumber "049654321", bio "Bio"
    Then Seeker User should be updated


