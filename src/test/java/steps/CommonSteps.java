package steps;

import objectManager.BasePage;
import cucumber.TestCaseContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class CommonSteps {
    TestCaseContext testContext;
    BasePage baseUtils = new BasePage();

    public CommonSteps(TestCaseContext context) {
        testContext = context;
    }


    @Given("^I open web page$")
    public void i_open_web_page() {
        baseUtils.launchBrowser(testContext.getBasePage().getDriver());
    }

    @Then("^I close the browser$")
    public void i_close_the_browser() {
        baseUtils.closeDriver(testContext.getBasePage().getDriver());
    }


}
