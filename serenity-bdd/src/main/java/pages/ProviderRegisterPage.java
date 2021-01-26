package pages;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@DefaultUrl("http://localhost:3000/#/register/provider")
public class ProviderRegisterPage extends AbstractPage{

    @FindBy(id = "name-input")
    WebElementFacade firstNameInput;

    @FindBy(id = "lastname-input")
    WebElementFacade lastNameInput;

    @FindBy(id = "email-input")
    WebElementFacade emailInput;

    @FindBy(id = "phone-input")
    WebElementFacade phoneInput;

    @FindBy(id = "password-input")
    WebElementFacade passwordInput;

    @FindBy(id = "confirm-password-input")
    WebElementFacade confirmPasswordInput;

    @FindBy(id = "bio-input")
    WebElementFacade bioInput;

    @FindBy(id = "birthday-input")
    WebElementFacade dateInput;

    @FindBy(id = "address-input")
    WebElementFacade addressInput;

    @FindBy(id = "Male")
    WebElementFacade maleInput;

    @FindBy(id = "Female")
    WebElementFacade femaleInput;

    @FindBy(id = "Prishtine")
    WebElementFacade cityPrishtine;

    @FindBy(id = "Peje")
    WebElementFacade cityPeje;

    @FindBy(id = "Prizren")
    WebElementFacade cityPrizren;

    @FindBy(id = "Gjakove")
    WebElementFacade cityGjakove;

    @FindBy(id = "Fushe Kosove")
    WebElementFacade cityFushekosove;

    @FindBy(id = "Ferizaj")
    WebElementFacade cityFerizaj;

    @FindBy(id = "Skenderaj")
    WebElementFacade citySkenderaj;

    @FindBy(id = "Vushtrri")
    WebElementFacade cityVushtrri;

    @FindBy(id = "Gjilan")
    WebElementFacade cityGjilan;

    @FindBy(id="Ulet")
    WebElementFacade uletEducation;

    @FindBy(id="Mesem")
    WebElementFacade mesemEducation;

    @FindBy(id = "Lart")
    WebElementFacade lartEducation;

    @FindBy(id = "submit-button")
    WebElementFacade submitButton;


    public WebElementFacade getFirstNameInput() {
        return firstNameInput;
    }

    public WebElementFacade getLastNameInput() {
        return lastNameInput;
    }

    public WebElementFacade getEmailInput() {
        return emailInput;
    }

    public WebElementFacade getPasswordInput() {
        return passwordInput;
    }

    public WebElementFacade getConfirmPasswordInput() {
        return confirmPasswordInput;
    }

    public WebElementFacade getBioInput() {
        return bioInput;
    }

    public WebElementFacade getDateInput() {
        return dateInput;
    }

    public WebElementFacade getAddressInput() {
        return addressInput;
    }

    public WebElementFacade getMaleInput() {
        return maleInput;
    }

    public WebElementFacade getFemaleInput() {
        return femaleInput;
    }

    public WebElementFacade getSubmitButton() {
        return submitButton;
    }

    public WebElementFacade getCityPeje() {
        return cityPeje;
    }

    public WebElementFacade getCityPrizren() {
        return cityPrizren;
    }

    public WebElementFacade getCityGjakove() {
        return cityGjakove;
    }

    public WebElementFacade getCityFushekosove() {
        return cityFushekosove;
    }

    public WebElementFacade getCityFerizaj() {
        return cityFerizaj;
    }

    public WebElementFacade getCitySkenderaj() {
        return citySkenderaj;
    }

    public WebElementFacade getCityVushtrri() {
        return cityVushtrri;
    }

    public WebElementFacade getCityGjilan() {
        return cityGjilan;
    }

    public WebElementFacade getUletEducation() {
        return uletEducation;
    }

    public WebElementFacade getMesemEducation() {
        return mesemEducation;
    }

    public WebElementFacade getLartEducation() {
        return lartEducation;
    }

    public WebElementFacade getPhoneInput() {
        return phoneInput;
    }

    public WebElementFacade getCityPrishtine() {
        return cityPrishtine;
    }
}


