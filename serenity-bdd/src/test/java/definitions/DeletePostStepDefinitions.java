package definitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.ApiPostsSteps;

public class DeletePostStepDefinitions {
    @Steps
    ApiPostsSteps apiPostsSteps;

    @When("^The user deletes post with id (\\d+)$")
    public void theUserDeletesPostWith(int id) {
        apiPostsSteps.deletePosts(id);
    }

    @Then("^The post should be deleted$")
    public void thePostShouldBeDeleted() {
    }
}

