package pages;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@DefaultUrl("http://localhost:3000/#/login")
public class LoginPage extends AbstractPage {

    @FindBy(id = "email-input")
    WebElementFacade emailInput;

    @FindBy(id = "password-input")
    WebElementFacade passwordInput;

    public WebElementFacade getEmailInput() {
        return emailInput;
    }

    public WebElementFacade getPasswordInput() {
        return passwordInput;
    }

    public WebElementFacade getSubmitButton() {
        return submitButton;
    }

    @FindBy(id = "button-input")
    WebElementFacade submitButton;
}