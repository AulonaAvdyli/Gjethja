package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.ApiFeedbackSteps;

public class FeedbackStepDefinitions {

    @Steps
    ApiFeedbackSteps apiFeedbackSteps;

    @Given("^User is logged in$")
    public void userIsLoggedIn() {
    }

    @When("^I create feedback with description \"([^\"]*)\"$")
    public void iCreateFeedbackWithDescription(String description) {

        apiFeedbackSteps.createFeedback(description);
    }


    @Then("^Feedback should be created with description \"([^\"]*)\"$")
    public void feedbackShouldBeCreatedWithDescription(String description) {

        apiFeedbackSteps.verifyFeedbackItsCreated(description);
    }
}
