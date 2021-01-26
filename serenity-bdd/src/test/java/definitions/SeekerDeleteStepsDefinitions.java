package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import steps.ApiSeekerSteps;

public class SeekerDeleteStepsDefinitions {

    @Steps
    ApiSeekerSteps apiSeekerSteps;

    @Given("^Seeker is logged in the web$")
    public void seekerIsLoggedInTheWeb() {
        apiSeekerSteps.loginWithCredentials("ardi_pllana@hotmail.com", "Shendi12");
    }

    @Then("^Seeker deletes his account$")
    public void seekerDeletesHisAccount() {
        apiSeekerSteps.deleteSeeker();
    }
}
