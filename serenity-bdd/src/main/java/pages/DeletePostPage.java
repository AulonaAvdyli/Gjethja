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
public class DeletePostPage extends AbstractPage {

    @FindBy(id = "delete-button")
    WebElementFacade button;

    @FindBy(id = "posts-button")
    WebElementFacade postsbutton;

    public WebElementFacade getButton() {
        return button;
    }

    public WebElementFacade getPostsbutton() {
        return postsbutton;
    }
}
