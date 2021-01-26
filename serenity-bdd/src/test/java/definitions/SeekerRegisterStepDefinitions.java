package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import steps.ApiSeekerSteps;


public class SeekerRegisterStepDefinitions {

    @Steps
    ApiSeekerSteps apiSeekerSteps;

    @Given("^I create user with firstName \"([^\"]*)\", lastName \"([^\"]*)\", email \"([^\"]*)\", password \"([^\"]*)\", confirmPassword \"([^\"]*)\", address \"([^\"]*)\", gender \"([^\"]*)\", phoneNumber \"([^\"]*)\", city \"([^\"]*)\", education \"([^\"]*)\" , bio \"([^\"]*)\" and dateOfBirth \"([^\"]*)\"$")
    public void iCreateUserWithFirstNameLastNameEmailPasswordConfirmPasswordAddressGenderPhoneNumberCityEducationAndDateOfBirth(String firstName, String lastName, String email, String password, String confirmPassword, String address, String gender, String phoneNumber, String city, String education, String bio, String dateOfBirth) {
        apiSeekerSteps.createUser(firstName, lastName, email, password, confirmPassword, address, gender, phoneNumber, city, education, bio, dateOfBirth);
    }

    @Then("^Seeker User should be created$")
    public void seekerUserShouldBeCreated() {
    }

    @And("^Seeker User should see message \"([^\"]*)\"$")
    public void seekerUserShouldSeeMessage(String msg) {
        apiSeekerSteps.verifyMessage(msg);
    }

}
