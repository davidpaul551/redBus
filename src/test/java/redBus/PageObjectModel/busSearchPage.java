package redBus.PageObjectModel;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class busSearchPage {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    By busTicketButton = By.xpath("//*[@id=\"bus_tickets_vertical\"]");
    public By fromAddress = By.xpath("//*[@id=\"src\"]");
    public By toAddress = By.xpath("//*[@id=\"dest\"]");
    By secondFromItem = By.xpath("(//li[@class=\"sc-iwsKbI jTMXri\"])[4]");
    By secondToItem = By.xpath("//li[contains(@class, 'sc-iwsKbI')][4]");
    By dateButton = By.xpath("//*[@id=\"onwardCal\"]");
    public By calender = By.xpath("//*[@id=\"onwardCal\"]/div/div[2]");
    By nextMonth = By.xpath("//*[@id=\"onwardCal\"]/div/div[2]/div/div/div[1]/div[3]");
    By dateElement = By.xpath("//*[@id=\"onwardCal\"]/div/div[2]/div/div/div[3]/div[4]/span/div[2]");
    public By searchBusesButton = By.xpath("//*[@id=\"search_button\"]");

    By oopsError = By.xpath("//*[@id=\"root\"]/div/div[2]/div");
    By found = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/div[1]/span[1]");
    By viewSeats = By.xpath("//div[@class='button view-seats fr']");
    By seatsAvailable = By.xpath("//*[@id=\"25654934\"]/div[1]/div[1]/div[1]/div[7]/div[1]/span");

    By seat1 = By.xpath("/html[1]/body[1]/section[1]/div[2]/div[4]/div[1]/div[2]/div[1]/div[2]/div[3]/div[3]/ul[1]/div[1]/li[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[2]/canvas[1]");
    By seat2 = By.xpath("/html[1]/body[1]/section[1]/div[2]/div[4]/div[1]/div[2]/div[1]/div[2]/div[3]/div[3]/ul[1]/div[1]/li[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[2]/canvas[1]");
    By boardingP1 = By.xpath("/html[1]/body[1]/section[1]/div[2]/div[4]/div[1]/div[2]/div[1]/div[2]/div[3]/div[1]/ul[1]/div[1]/li[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[2]/div[1]/div[1]/div[1]");
    By droppingP1 = By.xpath("/html[1]/body[1]/section[1]/div[2]/div[4]/div[1]/div[2]/div[1]/div[2]/div[3]/div[1]/ul[1]/div[1]/li[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/ul[1]/li[2]/div[1]/div[1]");
    By fareAmount = By.xpath("//*[@id=\"seatBpdp_25654934\"]/div/div[2]/div/div/div[1]/div[2]/div[4]/span[2]/span[2]");
    By busDeck = By.xpath("//*[@id=\"rt_25654934\"]/div/div/div/div[3]/div[2]/div[2]/canvas");

    By destinationName = By.xpath("//span[normalize-space()='Hyderabad']");


    public busSearchPage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickBusTicketButton(){
        WebElement busTicket = wait.until(ExpectedConditions.visibilityOfElementLocated(busTicketButton));
        busTicket.click();
    }
    public void enterFromAddress(String from) {
        WebElement fromAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(fromAddress));
        fromAddressInput.sendKeys(from);
    }

    public void selectSecondFromItem() {
        WebElement secondFromItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(secondFromItem));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", secondFromItemElement);
        wait.until(ExpectedConditions.elementToBeClickable(secondFromItemElement)).click();
    }

    public void enterToAddress(String to) {
        WebElement toAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(toAddress));
        toAddressInput.sendKeys(to);
    }

    public void selectSecondToItem() {
        WebElement secondToItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(secondToItem));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", secondToItemElement);
        wait.until(ExpectedConditions.elementToBeClickable(secondToItemElement)).click();
    }

    public void clickDateButton() throws InterruptedException {
        WebElement dateButtonElement = driver.findElement(dateButton);
        dateButtonElement.click();
        Thread.sleep(2000);
        dateButtonElement.click();

        System.out.println("Clicked onwardCal button");
    }

    public void clickNextMonthButton() {
        WebElement nextMonthButtonElement = driver.findElement(nextMonth);
        nextMonthButtonElement.click();
    }

    public void selectDate() {
        WebElement dateElementElement = wait.until(ExpectedConditions.elementToBeClickable(dateElement));
        dateElementElement.click();
        System.out.println("Selected the date");
    }

    public void clickSearchBusesButton() {
        WebElement searchBusesButtonElement = driver.findElement(searchBusesButton);
        searchBusesButtonElement.click();
    }

    public boolean noResultsDisplayed(){
        try {
            WebElement oopsMessage = driver.findElement(oopsError);
            return oopsMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isBussesFound(){
        WebElement foundElement = wait.until(ExpectedConditions.visibilityOfElementLocated(found));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(found)).isDisplayed();

    }
    public void clickViewSeats(){
        WebElement viewSeatsElement = driver.findElement(viewSeats);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", viewSeatsElement);
        viewSeatsElement.click();


    }

    public boolean isSeatsAvailable(){
        WebElement seatsAvailableElement = driver.findElement(seatsAvailable);
        //js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seatsAvailableElement);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(seatsAvailable)).isDisplayed();

    }

    public void clickOneSeat() throws InterruptedException {

        //js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", busDeckElement);
        WebElement seat1Element = driver.findElement(seat1);
        Actions actions = new Actions(driver);
//        WebElement element = driver.findElement(By.xpath("your_xpath_here"));
        js.executeScript("arguments[0].style.border='3px solid red'", seat1Element);
// Move the cursor to an offset relative to the element
        actions.moveToElement(seat1Element, 20, 15).build().perform();
        String cur=seat1Element.getAttribute("class");
        Thread.sleep(2000);
        if(cur.equals("pointer")){
            js.executeScript("arguments[0].click();", seat1Element);
        }else{
            System.out.print("move cursor");
        }
        js.executeScript("arguments[0].click();", seat1Element);


        //js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seat1Element);
        //wait.until(ExpectedConditions.elementToBeClickable(seat1Element)).click();


    }
    public void clickTwoSeats(){
        WebElement busDeckElement = driver.findElement(busDeck);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", busDeckElement);
        WebElement seat1Element = driver.findElement(seat1);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seat1Element);
        wait.until(ExpectedConditions.elementToBeClickable(seat1Element)).click();
        WebElement seat2Element = driver.findElement(seat2);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seat2Element);
        wait.until(ExpectedConditions.elementToBeClickable(seat2Element)).click();


    }

    public void selectLocations() throws InterruptedException {
        WebElement boardingP1Element = driver.findElement(boardingP1);
        wait.until(ExpectedConditions.elementToBeClickable(boardingP1Element)).click();
        Thread.sleep(3000);
        WebElement  droppingP1Element = driver.findElement(droppingP1);
        wait.until(ExpectedConditions.elementToBeClickable(droppingP1Element)).click();


    }

    public String getFareAmount(){
        WebElement fareAmountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fareAmount));
        fareAmountElement.getText();
        return fareAmountElement.getText();

    }
    public String getDestinationName(){
        WebElement DElement = driver.findElement(destinationName);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(destinationName)).getText();
    }



}
