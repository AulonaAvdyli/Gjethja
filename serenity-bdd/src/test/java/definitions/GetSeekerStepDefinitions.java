package definitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.ApiSeekerSteps;

public class GetSeekerStepDefinitions {

    @Steps
    ApiSeekerSteps apiSeekerSteps;

    @When("^User requests to view all seekers$")
    public void userRequestsToViewAllSeekers() {
    }

    @Then("^A list of all seekers will show$")
    public void aListOfAllSeekersWillShow() {
        apiSeekerSteps.getSeekers();
    }
}
