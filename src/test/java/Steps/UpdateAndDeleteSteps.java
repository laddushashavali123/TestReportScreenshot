package Steps;

import Pages.UpdateAndDeletePage;
import cucumber.TestCaseContext;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateAndDeleteSteps {

    TestCaseContext testContext;
    UpdateAndDeletePage updateAndDelete;

    public UpdateAndDeleteSteps(TestCaseContext context) {
        testContext = context;
        updateAndDelete = testContext.getPageObjectManager().getUpdateAndDeletePage();
    }

    @Then("^I should see edit computer contents$")
    public void i_should_see_edit_computer_contents() throws Throwable {
        updateAndDelete.validateEditComputerContents();
    }


    @Then("^I click on Delete the computer button$")
    public void i_click_on_Delete_the_computer_button() throws Throwable {
        updateAndDelete.deleteComputer();
    }

    @Then("^I click on search result$")
    public void i_click_on_search_result() throws Throwable {
        updateAndDelete.selectFirstComputerToUpdate();
    }


    @Then("^I get the computername$")
    public void i_get_the_computername() throws Throwable {
        updateAndDelete.getComputerName();
    }

    @When("^I update the computer details as \"([^\"]*)\"$")
    public void i_update_the_computer_details_as(String arg1) {
        updateAndDelete.updateComputer(arg1);
    }

    @Then("^I should see the details getting saved as \"([^\"]*)\"$")
    public void i_should_see_the_details_getting_saved_as(String arg1) {
        updateAndDelete.validateUpdatedContents(arg1);
    }

    @Then("^I should see the details getting deleted as \"([^\"]*)\"$")
    public void i_should_see_the_details_getting_deleted_as(String arg1) {

        updateAndDelete.validateDeletedComputer();
    }
}
