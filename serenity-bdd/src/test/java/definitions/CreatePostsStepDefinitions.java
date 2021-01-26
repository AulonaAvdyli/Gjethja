package definitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.ApiPostsSteps;


public class CreatePostsStepDefinitions {

    @Steps
    ApiPostsSteps apiPostsSteps;

    @When("^I create post with with title \"([^\"]*)\", description \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void iCreatePostWithWithIdTitleDescriptionAndStatus(String title, String description, String status) {
        apiPostsSteps.createPosts(title, description, status);
    }

    @Then("^Post should be created with title \"([^\"]*)\", description \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void postShouldBeCreatedWithTitleDescriptionAndStatus(String title, String description, String status) {
        apiPostsSteps.verifyPostIsCreated(title, description, status);
    }
}
