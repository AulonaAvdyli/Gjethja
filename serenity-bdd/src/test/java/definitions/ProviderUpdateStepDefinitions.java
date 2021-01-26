package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import steps.ApiPostsSteps;
import steps.ApiProviderSteps;

public class ProviderUpdateStepDefinitions {

    @Steps
    ApiProviderSteps apiProviderSteps;

    @Given("^I update existing provider user with firstName \"([^\"]*)\", lastName \"([^\"]*)\", city \"([^\"]*)\", address \"([^\"]*)\", phoneNumber \"([^\"]*)\", bio \"([^\"]*)\"$")
    public void iUpdateExistingProviderUserWithFirstNameLastNameCityAddressPhoneNumberBio(String firstName, String lastName, String city, String address, String phoneNumber, String bio) {
        apiProviderSteps.updateProvider(firstName, lastName, city, address, phoneNumber, bio);
    }

    @Then("^Provider User should be updated$")
    public void providerUserShouldBeUpdated() {
    }
}
