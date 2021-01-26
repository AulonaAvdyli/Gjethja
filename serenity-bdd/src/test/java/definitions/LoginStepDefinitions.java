package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.ApiLoginSteps;
import steps.LoginSteps;

/**
 * Created by Ardit Podrimaj
 */

public class LoginStepDefinitions {

    @Steps
    ApiLoginSteps apiLoginSteps;

    @Steps
    LoginSteps loginSteps;

    //for post.feature
    @Given("^User is logged in the web$")
    public void userIsLoggedInTheWeb() {

        loginSteps.loginWithCredentials("katrasolutions@gmail.com", "Katra");
    }

    @Given("^the user is on login page$")
    public void theUserIsOnLoginPage() {
        apiLoginSteps.isOnLoginPage();
    }

    @When("^the user is logged in with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void theUserIsLoggedInWithEmailAndPassword(String email, String password) {
        apiLoginSteps.login(email,password);
    }

    @Then("^User should be logged in$")
    public void userShouldBeLoggedIn() {
        apiLoginSteps.verifyUserIsLoggedIn();
    }
}
