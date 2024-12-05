package redBus.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class trainLiveStatusPage {

    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    //Live train status check
    By liveTrainStatusButton = By.xpath("//*[@id=\"root\"]/section/div[2]/div[3]/div[1]/div[3]");
    By trainNumberTextbox = By.xpath("//*[@id=\"root\"]/div/section/div[2]/div[4]/input");
    By selectTrainItem = By.xpath("(//div[@class='lts_solr_wrap']/b)[2]");
    By checkStatusButton = By.xpath("//*[@id=\"root\"]/div/section/div[2]/div[4]/button");
    By trainNumber = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div[1]/span");
    public trainLiveStatusPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void liveTrainStatus(){
        driver.findElement(liveTrainStatusButton).click();

    }
    public void enterTrainNumber(String Train_Number){
        WebElement trainNumberText = driver.findElement(trainNumberTextbox);
        trainNumberText.click();
        trainNumberText.sendKeys(Train_Number);


    }
    public void clickCheckStatus(){

        WebElement trainNumberItem = wait.until(ExpectedConditions.visibilityOfElementLocated(selectTrainItem));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trainNumberItem);
        wait.until(ExpectedConditions.elementToBeClickable(trainNumberItem));
        js.executeScript("arguments[0].click();", trainNumberItem);

        WebElement statusButton = driver.findElement(checkStatusButton);
        js.executeScript("arguments[0].click();", statusButton);


    }
    public boolean isTrainDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(trainNumber)).isDisplayed();
    }
    public String getTrainNumberDisplayed(){
        WebElement TrainNumberElement = driver.findElement(trainNumber);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", TrainNumberElement);
        return TrainNumberElement.getText();




    }
}
