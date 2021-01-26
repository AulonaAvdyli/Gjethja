package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import steps.ApiProviderSteps;

public class ProviderDeleteStepDefinitions {

    @Steps
    ApiProviderSteps apiProviderSteps;

    @Given("^Provider is logged in the web$")
    public void providerIsLoggedInTheWeb() {
        apiProviderSteps.loginWithCredentials("ardi_pllana@hotmail.com", "Shendi12");
    }

    @Then("^Provider deletes his account$")
    public void providerDeletesHisAccount() {
        apiProviderSteps.deleteProvider();
    }

}
