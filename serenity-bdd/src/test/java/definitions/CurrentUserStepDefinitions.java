package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Steps;
import steps.ApiCurrentUserSteps;


public class CurrentUserStepDefinitions {

    @Steps
    ApiCurrentUserSteps currentUserSteps;

    @Given("^user should be logged-in$")
    public void userShouldBeLoggedIn() {

    }

    @Then("^you should see the current user$")
    public void youShouldSeeTheCurrentUser() {
        currentUserSteps.getCurrentUser();
    }
}
