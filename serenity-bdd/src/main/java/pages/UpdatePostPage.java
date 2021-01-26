package pages;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@DefaultUrl("http://localhost:3000/#/profile")
public class UpdatePostPage extends AbstractPage {

    @FindBy(id = "posts-button")
    WebElementFacade postsButton;

    @FindBy(id = "edit-button")
    WebElementFacade editButton;

    @FindBy(id = "title-input")
    WebElementFacade titleInput;

    @FindBy(id = "name-input")
    WebElementFacade nameInput;

    @FindBy(id = "opened")
    WebElementFacade openedStatus;

    @FindBy(id = "closed")
    WebElementFacade closedStatus;

    @FindBy(id = "submit-button")
    WebElementFacade submitButton;

    public WebElementFacade getPostsButton() {
        return postsButton;
    }

    public WebElementFacade getEditButton() {
        return editButton;
    }

    public WebElementFacade getTitleInput() {
        return titleInput;
    }

    public WebElementFacade getNameInput() {
        return nameInput;
    }

    public WebElementFacade getOpenedStatus() {
        return openedStatus;
    }

    public WebElementFacade getClosedStatus() {
        return closedStatus;
    }

    public WebElementFacade getSubmitButton() {
        return submitButton;
    }

    public void setPostsButton(WebElementFacade postsButton) {
        this.postsButton = postsButton;
    }

    public void setEditButton(WebElementFacade editButton) {
        this.editButton = editButton;
    }

    public void setTitleInput(WebElementFacade titleInput) {
        this.titleInput = titleInput;
    }

    public void setNameInput(WebElementFacade nameInput) {
        this.nameInput = nameInput;
    }

    public void setOpenedStatus(WebElementFacade openedStatus) {
        this.openedStatus = openedStatus;
    }

    public void setClosedStatus(WebElementFacade closedStatus) {
        this.closedStatus = closedStatus;
    }

    public void setSubmitButton(WebElementFacade submitButton) {
        this.submitButton = submitButton;
    }
}
