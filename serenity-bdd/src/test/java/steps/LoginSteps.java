package steps;

import io.restassured.http.ContentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.steps.ScenarioSteps;
import pages.LoginPage;

/**
 * Created by Ardit Podrimaj
 */

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginSteps extends ScenarioSteps {


    final String login = "http://localhost:8282/main-leaf-service/login";

    public void loginWithCredentials(String s, String katra) {

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
    }
}

