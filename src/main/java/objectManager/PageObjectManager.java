package objectManager;

import pages.AddAComputer;
import pages.SearchPage;
import pages.UpdateAndDeletePage;
import org.openqa.selenium.WebDriver;


public class PageObjectManager {

    private AddAComputer addAComputer;
    private WebDriver driver;
    private SearchPage searchPage;
    private UpdateAndDeletePage updateAndDeletePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public AddAComputer getAddAComputer() {
        return (addAComputer == null) ? addAComputer = new AddAComputer(driver) : addAComputer;

    }

    public SearchPage getSearchPage() {
        return (searchPage == null) ? searchPage = new SearchPage(driver) : searchPage;

    }

    public UpdateAndDeletePage getUpdateAndDeletePage() {
        return (updateAndDeletePage == null) ? updateAndDeletePage = new UpdateAndDeletePage(driver) : updateAndDeletePage;

    }

}
