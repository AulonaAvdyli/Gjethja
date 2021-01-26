package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import steps.ApiSeekerSteps;

public class SeekerUpdateStepDefinitions {

    @Steps
    ApiSeekerSteps apiSeekerSteps;

    @Given("^I update existing seeker user with firstName \"([^\"]*)\", lastName \"([^\"]*)\", city \"([^\"]*)\", address \"([^\"]*)\", phoneNumber \"([^\"]*)\", bio \"([^\"]*)\"$")
    public void iUpdateExistingSeekerUserWithFirstNameLastNameCityAddressPhoneNumberBio(String firstName, String lastName, String city, String address, String phoneNumber, String bio){
        apiSeekerSteps.updateSeeker(firstName, lastName, city, address, phoneNumber, bio);
    }

    @Then("^Seeker User should be updated$")
    public void seekerUserShouldBeUpdated() {
    }

}
