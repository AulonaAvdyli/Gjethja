package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import steps.ApiProviderSteps;

import java.util.List;

public class ProviderRegisterStepDefinitions {

    @Steps
    ApiProviderSteps apiProviderSteps;

    @Given("^I create user with firstName \"([^\"]*)\", lastName \"([^\"]*)\", email \"([^\"]*)\", password \"([^\"]*)\", confirmPassword \"([^\"]*)\", address \"([^\"]*)\", gender \"([^\"]*)\", phoneNumber \"([^\"]*)\", city \"([^\"]*)\", bio \"([^\"]*)\", education \"([^\"]*)\", dateOfBirth \"([^\"]*)\" and jobs$")
    public void iCreateUserWithFirstNameLastNameEmailPasswordConfirmPasswordAddressGenderPhoneNumberCityEducationDateOfBirthAndJobs(String firstName, String lastName, String email, String password, String confirmPassword, String address, String gender, String phoneNumber, String city, String bio, String education, String dateOfBirth, List<String> jobs) {

        apiProviderSteps.createUser(firstName,lastName,email,password,confirmPassword,address,gender,phoneNumber,city,education,bio,dateOfBirth,jobs);
    }

    @Then("^Provider User should be created$")
    public void providerUserShouldBeCreated() {

    }

    @And("^Provider User should see message \"([^\"]*)\"$")
    public void providerUserShouldSeeMessage(String msg)
    {
        apiProviderSteps.verifyMessage(msg);
    }
}
