Feature: SeekerRegister
  @Smoke
#  @debug
  @Regression
  Scenario: Create user as seeker
    Given I create user with firstName "Ardian", lastName "Pllana", email "ardi_pllana@hotmail.com", password "Shendi12", confirmPassword "Shendi12", address "Rruga B", gender "Male", phoneNumber "044123456", city "Prishtine", education "Bachelor" , bio "bio" and dateOfBirth "29-08-1996"
    Then Seeker User should be created
    And  Seeker User should see message "Ardian registered successfully, all that's left is to confirm the email"


