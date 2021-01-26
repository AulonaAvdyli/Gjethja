package steps;

import io.restassured.http.ContentType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class ApiCurrentUserSteps extends ScenarioSteps {

    private String CURRENT_USER = "http://localhost:8282/main-leaf-service/current";

    public void loginWithCredentials(String s, String katra) {
        final String login = "http://localhost:8282/main-leaf-service/login";

        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\n" +
                        "\"email\" : \"katrasolutions@gmail.com\",\n" +
                        "\"password\" : \"Katra\"\n" +
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

    @Step
    @Screenshots(disabled=true)
    public void getCurrentUser() {
        loginWithCredentials("katrasolutions@gmail.com", "Katra");
        SerenityRest
                .rest()
                .header("Authorization", "Bearer " + Serenity.sessionVariableCalled("loginToken"))
                .when()
                .get(CURRENT_USER)
                .then()
                .assertThat()
                .statusCode(200);

    }
}
