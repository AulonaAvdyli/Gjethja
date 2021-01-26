package steps;

import io.restassured.http.ContentType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.hamcrest.Matchers;
import pages.FeedbackPage;

public class ApiFeedbackSteps extends ScenarioSteps {

    private final String CREATE_FEEDBACK = "http://localhost:8282/feedback-service/feedback";

    FeedbackPage feedbackPage;

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

    @Step("Create feedback with description {0}")
    public void createFeedback(String description) {
        feedbackPage.open();

        loginWithCredentials("katrasolutions@gmail.com", "Katra");
        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\t\"description\" : \"good\"\n" +
                        "}")
                .header("Authorization", "Bearer " + Serenity.sessionVariableCalled("loginToken"))
                .when()
                .post(CREATE_FEEDBACK)
                .then()
                .assertThat()
                .statusCode(201);

        feedbackPage.getFeedback().click();
        feedbackPage.getTitleInput().type(description);
        feedbackPage.getSubmit().click();
    }

    @Step("Verify created feedback with data {0}")
    public void verifyFeedbackItsCreated(String description) {
        SerenityRest
                .lastResponse()
                .then()
                .body("description", Matchers.equalTo(description))
                .assertThat()
                .statusCode(201);
    }
}
