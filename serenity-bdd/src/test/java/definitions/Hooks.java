package definitions;

import cucumber.api.java.Before;
import net.serenitybdd.rest.SerenityRest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static net.serenitybdd.core.Serenity.getWebdriverManager;

/**
 * Created by Ardit Podrimaj
 */

public class Hooks {

    @Before
    public void setup() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");

//        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
//        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//        capabilities.setCapability("marionette", true);
//        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);

//        WebDriver driver = new FirefoxDriver(options);
//        driver.get("http://localhost:3000/#/");
//        driver.navigate().to("http://localhost:3000/#/");
        getWebdriverManager().getWebdriver().manage().window().maximize();
        getWebdriverManager().getWebdriver().get("http://localhost:3000/#/");

        getWebdriverManager().getWebdriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();

    }
}