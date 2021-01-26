package runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features ="classpath:features/SeekerUpdate.feature",
        glue = "classpath:definitions")
public class SeekerUpdateRunner {
}
