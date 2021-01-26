Feature: ProviderRegister
  @Smoke
#  @debug
  @Regression
  Scenario: Create user as provider
    Given I create user with firstName "Aulona", lastName "Avdyli", email "rinesaavdyli@live.com", password "Aulona", confirmPassword "Aulona", address "Ferizaj", gender "Female", phoneNumber "045271029", city "Ferizaj", bio "bio", education "Lart", dateOfBirth "12-03-1998" and jobs

       | housekeeper | petcare | babysitter | eldercare |

    Then Provider User should be created
    And  Provider User should see message "Aulona registered successfully, all that's left is to confirm the email"


