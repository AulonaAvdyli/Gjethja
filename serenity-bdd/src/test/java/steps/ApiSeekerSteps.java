package steps;

import io.restassured.http.ContentType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.hamcrest.Matchers;
import pages.SeekerRegisterPage;

public class ApiSeekerSteps extends ScenarioSteps {

    SeekerRegisterPage registerPage;

    final String CREATE_SEEKER = "http://localhost:8282/main-leaf-service/seekers/register";
    final String UPDATE_SEEKER = "http://localhost:8282/main-leaf-service/seekers/";
    final String DELETE_SEEKER = "http://localhost:8282/main-leaf-service/seekers/";
    final String GET_SEEKER = "http://localhost:8282/main-leaf-service/seekers/";

    @Step("Create user as seeker")
    public void createUser(String firstName, String lastName, String email, String password, String confirmPassword, String address, String gender, String phoneNumber, String city, String education, String bio, String dateOfBirth) {

        registerPage.open();

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .body("{ \n" +
                        "\t\n" +
                        "\t\"firstName\" : \"Ardian\",\n" +
                        "\t\"lastName\" : \"Pllana\",\n" +
                        "\t\"email\" : \"ardi_pllana@hotmail.com\",\n" +
                        "\t\"password\" : \"Shendi12\",\n" +
                        "\t\"confirmPassword\" : \"Shendi12\",\n" +
                        "\t\"address\" : \"Rruga B\",\n" +
                        "\t\"gender\" : \"Male\",\n" +
                        "\t\"phoneNumber\" : \"044123456\",\n" +
                        "\t\"city\" : \"Prishtine\",\n" +
                        "\t\"bio\" : \"bio\",\n" +
                        "\t\"education\" : \"Bachelor\",\n" +
                        "\t\"dateOfBirth\" : \"29-08-1996\"\n" +
                        "\n" +
                        "}")
                .when()
                .post(CREATE_SEEKER)
                .then()
                .assertThat()
                .statusCode(201);


        registerPage.getFirstNameInput().type(firstName);
        registerPage.getLastNameInput().type(lastName);
        registerPage.getEmailInput().type(email);
        registerPage.getPhoneInput().type(phoneNumber);
        registerPage.getPasswordInput().type(password);
        registerPage.getConfirmPasswordInput().type(confirmPassword);
        registerPage.getDateInput().type(dateOfBirth).click();
        if (gender.equalsIgnoreCase("Male")) {
            registerPage.getMaleInput().click();
        }
        if (gender.equalsIgnoreCase("Female")) {
            registerPage.getFemaleInput().click();
        }

        switch (city) {
            case "Prishtine":
                registerPage.getCityPrishtine().click();
                break;
            case "Prizren":
                registerPage.getCityPrizren().click();
                break;
            case "Peje":
                registerPage.getCityPeje().click();
                break;
            case "Gjakove":
                registerPage.getCityGjakove().click();
                break;
            case "Fushe Kosove":
                registerPage.getCityFushekosove().click();
                break;
            case "Ferizaj":
                registerPage.getCityFerizaj().click();
                break;
            case "Skenderaj":
                registerPage.getCitySkenderaj().click();
                break;
            case "Vushtrri":
                registerPage.getCityVushtrri().click();
                break;
            case "Gjilan":
                registerPage.getCityGjilan().click();
                break;
        }
        registerPage.getAddressInput().type(address);
        registerPage.getBioInput().type(bio);
        registerPage.getSubmitButton().click();

    }

    @Step
    public void updateSeeker(String firstName, String lastName, String city, String address, String
            phoneNumber, String bio) {
        loginWithCredentials("ardi_pllana@hotmail.com", "Shendi12");

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .body("{ \n" +
                        "\t\n" +
                        "\t\"firstName\" : \"Ardi\",\n" +
                        "\t\"lastName\" : \"Piupiu\",\n" +
                        "\t\"city\" : \"Paris\",\n" +
                        "\t\"address\" : \"Rruga C\",\n" +
                        "\t\"phoneNumber\" : \"049654321\",\n" +
                        "\t\"bio\" : \"Bio\"\n" +
                        "\n" +
                        "}")
                .header("Authorization", "Bearer " + Serenity.sessionVariableCalled("loginToken"))
                .when()
                .put(UPDATE_SEEKER)
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Step
    public void deleteSeeker() {

        loginWithCredentials("ardi_pllana@hotmail.com", "Shendi12");

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Serenity.sessionVariableCalled("loginToken"))
                .when()
                .delete(DELETE_SEEKER)
                .then()
                .assertThat()
                .statusCode(204);

    }

    @Step
    public void getSeekers() {

        loginWithCredentials("ardi_pllana@hotmail.com", "Shendi12");

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Serenity.sessionVariableCalled("loginToken"))
                .when()
                .get(GET_SEEKER)
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Step("Verify message is equal to {0}")
    public void verifyMessage(final String msg) {
        SerenityRest
                .lastResponse()
                .then()
                .body("message", Matchers.equalTo(msg));
    }

    public void loginWithCredentials(String email, String pass) {
        final String login = "http://localhost:8282/main-leaf-service/login";

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\n" +
                        "\"email\" : \"ardi_pllana@hotmail.com\",\n" +
                        "\"password\" : \"Shendi12\"\n" +
                        "\n" +
                        "}")
                .when()
                .post(login)
                .then()
                .assertThat()
                .statusCode(200);

        String token =
                SerenityRest
                        .lastResponse()
                        .jsonPath()
                        .get("accessToken");
        Serenity.setSessionVariable("loginToken")
                .to(token);
    }
}



