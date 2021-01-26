Feature: ProviderUpdate

#  @debug
  @Regression
  Scenario: Update provider data
    Given I update existing provider user with firstName "Ardi", lastName "Piupiu", city "Paris", address "Rruga C", phoneNumber "049654321", bio "Bio"
    Then Provider User should be updated


