package Pages;
import Base.BaseTest;
import CommonUtilities.CommonUtilities;
import Enums.LocatorType;
import TestData.TestDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import java.lang.*;
import java.time.Duration;
import java.util.List;

import Objects.*;

public class Page {
    WebDriver driver;
    WebDriverWait wait;
    public BaseTest baseTest;
    PageObjects pageObjects;
    JavascriptExecutor js;


    public Page(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        pageObjects = new PageObjects(this.driver);
        js = (JavascriptExecutor) this.driver;
    }

    // Method to navigate to specific tab items
    public boolean NavigatetoSubPage(String tabName){
        js.executeScript("arguments[0].click();", pageObjects.Tab(tabName));
        CommonUtilities.untilPageLoadComplete(driver, Duration.ofMillis(7000));
        //Verifies the title now contains
        wait.until(ExpectedConditions.urlContains(tabName));
        if (driver.getCurrentUrl().contains(tabName)) {
            System.out.println("User landed on " +tabName+ " page");
            CommonUtilities.CaptureScreenShot("User lands on " +tabName+ " page", this.driver);
            return true;
        }
        else{
            System.out.println("User failed to land on " +tabName+ " page");
            CommonUtilities.CaptureScreenShot("User failed to land on "+tabName+" page", this.driver);
            return false;
        }
    }

    // Method to click 'Add New Contact' button
    public void ClickSubmit() {
        CommonUtilities.ScrollIntoView(driver, pageObjects.SubmitButton());
        pageObjects.SubmitButton().click();
    }

    //Method to verify the Success message or mandatory alert is displayed
    public Boolean AlertMessageCheck(String alert) {
        Boolean result =true;
        switch (alert) {
            case "errorExpected":
                // verifying alert message is displayed for Forename, Email and Message
                Boolean validationerror = CommonUtilities.HasElement(driver, pageObjects.ErrorAlertInContactPage.replace("{0}", "Forename is required"), LocatorType.XPATH);
                validationerror &= CommonUtilities.HasElement(driver, pageObjects.ErrorAlertInContactPage.replace("{0}", "Email is required"), LocatorType.XPATH);
                validationerror &= CommonUtilities.HasElement(driver, pageObjects.ErrorAlertInContactPage.replace("{0}", "Message is required"), LocatorType.XPATH);
                if (validationerror) {
                    System.out.println("==================Alert message displayed for all the mandatory fields as expected==================");
                    CommonUtilities.CaptureScreenShot("Alerts in Contact page", driver);
                    result &= true;
                } else {
                    System.out.println("==================Alert message is NOT displayed for all the mandatory fields as expected==================");
                    CommonUtilities.CaptureScreenShot("NO alerts in Contact page", driver);
                    result &= false;
                }
                break;
            case "Success":
                wait.until(ExpectedConditions.invisibilityOf(pageObjects.SubmissionProgressBar()));
                Boolean isAlert = CommonUtilities.HasElement(driver, pageObjects.SuccessAlert, LocatorType.XPATH);
                if(isAlert)
                {
                    System.out.println("==================Success alert message is displayed as expected. Submission successful==================");
                    CommonUtilities.CaptureScreenShot("Submission successful", driver);
                    result &= true;
                }
                else
                {
                    result &= false;
                    System.out.println("==================Success alert message is NOT displayed as expected. Submission unsuccessful==================");
                    CommonUtilities.CaptureScreenShot("Submission unsuccessful", driver);
                }
                break;
            case "NoAlert":
                int count = (int) driver.findElements(By.xpath(pageObjects.ErrorAlertGroupDiv)).stream().count();
                if(count == 0)
                {
                    result &= true;
                    System.out.println("==================No alert in Contact page==================");
                    CommonUtilities.CaptureScreenShot("No alerts in Contact page", driver);
                }
                else{
                    result &= false;
                    System.out.println("==================No alert in Contact page==================");
                    CommonUtilities.CaptureScreenShot("Alert in Contact page", driver);
                }
                break;
        }
        return result;
    }

    // Method to add mandatory fields on Contact page
    public void UserEntersMandatoryFields(String foreName, String email, String message)
    {
        pageObjects.ForeNameInputTextBox().sendKeys(foreName);
        pageObjects.EmailInputTextBox().sendKeys(email);
        pageObjects.MessageInputTextBox().sendKeys(message);
    }

    // Method to select the specific item, specific no.of times
    public boolean UserSelectsItems(int noOfItems, String item)
    {
        wait.until(ExpectedConditions.visibilityOf(pageObjects.CartCountHolder()));
        wait.until(ExpectedConditions.visibilityOf(pageObjects.BuyButton(item)));
        String cartCount = pageObjects.CartCountHolder().getText();
        int initialCount = Integer.parseInt(cartCount);
        for (int i = 1; i <= noOfItems; i++)
        {
            pageObjects.BuyButton(item).click();
            cartCount = pageObjects.CartCountHolder().getText();
            int count = Integer.parseInt(cartCount);
            System.out.println("No.of " +item+ " added to the cart is now: " +count);
        }
        if(initialCount < (Integer.parseInt(pageObjects.CartCountHolder().getText()))) {
            System.out.println("==================Item- " +item+ " added to the cart==================");
            CommonUtilities.CaptureScreenShot("Item - " +item+ " added to cart as expected", driver);
            return true;
        }
        else
        {return  false;}
    }

    //Method to calculate and check the subtotal of specific item
    public boolean SubtotalCheckforEachItem(String item, List<Double> data){
        double subtotal = data.get(2);
        if(pageObjects.ItemSubtotalHolder(item).getText().contains(subtotal+""))
        {
            System.out.println("==================Subtotal of the " +item+ " is correctly calculated to " +subtotal+ "==================");
            CommonUtilities.CaptureScreenShot("Subtotal of item " +item, driver);
            return true;
        }
        else {
            System.out.println("==================Subtotal of the " +item+ " is incorrectly calculated ==================");
            CommonUtilities.CaptureScreenShot("Incorrect subtotal of item " +item , driver);
            return false;
        }
    }

    //Method to verify the price of each item
    public boolean PriceCheckForItem(String item, List<Double> data) {
        double price = data.get(1);
        if(pageObjects.ItemPriceHolder(item).getText().contains(price+""))
        {
            System.out.println("==================Price of the " +item+ " is correctly displayed as " +price+ "==================");
            CommonUtilities.CaptureScreenShot("Price of item " +item, driver);
            return true;
        }
        else {
            System.out.println("==================Price of the " +item+ " is incorrectly displayed ==================");
            CommonUtilities.CaptureScreenShot("Incorrect Price of item " +item , driver);
            return false;
        }
    }

    //Method to verify the total amount of all items in cart
    public boolean TotalAmountCheck(List<Double> data1, List<Double> data2, List<Double> data3)
    {
        double total = data1.get(2) + data2.get(2) + data3.get(2);
        if(pageObjects.TotalAmountHolder().getText().contains(total+""))
        {
            System.out.println("==================Total amount is calculated correctly in the cart==================");
            CommonUtilities.CaptureScreenShot("Correct Total amount in cart", driver);
            return true;
        }
        else {
            System.out.println("==================Total amount is calculated incorrectly in the cart ==================");
            CommonUtilities.CaptureScreenShot("Incorrect Total amount in cart" , driver);
            return false;
        }
    }
}