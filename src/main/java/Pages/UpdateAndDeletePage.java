package Pages;

import ObjectManager.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class UpdateAndDeletePage extends BasePage {
    WebDriver driver;

    public UpdateAndDeletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    AddAComputer addAComputer = new AddAComputer(driver);

    private final String idFirstComputer = "//*[@id='main']/table/tbody/tr[1]/td[1]/a";
    private final String idDeleteComputer = "//*[@class='btn danger']";
    private final String idSaveComputer = "//*[@class='btn primary']";
    static String computerName;

    By saveComputerBtn = getLocator(idSaveComputer, BY_TYPE.BY_XPATH);
    By linkFirstComputer = getLocator(idFirstComputer, BY_TYPE.BY_XPATH);
    By btnDeleteComputer = getLocator(idDeleteComputer, BY_TYPE.BY_XPATH);


    public void selectFirstComputerToUpdate() {
        click(driver, linkFirstComputer);
    }

    public void updateComputer(String updateDetails) {
        addExplicitWait(driver, addAComputer.computerDiscontinued, "visibility", 3);
        type(driver, addAComputer.computerCompany, updateDetails, "Discount continued date");
        click(driver, saveComputerBtn);
    }

    public void validateEditComputerContents() {
        AssertText(driver, "Edit computer");
        failIfElementNotVisible(driver, btnDeleteComputer, "Delete computer button");
        failIfElementNotVisible(driver, saveComputerBtn, "Save computer button");
    }

    public void validateUpdatedContents(String computer) {
        AssertText(driver, "Computer " + computer + " has been updated");
    }

    public void validateDeletedComputer() {
        AssertText(driver, "Computer has been deleted");
    }

    public void deleteComputer() {
        click(driver, btnDeleteComputer);
    }

    public String getComputerName() {
        computerName = getText(driver, addAComputer.computerName);
        return computerName;
    }


}
