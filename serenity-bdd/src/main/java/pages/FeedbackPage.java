package pages;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@DefaultUrl("http://localhost:3000/#/")
public class FeedbackPage extends AbstractPage {

    @FindBy(id = "feedback-input")
    WebElementFacade titleInput;

    @FindBy(id="feedback-button")
    WebElementFacade feedback;

    @FindBy(id="submit-button")
    WebElementFacade submit;

    public WebElementFacade getTitleInput() {
        return titleInput;
    }

    public WebElementFacade getSubmit() {
        return submit;
    }

    public WebElementFacade getFeedback() {
        return feedback;
    }
}
