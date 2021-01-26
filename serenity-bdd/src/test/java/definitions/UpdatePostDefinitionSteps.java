package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.ApiPostsSteps;

public class UpdatePostDefinitionSteps {

    @Steps
    ApiPostsSteps apiPostsSteps;

    @Given("^User is on the users page$")
    public void userIsOnTheUsersPage() {
    }

    @When("^The user updates post with id (\\d+)$")
    public void theUserUpdatesPostWith(int id) {
        apiPostsSteps.updatePosts(id);
    }

    @Then("^They should see the updated post$")
    public void theyShouldSeeThatThePostUpdated() {
    }
}
