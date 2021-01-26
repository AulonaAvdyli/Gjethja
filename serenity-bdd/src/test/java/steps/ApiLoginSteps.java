package steps;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.LoginPage;

public class ApiLoginSteps extends ScenarioSteps {

    LoginPage loginPage;

    @Step
    public void isOnLoginPage() {
        loginPage.open();
    }

    @Step
    public void login(String email, String password) {

        String req = "{\"email\":\"katrasolutions@gmail.com\",\"password\" : \"Katra\"}";

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .body(req)
                .when()
                .post("http://localhost:8282/main-leaf-service/login")
                .then()
                .assertThat()
                .statusCode(200);

        loginPage.getEmailInput().type(email);
        loginPage.getPasswordInput().type(password);
        loginPage.getSubmitButton().click();

    }

    @Step
    public void verifyUserIsLoggedIn() {

        SerenityRest
                .lastResponse()
                .then()
                .assertThat()
                .statusCode(200);
    }
}

