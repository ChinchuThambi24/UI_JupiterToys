package CommonUtilities;
import Enums.LocatorType;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;
import org.openqa.selenium.TakesScreenshot;
import java.io.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import java.lang.*;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.function.Function;

public class CommonUtilities {

    //Method to get the today's date
    public static String GetDate()
    {   Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
        String todayAsString = dateFormat.format(today);
        System.out.println(todayAsString);
        return todayAsString;   // returns 23/6/2023
    }

    //Method to capture the screenshot
    public static void CaptureScreenShot(String fileName, WebDriver driver)
    {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            fileName = fileName +"_"+GetDate();
            try {
                FileUtils.copyFile(screenshot, new File("bin/Debug/screenshots", fileName+".png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /// <summary>
    /// Method to scroll to an element in the DOM if needed
    /// </summary>
    /// <param name="driver"></param>
    /// <param name="element"></param>
    public static void ScrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static Boolean HasElement (WebDriver driver, String Locator, LocatorType Type)
    {
        boolean hasElement = false;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        switch (Type.toString().toLowerCase())
        {
            case "class":
                if (driver.findElements(By.className(Locator)).size() > 0)
                {hasElement = true;}
                else{ hasElement = false;}
                break;
            case "xpath":
                if ((long) driver.findElements(By.xpath(Locator)).size() > 0)
                {hasElement = true;}
                else{hasElement = false;}
                break;
            case "id":
                if (driver.findElements(By.id(Locator)).size() > 0)
                {hasElement = true;}
                else
                {hasElement = false;}
                break;
        }
        return hasElement;
    }

    public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Duration timeoutInSecs){
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSecs);
        webDriverWait.withTimeout(timeoutInSecs);
        try{
            webDriverWait.until(waitCondition);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public  static void untilPageLoadComplete(WebDriver driver, Duration timeoutInSeconds)
    {
        until(driver, (d) ->
        {
            Boolean isPageLoaded = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            if (!isPageLoaded) System.out.println("Document is loading");
            return isPageLoaded;
        }, timeoutInSeconds);
    }
}
