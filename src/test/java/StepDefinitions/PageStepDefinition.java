package StepDefinitions;
import Base.BaseTest;
import TestData.TestDataProvider;
import dev.failsafe.internal.util.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import Pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PageStepDefinition
{
    public BaseTest baseTest;
    public Page page;

    public PageStepDefinition()
    {
        baseTest = new BaseTest();
    }

    @Before()
    public void initialSetUp()
    {
        //initiate driver> launch application
        baseTest.TestSetUp();
        page = new Page(baseTest.driver, baseTest.wait);
    }

    //Navigates to 'Contact' page
    @Given("User navigates to the {string} of Jupiter Toys site")
    public void userNavigatesToTheContactsOfJupiterToysSite(String tabName) {
        page.NavigatetoSubPage(tabName);
    }
    @When("User clicks Submit button")
    public void userClicksSubmitButton() {
        page.ClickSubmit();
    }

    @Then("Alert message is displayed for mandatory fields")
    public void alertMessageIsDisplayedForMandatoryFields() {
        Assert.isTrue(page.AlertMessageCheck("errorExpected"), "Alert Message is not displayed for all the mandatory fields as expected" );
    }

    @When("User fills the mandatory fields {string}, {string}, {string}")
    public void userFillsTheMandatoryFieldsForenameEmailMessage(String foreName, String email, String message) {
            page.UserEntersMandatoryFields(foreName, email, message);
    }
    @Then("Verify no alert message is displayed")
    public void verifyNoAlertMessageIsDisplayed() {
        Assert.isTrue(page.AlertMessageCheck("NoAlert"),"Alert is displayed in the contact page -Fail");
    }

    @Then("Successful submission message is displayed")
    public void successfulSubmissionMessageIsDisplayed() {
        Assert.isTrue(page.AlertMessageCheck("Success"),"Success message is NOT displayed in the contact page");
    }

    @And("User selects {int} of Stuffed Frogs - {string}")
    public void userSelectsStuffedFrogsItem(int number, String item) {
        page.UserSelectsItems(number, item);
    }

    @And("User selects {int} of Fluffy Bunny - {string}")
    public void userSelectsFluffyBunnyItem(int number, String item) {
        page.UserSelectsItems(number, item);
    }

    @And("User selects {int} of Valentine Bear - {string}")
    public void userSelectsValentineBearItem(int number, String item) {
        page.UserSelectsItems(number, item);
    }

    @Then("Verify subtotal of {string}, {string} and {string} is displayed correctly")
    public void verifySubtotalOfItem(String item1, String item2, String item3) {
        Assert.isTrue(page.SubtotalCheckforEachItem(item1, TestDataProvider.subtotalTestData1()),"Subtotal is incorrect for the item" +item1);
        Assert.isTrue(page.SubtotalCheckforEachItem(item2, TestDataProvider.subtotalTestData2()),"Subtotal is incorrect for the item" +item2);
        Assert.isTrue(page.SubtotalCheckforEachItem(item3, TestDataProvider.subtotalTestData3()),"Subtotal is incorrect for the item" +item3);
    }

    @And("Verify price of {string}, {string} and {string} is displayed correctly")
    public void verifyPriceOfItem(String item1, String item2, String item3) {
        Assert.isTrue(page.PriceCheckForItem(item1, TestDataProvider.subtotalTestData1()),"Price is incorrect for the item" +item1);
        Assert.isTrue(page.PriceCheckForItem(item2, TestDataProvider.subtotalTestData2()),"Price is incorrect for the item" +item1);
        Assert.isTrue(page.PriceCheckForItem(item3, TestDataProvider.subtotalTestData3()),"Price is incorrect for the item" +item1);
    }

    @And("Verify total is the sum of all subtotals")
    public void verifyTotalIsTheSumOfAllSubtotals() {
        Assert.isTrue(page.TotalAmountCheck(TestDataProvider.subtotalTestData1(), TestDataProvider.subtotalTestData2(), TestDataProvider.subtotalTestData3()),"Total amount is incorrect for the cart items");
    }

    @After()
    public void driverQuit()
    {
        //close driver
        baseTest.closeDriver();
    }
}