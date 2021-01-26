package steps;

import io.restassured.http.ContentType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.hamcrest.Matchers;
import org.openqa.selenium.JavascriptExecutor;
import pages.ProviderRegisterPage;

import java.util.List;

public class ApiProviderSteps extends ScenarioSteps {

    ProviderRegisterPage registerPage;

    final String CREATE_PROVIDER = "http://localhost:8282/main-leaf-service/providers/register";
    final String UPDATE_PROVIDER = "http://localhost:8282/main-leaf-service/providers/";
    final String DELETE_PROVIDER = "http://localhost:8282/main-leaf-service/providers/";
    final String GET_PROVIDER = "http://localhost:8282/main-leaf-service/providers/";

    public void createUser(String firstName, String lastName, String email, String password, String confirmPassword, String address, String gender, String phoneNumber, String city, String bio, String education, String dateOfBirth, List<String> jobs) {

        registerPage.open();

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .body("{ \n" +
                        "\t\n" +
                        "\t\"firstName\" : \"Aulona\",\n" +
                        "\t\"lastName\" : \"Avdyli\",\n" +
                        "\t\"email\" : \"rinesaavdyli@live.com\",\n" +
                        "\t\"password\" : \"Aulona\",\n" +
                        "\t\"confirmPassword\" : \"Aulona\",\n" +
                        "\t\"address\" : \"Ferizaj\",\n" +
                        "\t\"gender\" : \"Female\",\n" +
                        "\t\"phoneNumber\" : \"045217029\",\n" +
                        "\t\"city\" : \"Ferizaj\",\n" +
                        "\t\"education\" : \"Lart\",\n" +
                        "\t\"bio\" : \"bio\",\n" +
                        "\t\"dateOfBirth\" : \"12-03-1998\",\n" +
                        "\t\"jobs\" : [\"housekeeper\", \"petcare\", \"babysitter\",\"eldercare\"]\n" +
                        "\n" +
                        "}")
                .when()
                .post(CREATE_PROVIDER)
                .then()
                .assertThat()
                .statusCode(201);

        registerPage.getFirstNameInput().type(firstName);
        registerPage.getLastNameInput().type(lastName);
        registerPage.getEmailInput().type(email);
        registerPage.getPhoneInput().type(phoneNumber);
        registerPage.getPasswordInput().type(password);
        registerPage.getConfirmPasswordInput().type(confirmPassword);

//        JavascriptExecutor js = (JavascriptExecutor)getDriver();
//        js.executeScript("document.getElementById('birthday-input').value='12031998'");
        registerPage.getDateInput().type("12031998");
//        registerPage.getDateInput().selectByVisibleText(dateOfBirth);

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
        if (education.equalsIgnoreCase("Ulet")) {
            registerPage.getUletEducation().click();
        }
        if (gender.equalsIgnoreCase("Mesem")) {
            registerPage.getMesemEducation().click();
        }
        if (gender.equalsIgnoreCase("Lart")) {
            registerPage.getLartEducation().click();
        }

        registerPage.getSubmitButton().click();
    }

    @Step
    public void updateProvider (String firstName, String lastName, String city, String address, String
            phoneNumber, String bio){
        loginWithCredentials("rinesaavdyli@live.com", "Aulona");

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
                .put(UPDATE_PROVIDER)
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Step
    public void deleteProvider(){

        loginWithCredentials("rinesaavdyli@live.com", "Aulona");

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Serenity.sessionVariableCalled("loginToken"))
                .when()
                .delete(DELETE_PROVIDER)
                .then()
                .assertThat()
                .statusCode(204);

    }

    @Step
    public void getProviders(){

        loginWithCredentials("rinesaavdyli@live.com", "Aulona");

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Serenity.sessionVariableCalled("loginToken"))
                .when()
                .get(GET_PROVIDER)
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

    public void loginWithCredentials (String email, String pass){
        final String login = "http://localhost:8282/main-leaf-service/login";

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\n" +
                        "\"email\" : \"rinesaavdyli@live.com\",\n" +
                        "\"password\" : \"Aulona\"\n" +
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

