package pages;


import objectManager.BasePage;
import com.vimalselvam.cucumber.listener.Reporter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;


public class SearchPage extends BasePage {

    WebDriver driver;

    private final String idSearchBox = "searchbox";
    private final String idSearchSubmit = "searchsubmit";
    private final String idTable = "//table[@class='computers zebra-striped']";
    private final String idPaginationPrevious = "//li[contains(@class,'prev')]";
    private final String idPaginationCurrent = "//li[@class='current']";
    private final String idPaginationNext = "//li[@class='next']";

    By searchBox = getLocator(idSearchBox, BY_TYPE.BY_ID);
    By searchSubmit = getLocator(idSearchSubmit, BasePage.BY_TYPE.BY_ID);
    By tableResult = getLocator(idTable, BY_TYPE.BY_XPATH);
    By previous = getLocator(idPaginationPrevious, BY_TYPE.BY_XPATH);
    By current = getLocator(idPaginationCurrent, BY_TYPE.BY_XPATH);
    By next = getLocator(idPaginationNext, BY_TYPE.BY_XPATH);

    AddAComputer addAComputer = new AddAComputer(driver);

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void performSearchWithComputerName(String computerName) {
        addExplicitWait(driver, searchBox, "visibility", 5);
        type(driver, searchBox, computerName, "Search box");
        click(driver, searchSubmit);
    }

    public void validateSearchResult(String computerName, String date1, String date2, String company) {
        List<String> searchValues = getContentFromSearchResultTable(driver, tableResult, "Table content");
        List<String> originalValues = Arrays.asList(computerName, date1, date2, company);
        for (int i = 0; i <= 3; i++) {
            if (!searchValues.get(i).equals(originalValues.get(i))) {
                Assert.fail("Original values does not match with search results");
                Reporter.addStepLog("Mismatch with original values and search result values");
            }
        }
    }

    public void validateSearchPageContents() {
        failIfElementNotVisible(driver, searchBox, "Search box");
        failIfElementNotVisible(driver, searchSubmit, "Submit Search button");
        failIfElementNotVisible(driver, tableResult, "table");
        failIfElementNotVisible(driver, previous, "Previous link");
        failIfElementNotVisible(driver, current, "Current link");
        failIfElementNotVisible(driver, next, "Next link");
    }

    public void openSearchResult(String computerName) {
        addExplicitWait(driver, By.linkText(computerName), "visibility", 3);
        click(driver, By.linkText(computerName));
    }

    public void validateContentAfterOpeningSearchResult(String computername, String date1, String date2, String companyName) {
        AssertText(driver, computername);
        AssertText(driver, date1);
        AssertText(driver, date2);
        AssertText(driver, companyName);
        failIfElementNotVisible(driver, addAComputer.btnCancel, "Cancel button");
        failIfElementNotVisible(driver, addAComputer.btnAdd, "Next link");
    }

    public void getSearchResult(String computerName) {
        List<String> searchValues = getContentFromSearchResultTable(driver, tableResult, "Table content");
        if (!searchValues.get(0).equals(computerName)) {
            Assert.fail("Original values does not match with search results");
            Reporter.addStepLog("Mismatch with original values and search result values");
        }
    }
}
