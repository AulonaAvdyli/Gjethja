package runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features ="classpath:features/ProviderRegister.feature",
        glue = "classpath:definitions")
public class ProviderRegisterRunner {
}
