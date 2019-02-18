
### Selenium-Cucumber-Maven-Extent Reporting Setup Guide   

### Features

* All scripts written with Selenium,Java and Cucumber feature files.
* Page Object design pattern implementation.
* Extensive hooks implemented for Before, AfterScenarios etc.
* Screenshots on failure feature scenarios.


### To Get Started

#### Pre-requisites
1.Maven and Java Installed 

2.Chrome or Firefox browsers installed.

3.Eclipse or Intellij IDE installed

#### Setup Scripts
* Clone the repository into a folder
```
* Set up the folder path for Reporting as specified in the config.properties file as "reportDirectory" path.
```
* Place the log4j properties file and extentconfig.xml in the directory path mentioned in the config properties fiile
```
```
* Create folder named screenshots inside the "reportDirectory" path
```
* Comment the line for FirefoxBinary option in BasePage.java if not needed(if firefox.exe is present in C drive and not in default location) to execute in firefox browser
```
* Install cucumber plugin in eclipse IDE using the following steps
Launch Eclipse and navigate to 'Help->Install New Software'.
Click on 'Add' button
Enter any name e.g. 'Cucumber' and type 'http://cucumber.github.com/cucumber-eclipse/update-site' as location.
Click on OK.
'Cucumber Eclipse Plugin' option will appear in the available software list.
Select the check box against it and click on 'Next' button.
Read the terms of license agreement and click on 'I accept the terms of the license agreement” if you agree with the license terms.
Click on 'Finish' button.
```
#### Run Scripts

* First step is to download the maven dependencies,Right Click on the project choose->maven and then choose the below goals

```
mvn clean 
mvn install
``` 

* Right Click Test Runner after configuring cucumber and select 'Run as Junit test'
```

#### Writing Features
```
Feature: Add a computer feature functionality

Scenario: Validate Add a computer page contents
    Given I open web page
    And I click on 'Add a new computer'
    Then I should see 'Add a new computer' page contents
```
#### Writing Step Definitions
    
```
import Pages.SearchPage;
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
```

#### Writing Page Objects
```
    private final String title_AddAComputer = "//*[@id='main']/h1";
    private final String idComputerName = "name";
    private final String idDate = "introduced";
    private final String idDiscontinued = "discontinued";
  

    By titleAddaComputer = getLocator(title_AddAComputer, BY_TYPE.BY_XPATH);
    By computerName = getLocator(idComputerName, BY_TYPE.BY_ID);
    By computerDate = getLocator(idDate, BY_TYPE.BY_ID);
    By computerDiscontinued = getLocator(idDiscontinued, BY_TYPE.BY_ID);
  
```
#### Cucumber Hooks
Following method takes screenshot on failure of each scenario
```
 if (scenario.isFailed()) {
                String screenshotName = scenario.getName().replaceAll(" ", "_");

                //This takes a screenshot from the driver at save it to the specified location
                File sourcePath = ((TakesScreenshot) testContext.getBasePage().getDriver()).getScreenshotAs(OutputType.FILE);

                //Building up the destination path for the screenshot to save
                //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
                File destinationPath = new File(reportPath + "/screenshots/" + screenshotName + "_" + formattedTime + ".png");

                //Copy taken screenshot from source location to destination location
                Files.copy(sourcePath, destinationPath);

                //This attach the specified screenshot to the test
                Reporter.addScreenCaptureFromPath("./screenshots/" + screenshotName + "_" + formattedTime + ".png");
            }
```
#### CucumberOpts Tags
Following configuration shows to call specific tags from feature files
```
@CucumberOptions(
        features = "Features",
        glue = {"Steps"},
        plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:"},
        tags = {"@Testing"}
),
```
#### HTML Reports
Currently this project has been integrated with [Extent reporter](http://www.vimalselvam.com/cucumber-extent-reporter/), which is generated in the `reports` folder when you run `TestRunner`.
They can be customized according to the needs.
