package Pages;

import ObjectManager.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AddAComputer extends BasePage {

    WebDriver driver;

    private final String idAddAComputer = "add";
    private final String title_AddAComputer = "//*[@id='main']/h1";
    private final String idComputerName = "name";
    private final String idDate = "introduced";
    private final String idDiscontinued = "discontinued";
    private final String idCompany = "company";
    private final String idCreateComputer = "//*[@id='main']/form/div/input";
    private final String idCancel = "Cancel";
    private final String idFixError = "//div[@class='clearfix error']";


    public AddAComputer(WebDriver driver) {
        this.driver = driver;
    }

    By titleAddaComputer = getLocator(title_AddAComputer, BY_TYPE.BY_XPATH);
    By computerName = getLocator(idComputerName, BY_TYPE.BY_ID);
    By computerDate = getLocator(idDate, BY_TYPE.BY_ID);
    By computerDiscontinued = getLocator(idDiscontinued, BY_TYPE.BY_ID);
    By computerCompany = getLocator(idCompany, BY_TYPE.BY_ID);
    By btnAdd = getLocator(idCreateComputer, BY_TYPE.BY_XPATH);
    By btnCancel = getLocator(idCancel, BY_TYPE.BY_LINKTEXT);
    By fixError = getLocator(idFixError, BY_TYPE.BY_XPATH);


    public void clickOnAddAComputer() {
        By addAComputer = getLocator(idAddAComputer, BY_TYPE.BY_ID);
        addExplicitWait(driver, addAComputer, "visibility", 5);
        click(driver, addAComputer);
    }


    public void validateAddComputerPageContents() {
        addExplicitWait(driver, titleAddaComputer, "visibility", 5);
        AssertText(driver, "Add a computer");
        failIfElementNotVisible(driver, computerName, "Computer Name");
        failIfElementNotVisible(driver, computerDate, "Introduced date");
        failIfElementNotVisible(driver, computerDiscontinued, "Discontinued date");
        failIfElementNotVisible(driver, computerCompany, "Company Name");
        failIfElementNotVisible(driver, btnAdd, "Add computer button");
        failIfElementNotVisible(driver, btnCancel, "Cancel button");
    }

    public void enterComputerName(String computerNameData) {
        type(driver, computerName, computerNameData, "Computer Name");
    }

    public void enterIntroducedDate(String introducedDateData) {
        type(driver, computerDate, introducedDateData, "Introduced Date");
    }

    public void enterDiscontinuedDate(String discontinuedData) {
        type(driver, computerDiscontinued, discontinuedData, "Discontinued Date");
    }

    public void enterCompanyName(String companyNameData) {
        selectDropDownByVisibleText(driver, computerCompany, companyNameData);
    }

    public void submitComputerAddition() {
        click(driver, btnAdd);
        addExplicitWait(driver, titleAddaComputer, "visibility", 5);
    }

    public void validateSucessfulAddition(String computername) {
        System.out.println("Computer " + computername + " has been created");
        AssertText(driver, "Computer " + computername + " has been created");
    }

    public void cancelComputerAddition() {
        click(driver, btnCancel);
        addExplicitWait(driver, titleAddaComputer, "visibility", 5);
    }

    public void validateInHomePage() {
        failIfElementNotVisible(driver, titleAddaComputer, "Add a new computer button in home page");
    }

    public void validateInAddaComputerPage() {
        failIfElementNotVisible(driver, fixError, "Fix error message in add a computer page");
        failIfElementNotVisible(driver, computerDate, "Introduced date");
    }
}
