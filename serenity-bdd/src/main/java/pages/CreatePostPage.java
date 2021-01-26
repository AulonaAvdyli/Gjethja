package pages;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.ui.ExpectedCondition;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@DefaultUrl("http://localhost:3000/#/home")
public class CreatePostPage extends AbstractPage {

    @FindBy(id ="create-post")
    WebElementFacade createPost;

    @FindBy(name="title-input")
    WebElementFacade titleInput;

    @FindBy(id="name-input")
    WebElementFacade descriptionInput;

    @FindBy(id="opened")
    WebElementFacade openStatus;

    @FindBy(id="closed")
    WebElementFacade closedStatus;

    @FindBy(id="button-input")
    WebElementFacade button;

    public WebElementFacade getButton() {
        return button;
    }

    public void setButton(WebElementFacade button) {
        this.button = button;
    }

    public WebElementFacade getTitleInput() {
        return titleInput;
    }

    public void setTitleInput(WebElementFacade titleInput) {
        this.titleInput = titleInput;
    }

    public WebElementFacade getDescriptionInput() {
        return descriptionInput;
    }

    public void setDescriptionInput(WebElementFacade descriptionInput) {
        this.descriptionInput = descriptionInput;
    }

    public WebElementFacade getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(WebElementFacade openStatus) {
        this.openStatus = openStatus;
    }

    public WebElementFacade getClosedStatus() {
        return closedStatus;
    }

    public void setClosedStatus(WebElementFacade closedStatus) {
        this.closedStatus = closedStatus;
    }

    public WebElementFacade getCreatePost() {
        return createPost;
    }
}
