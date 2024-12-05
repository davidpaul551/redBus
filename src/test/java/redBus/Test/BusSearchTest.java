package redBus.Test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import redBus.Base.baseClass;
import redBus.PageObjectModel.busSearchPage;

import java.time.Duration;

public class BusSearchTest extends baseClass {
    busSearchPage searchPage;

    @Test(priority = 1)
    public void validateInputFieldsVisibility() {
        test = extent.createTest("Validate Input Fields Visibility", "Verifies if the 'From' and 'To' fields are displayed");
        busSearchPage searchPage = new busSearchPage(driver);

        searchPage.clickBusTicketButton();

        Assert.assertTrue(driver.findElement(searchPage.fromAddress).isDisplayed(), "'From' address field is not displayed");
        test.pass("'From' address field is displayed");

        Assert.assertTrue(driver.findElement(searchPage.toAddress).isDisplayed(), "'To' address field is not displayed");
        test.pass("'To' address field is displayed");
    }
/*
    @Test(priority = 2)
    public void enterValidLocations() {
        test = extent.createTest("Enter Valid Locations", "Verifies entering valid 'From' and 'To' locations");
        busSearchPage searchPage = new busSearchPage(driver);

        searchPage.enterFromAddress("Chennai");
        test.pass("Entered valid 'From' location");

        searchPage.selectSecondFromItem();
        test.pass("Selected a valid option for 'From' location");

        searchPage.enterToAddress("Hyderabad");
        test.pass("Entered valid 'To' location");

        searchPage.selectSecondToItem();
        test.pass("Selected a valid option for 'To' location");
    }

 */

    @Test(priority = 3)
    public void verifySearchButtonEnabledWithValidData() throws InterruptedException {
        test = extent.createTest("Verify Search Button Enabled", "Ensures the 'Search' button is enabled with valid data");
        busSearchPage searchPage = new busSearchPage(driver);

        // Pre-fill valid locations and date
        searchPage.enterFromAddress("Chennai");
        searchPage.selectSecondFromItem();

        searchPage.enterToAddress("Hyderabad");
        searchPage.selectSecondToItem();
        //js.executeScript("window.scrollBy(0, 100);");


        //searchPage.clickDateButton();
        searchPage.clickNextMonthButton();
        searchPage.selectDate();
        js.executeScript("window.scrollBy(0, 100);");

        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(searchPage.searchBusesButton).isEnabled(), "'Search' button is not enabled with valid data");
        test.pass("'Search' button is enabled with valid 'From', 'To', and Date");
    }

    @Test(priority = 4)
    public void verifySearchButtonDisabledWithInvalidData() {
        test = extent.createTest("Verify Search Button Disabled", "Ensures the 'Search' button remains disabled with invalid data");
        busSearchPage searchPage = new busSearchPage(driver);

        // Test invalid 'To' location
        searchPage.enterFromAddress("chennai");
        searchPage.selectSecondFromItem();

        searchPage.enterToAddress(" ");
        boolean isSearchButtonEnabled = driver.findElement(searchPage.searchBusesButton).isEnabled();

        if (isSearchButtonEnabled) {
            test.fail("'Search' button is enabled with invalid 'To' location");
        } else {
            test.pass("'Search' button remains disabled with invalid 'To' location");
        }
        }
    @Test(priority = 5)
    public void testNoDateSelected() {
        test = extent.createTest("Verify Search Button Disabled for no date", "Ensures the 'Search' button remains disabled with no date selected");

        busSearchPage searchPage = new busSearchPage(driver);

        searchPage.enterFromAddress("Chennai");
        searchPage.selectSecondFromItem();

        searchPage.enterToAddress("Hyderabad");
        searchPage.selectSecondToItem();


        boolean isSearchButtonEnabled = driver.findElement(searchPage.searchBusesButton).isEnabled();

        if (isSearchButtonEnabled) {
            test.fail("'Search' button is enabled with no date selected");
        } else {
            test.pass("'Search' button remains disabled with no date selected");
        } }


    @Test(priority = 6)
    public void validateDatePicker() throws InterruptedException {
        busSearchPage searchPage = new busSearchPage(driver);

        test = extent.createTest("Validate Date Picker", "Ensures the date picker is displayed properly");

        searchPage.clickDateButton();
        Assert.assertTrue(driver.findElement(searchPage.calender).isDisplayed(), "Date picker is not displayed");
        test.pass("Date picker is displayed properly");

        searchPage.clickNextMonthButton();
        searchPage.selectDate();
        test.pass("Date selected successfully");
    }

    @Test(priority = 7)
    public void verifySearchResultsWithInvalidLocations() throws InterruptedException {
        busSearchPage searchPage = new busSearchPage(driver);

        test = extent.createTest("Verify Search Results with Invalid Locations", "Ensures no results are displayed for invalid locations");

        searchPage.enterFromAddress("chennai");
        searchPage.enterToAddress("england");
        searchPage.clickDateButton();
        searchPage.clickNextMonthButton();
        searchPage.clickDateButton();
        searchPage.selectDate();


        searchPage.clickSearchBusesButton();
        Thread.sleep(3000);

        boolean noResultsDisplayed = searchPage.noResultsDisplayed(); // Replace with actual validation logic
        if (noResultsDisplayed) {
            String screenshotPath = captureScreenshot("verifySearchResultsWithInvalidLocations");
            test.pass("No results displayed for invalid locations").addScreenCaptureFromPath(screenshotPath);
        } else {
            test.fail("Bus results displayed for invalid locations");
        }}

    @Test(priority = 8)
    public void validateSearchResultsWithValidLocations() throws InterruptedException {
        busSearchPage searchPage = new busSearchPage(driver);

        test = extent.createTest("Validate Search Results with Valid Locations", "Ensures results are displayed for valid 'From' and 'To' locations");

        searchPage.enterFromAddress("Chennai");
        searchPage.selectSecondFromItem();

        searchPage.enterToAddress("Hyderabad");
        searchPage.selectSecondToItem();
        Thread.sleep(3000);

        searchPage.clickDateButton();
        searchPage.clickNextMonthButton();
        searchPage.selectDate();
        Thread.sleep(3000);

        searchPage.clickSearchBusesButton();

        boolean noResultsDisplayed = searchPage.noResultsDisplayed(); // Replace with actual validation logic
        if (!noResultsDisplayed) {
            test.pass("Bus results displayed for valid locations");
        } else {
            test.fail("Bus results not displayed for valid locations");
        } }
}
