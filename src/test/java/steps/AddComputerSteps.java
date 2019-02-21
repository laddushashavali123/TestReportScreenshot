package steps;


import pages.AddAComputer;
import cucumber.TestCaseContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddComputerSteps {

    TestCaseContext testContext;
    AddAComputer addAComputer;

    public AddComputerSteps(TestCaseContext context) {
        testContext = context;
        addAComputer = testContext.getPageObjectManager().getAddAComputer();
    }

    @And("^I click on 'Add a new computer'$")
    public void i_click_on_Add_a_new_computer() {
        addAComputer.clickOnAddAComputer();
    }

    @Then("^I should see 'Add a new computer' page contents$")
    public void i_should_see_Add_a_new_computer_page_contents() {
        addAComputer.validateAddComputerPageContents();
    }

    @When("^I enter the computer name as \"([^\"]*)\"$")
    public void i_enter_the_computer_name_as(String arg1) {
        addAComputer.enterComputerName(arg1);
    }

    @When("^I enter the Introduced date as \"([^\"]*)\"$")
    public void i_enter_the_Introduced_date_as(String arg1) {
        addAComputer.enterIntroducedDate(arg1);
    }

    @When("^I enter the Discontinued date as \"([^\"]*)\"$")
    public void i_enter_the_Discontinued_date_as(String arg1) {
        addAComputer.enterDiscontinuedDate(arg1);
    }

    @When("^I select the company as \"([^\"]*)\"$")
    public void i_select_the_company_as(String arg1) {
        addAComputer.enterCompanyName(arg1);
    }

    @When("^I click on 'Create this computer'$")
    public void i_click_on_Create_this_computer() {
        addAComputer.submitComputerAddition();
    }

    @Then("^I should see the computer added successfully as \"([^\"]*)\"$")
    public void i_should_see_the_computer_added_successfully_as(String arg1) {
        addAComputer.validateSucessfulAddition(arg1);
    }

    @When("^I click on cancel button$")
    public void i_click_on_cancel_button() {
        addAComputer.cancelComputerAddition();
    }

    @Then("^I should see homepage$")
    public void i_should_see_homepage() {
        addAComputer.validateInHomePage();
    }

    @Then("^I should be in Add a computer page$")
    public void i_should_be_in_Add_a_computer_page() {
        addAComputer.validateInAddaComputerPage();
    }
}
