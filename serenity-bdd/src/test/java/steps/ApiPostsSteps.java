package steps;

import io.restassured.http.ContentType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.hamcrest.Matchers;
import pages.CreatePostPage;
import pages.DeletePostPage;
import pages.UpdatePostPage;

public class ApiPostsSteps extends ScenarioSteps {
    final String CREATE_POSTS = "http://localhost:8282/main-leaf-service/posts";
    final String UPDATE_POSTS = "http://localhost:8282/main-leaf-service/posts/";
    final String DELETE_POSTS = "http://localhost:8282/main-leaf-service/posts/";

    private int id;

    CreatePostPage createPostPage;
    DeletePostPage deletePostPage;
    UpdatePostPage updatePostPage;

    @Step("Create posts with data {0}, {1},{2}")
    public void createPosts(String title, String description, String status) {

        createPostPage.open();

        loginWithCredentials("katrasolutions@gmail.com", "Katra");
        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\n" +
                        "\"title\" : \"petcare\",\n" +
                        "\"description\" : \"dhaaksakl\",\n" +
                        "\"status\" : \"Hapur\"\n" +
                        "\n" +
                        "}")
                .header("Authorization", "Bearer " + Serenity.sessionVariableCalled("loginToken"))
                .when()
                .post(CREATE_POSTS)
                .then()
                .assertThat()
                .statusCode(201);

        this.id = SerenityRest.
                lastResponse().jsonPath().getObject("id", Integer.class);

        createPostPage.getCreatePost().click();
        createPostPage.getTitleInput().type(title).isDisplayed();
        createPostPage.getDescriptionInput().type(description);
        if (status.equalsIgnoreCase("Hapur")) {
            createPostPage.getOpenStatus().click();
        }
        if (status.equalsIgnoreCase("Mbyllur")) {
            createPostPage.getClosedStatus().click();
        }
        createPostPage.getButton().click();

    }

    @Step("Verify created post with data {0}, {1} and {2}")
    public void verifyPostIsCreated(String title, String description, String status) {
        SerenityRest
                .lastResponse()
                .then()
                .body("title", Matchers.equalTo(title))
                .body("description", Matchers.equalTo(description))
                .body("status", Matchers.equalTo(status))
                .assertThat()
                .statusCode(201);
    }


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
    public void updatePosts(int id) {
        updatePostPage.open();

        loginWithCredentials("katrasolutions@gmail.com", "Katra");
        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\n" +
                        "\"title\" : \"housekeeper\",\n" +
                        "\"description\" : \"good\",\n" +
                        "\"status\" : \"Hapur\"\n" +
                        "\n" +
                        "}")
                .header("Authorization", "Bearer " + Serenity.sessionVariableCalled("loginToken"))
                .when()
                .put(UPDATE_POSTS + id)
                .then()
                .assertThat()
                .statusCode(200);

        updatePostPage.getPostsButton().click();
        updatePostPage.getEditButton().click();
        updatePostPage.getTitleInput().type("housekeeper");
        updatePostPage.getNameInput().type("good");
        updatePostPage.getOpenedStatus().click();
        updatePostPage.getSubmitButton().click();
    }

    @Step
    public void deletePosts(int id) {
        deletePostPage.open();
        loginWithCredentials("katrasolutions@gmail.com", "Katra");
        SerenityRest
                .rest()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Serenity.sessionVariableCalled("loginToken"))
                .when()
                .delete(DELETE_POSTS + id)
                .then()
                .assertThat()
                .statusCode(204);

        deletePostPage.getPostsbutton().click();
        deletePostPage.getButton().click();
    }
}




