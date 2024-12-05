package redBus.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class trainSearchPage {

    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;


    By trainTicketButton = By.xpath("//*[@id=\"rail_tickets_vertical\"]");
    By trainTicketButtonOnLive = By.xpath("//*[@id=\"root\"]/div/div[1]/nav/div/div[1]/a[2]");
    By bookTrainTicketsButton = By.xpath("//*[@id=\"root\"]/section/div[2]/div[3]/div[1]/div[1]");
    By trainFromAddress = By.xpath("//*[@id=\"src\"]");
    By trainSecondFromAddress = By.xpath("//*[@id=\"root\"]/section/div[2]/div[3]/div[2]/div/div/div/div/div[1]");
    By trainToAddress = By.xpath("//*[@id=\"dst\"]");
    By trainSecondToAddress = By.xpath("//*[@id=\"root\"]/section/div[2]/div[3]/div[2]/div/div/div/div/div[1]");

    By trainDateButton = By.xpath("//*[@id=\"root\"]/section/div[2]/div[3]/div[2]/div/form/div[3]");
    By trainNextMonth = By.xpath("//*[@id=\"root\"]/section/div[2]/div[3]/div[2]/div/div/div/div/div[1]/div[3]");
    By TrainDateElement = By.xpath("//*[@id=\"root\"]/section/div[2]/div[3]/div[2]/div/div/div/div/div[3]/span[4]/div[2]");
    By searchTrainButton = By.xpath("//*[@id=\"root\"]/section/div[2]/div[3]/div[2]/div/form/button");


    By available = By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[4]/div[1]/div[1]/div/div[2]/span[1]");
    By sleeper = By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[4]/div[1]/div[1]/div");
    By goAhead = By.xpath("//*[@id=\"root\"]/div/div[4]/div[2]/div[6]");
    By fare = By.xpath("//*[@id=\"travel_info_page\"]/div[6]/div[1]/div[1]/span[2]");
    By destinationName = By.xpath("//*[@id=\"travel_info_page\"]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]");



    //Live train status check
    By liveTrainStatusButton = By.xpath("//*[@id=\"root\"]/section/div[2]/div[3]/div[1]/div[3]");
    By trainNumberTextbox = By.xpath("//*[@id=\"root\"]/div/section/div[2]/div[4]/input");
    By selectTrainItem = By.xpath("(//div[@class='lts_solr_wrap']/b)[2]");
    By checkStatusButton = By.xpath("//*[@id=\"root\"]/div/section/div[2]/div[4]/button");



    //Download link from playstore
    By playStoreLink = By.xpath("//*[@id=\"homeV2-root\"]/div[4]/div[2]/div/div/div/div/div[2]/div/div[2]/div/img[1]");


    public trainSearchPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void clickTrainTicketButton() throws InterruptedException {
        WebElement trainTicket = wait.until(ExpectedConditions.visibilityOfElementLocated(trainTicketButton));
        trainTicket.click();
        Thread.sleep(2000);
        driver.findElement(bookTrainTicketsButton).click();
    }
    public void enterTrainFromAddress(String from){
        WebElement fromAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(trainFromAddress));
        fromAddressInput.sendKeys(from);
    }
    public void selectTrainSecondFromItem() {
        WebElement trainSecondFromItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(trainSecondFromAddress));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trainSecondFromItemElement);
        wait.until(ExpectedConditions.elementToBeClickable(trainSecondFromItemElement));
        js.executeScript("arguments[0].click();", trainSecondFromItemElement);
    }
    public void enterTrainToAddress(String To){
        WebElement toAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(trainToAddress));
        toAddressInput.sendKeys(To);
    }
    public void selectTrainSecondToItem() {
        WebElement trainSecondToItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(trainSecondToAddress));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trainSecondToItemElement);
        wait.until(ExpectedConditions.elementToBeClickable(trainSecondToItemElement));
        js.executeScript("arguments[0].click();", trainSecondToItemElement);

    }

    public void clickTrainDateButton(){
        WebElement dateButtonElement = driver.findElement(trainDateButton);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", dateButtonElement);
        wait.until(ExpectedConditions.elementToBeClickable(dateButtonElement));
        js.executeScript("arguments[0].click();", dateButtonElement);


        //dateButtonElement.click();
    }

    public void clickTrainNextMonthButton(){
        WebElement trainNextMonthButtonElement = driver.findElement(trainNextMonth);
        trainNextMonthButtonElement.click();
    }

    public void selectTrainDate(){
        WebElement dateTrainElementElement = wait.until(ExpectedConditions.elementToBeClickable(TrainDateElement));
        dateTrainElementElement.click();
        System.out.println("Selected the date");
    }
    public void clickSearchTrainButton(){
        WebElement searchTrainsButtonElement = driver.findElement(searchTrainButton);
        searchTrainsButtonElement.click();
    }

    public boolean isAvailableDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(available)).isDisplayed();
    }
    public void selectSleeper(){
        WebElement sleeperElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sleeper));
        sleeperElement.click();
    }
    public boolean isGoAheadVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(goAhead)).isDisplayed();
    }
    public void clickGoAhead(){
        WebElement goAheadElement = wait.until(ExpectedConditions.visibilityOfElementLocated(goAhead));
        goAheadElement.click();
    }

    public String getFare() throws InterruptedException {
        WebElement fareElement = driver.findElement(fare);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", fareElement);
        Thread.sleep(2000);
        return fareElement.getText();


    }
    public String getDestinationName(){
        WebElement destinationNameElement = driver.findElement(destinationName);
        return destinationNameElement.getText();
    }




    public void clickTrainTicketButtonONLive(){
        driver.findElement(trainTicketButtonOnLive).click();
    }





}