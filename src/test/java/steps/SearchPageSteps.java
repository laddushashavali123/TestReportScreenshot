package steps;

import pages.SearchPage;
import cucumber.TestCaseContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SearchPageSteps {
    TestCaseContext testContext;
    SearchPage searchPage;

    public SearchPageSteps(TestCaseContext context) {
        testContext = context;
        searchPage = testContext.getPageObjectManager().getSearchPage();
    }

    @Then("^I should see 'Search' page$")
    public void i_should_see_Search_page() {
        searchPage.validateSearchPageContents();
    }

    @Then("^I should validate the 'Search' Page contents$")
    public void i_should_validate_the_Search_Page_contents() {
        searchPage.validateSearchPageContents();
    }

    @Given("^I search for a \"([^\"]*)\"$")
    public void i_search_for_a(String arg1) {
        searchPage.performSearchWithComputerName(arg1);
    }

    @Then("^I should see the search results with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_should_see_the_search_results_with_and_and_and(String arg1, String arg2, String arg3, String arg4)  {
        searchPage.validateSearchResult(arg1, arg2, arg3, arg4);
    }

    @Then("^I click on search result as \"([^\"]*)\"$")
    public void i_click_on_search_result_as(String arg1) {
        searchPage.openSearchResult(arg1);
    }

    @Then("^I should validate the computer contents as \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_should_validate_the_computer_contents_as_and_and_and(String arg1, String arg2, String arg3, String arg4) {
        searchPage.validateContentAfterOpeningSearchResult(arg1, arg2, arg3, arg4);
    }

    @Then("^I should see the search results as \"([^\"]*)\"$")
    public void i_should_see_the_search_results_as(String arg1) {
       searchPage.getSearchResult(arg1);
    }
}
