package Runner;

import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.PropertyUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Features",
        glue = {"Steps"},
        plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:"},
        tags = {"@Testing"}
)
public class TestRunner {
    static PropertyUtils appProperty = new PropertyUtils();
    static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static String formattedTime = timeFormat.format(new Date()).replace(":", "_");
    static String reportPath = appProperty.getPropertyValue("reportDirectory");


    @BeforeClass
    public static void setup() {
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath(reportPath + "/report_" + formattedTime + ".html");
    }

    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File(appProperty.getPropertyValue("reportConfigPath")));
    }

}
