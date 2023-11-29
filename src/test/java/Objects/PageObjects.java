package Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjects {
    WebDriver _driver;

    public PageObjects(WebDriver driver){
        _driver = driver;
    }

    // Tab in website
    public WebElement Tab(String tab)
    {
        WebElement Tab = _driver.findElement(By.xpath(PageObjects.Tab.replace("{0}", tab)));
        return Tab;
    }

    // Submit button in Contacts page
    public WebElement SubmitButton()
    {
        WebElement SubmitButton = _driver.findElement(By.xpath(PageObjects.SubmitButton));
        return SubmitButton;
    }

    // error alert message in Contact page
    public WebElement ErrorAlertInContactPage(String alertMsg)
    {
        WebElement ErrorAlertInContactPage = _driver.findElement(By.xpath(PageObjects.ErrorAlertInContactPage.replace("{0}", alertMsg)));
        return ErrorAlertInContactPage;
    }

    // Forename input text box in Contacts page
    public WebElement ForeNameInputTextBox()
    {
        WebElement ForeNameInput = _driver.findElement(By.id(PageObjects.ForeNameInput));
        return ForeNameInput;
    }

    // Email input text box in Contacts page
    public WebElement EmailInputTextBox()
    {
        WebElement EmailInput = _driver.findElement(By.id(PageObjects.EmailInput));
        return EmailInput;
    }

    // Email input text box in Contacts page
    public WebElement MessageInputTextBox()
    {
        WebElement MessageInput = _driver.findElement(By.id(PageObjects.MessageInput));
        return MessageInput;
    }

    // Alert Div in Contacts page
    public WebElement ErrorAlertGroupDiv()
    {
        WebElement ErrorAlertGroupDiv = _driver.findElement(By.xpath(PageObjects.ErrorAlertGroupDiv));
        return ErrorAlertGroupDiv;
    }

    // Submission progress indicator in Contacts page
    public WebElement SubmissionProgressBar()
    {
        WebElement SubmissionProgressBar = _driver.findElement(By.xpath(PageObjects.SubmissionProgressBar));
        return SubmissionProgressBar;
    }

    // Success alert in Contacts page
    public WebElement SuccessAlert()
    {
        WebElement SuccessAlert = _driver.findElement(By.xpath(PageObjects.SuccessAlert));
        return SuccessAlert;
    }

    // Buy button for a specific toy in Shop page
    public WebElement BuyButton(String item)
    {
        WebElement BuyButton = _driver.findElement(By.xpath(PageObjects.BuyButton.replace("{0}", item)));
        return BuyButton;
    }

    // Cart count holder
    public WebElement CartCountHolder()
    {
        WebElement CartCountHolder = _driver.findElement(By.xpath(PageObjects.CartCountHolder));
        return CartCountHolder;
    }

    // Subtotal value holder of the specific item
    public WebElement ItemSubtotalHolder(String item)
    {
        WebElement ItemSubtotalHolder = _driver.findElement(By.xpath(PageObjects.ItemSubtotalHolder.replace("{0}", item)));
        return ItemSubtotalHolder;
    }

    // Price value holder of the specific item
    public WebElement ItemPriceHolder(String item)
    {
        WebElement ItemPriceHolder = _driver.findElement(By.xpath(PageObjects.ItemPriceHolder.replace("{0}", item)));
        return ItemPriceHolder;
    }

    // Total amount holder in the cart
    public WebElement TotalAmountHolder()
    {
        WebElement TotalAmountHolder = _driver.findElement(By.xpath(PageObjects.TotalAmountHolder));
        return TotalAmountHolder;
    }

    public static String SubmitButton = "//a[text()='Submit']";
    public static String ErrorAlertInContactPage = "//span[text()='{0}']";
    public static String Tab = "//a[@href='#/{0}']";
    public static String ForeNameInput = "forename";
    public static String EmailInput = "email";
    public static String MessageInput = "message";
    public static String ErrorAlertGroupDiv = "//div[@class='control-group error']";
    public static String SuccessAlert = "//div[@class='alert alert-success']";
    public static String SubmissionProgressBar = "//div[contains(@class,'popup modal') and @aria-hidden='false']";
    public static String BuyButton = "//h4[text()='{0}']/parent::div//a";
    public static String CartCountHolder = "//a[@href='#/cart']/span";
    public static String ItemSubtotalHolder = "//td[contains(text(),'{0}')]/parent::tr//td[4]";
    public static String ItemPriceHolder = "//td[contains(text(),'{0}')]/parent::tr//td[2]";
    public static String TotalAmountHolder = "//tfoot//strong";
}


