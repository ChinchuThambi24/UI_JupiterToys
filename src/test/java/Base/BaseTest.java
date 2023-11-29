package Base;
import java.io.IOException;
import java.time.Duration;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
@RunWith(Cucumber.class)
public class BaseTest
{
    public WebDriver driver;
    public WebDriverWait wait;
    public ChromeOptions chromeOptions;
    public EdgeOptions edgeOptions;
    public FirefoxOptions firefoxOptions;
    public static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    String browser;

    public BaseTest()  {
        try {
            browser = AppConfigReader.GetBrowser();
        }
        catch(Exception e){}
    }

    public WebDriver getDriver() throws IOException {
        if(driver == null) driver = createDriver();
        return driver;
    }

    //create driver
    public WebDriver createDriver() throws IOException {
        if(browser.equals("firefox"))
        {
            driver = new FirefoxDriver();
        }
        if (browser.equals("chrome"))
        {
            System.setProperty(CHROME_DRIVER_PROPERTY, AppConfigReader.GetDriverPath());
            driver = new ChromeDriver();
            chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox");
            //set resolution
            chromeOptions.addArguments("window-size=1024,768");
        }
        if (browser.equals("edge"))
        {
            driver = new EdgeDriver();
        }

        if(AppConfigReader.GetBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return driver;
    }

    //Test Initialisation
    public void TestSetUp()  {
        try {
            //setting up driver
            getDriver();
            //launching application
            driver.navigate().to(AppConfigReader.GetUrl());
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            driver.navigate().refresh();
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Test Cleanup
    public void closeDriver() {
        try {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
