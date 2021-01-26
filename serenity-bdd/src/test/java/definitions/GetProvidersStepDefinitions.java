package definitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.ApiProviderSteps;

public class GetProvidersStepDefinitions {

    @Steps
    ApiProviderSteps apiProviderSteps;

    @When("^User requests to view all providers$")
    public void userRequestsToViewAllProviders() {
    }

    @Then("^A list of all providers will show$")
    public void aListOfAllProvidersWillShow() {
        apiProviderSteps.getProviders();
    }
}
