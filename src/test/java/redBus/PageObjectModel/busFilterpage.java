package redBus.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class busFilterpage {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    By popupCloseButton = By.xpath("//*[@id='root']/div/div[3]/div[1]/div/i");

    By acFilterButton = By.xpath("//*[@id=\"filter-block\"]/div/div[2]/ul[3]/li[3]/label[1]");
    By sleeperFilterButton = By.xpath("//*[@id=\"filter-block\"]/div/div[2]/ul[3]/li[2]");
    By overlay = By.className("overlayScroller");
    By boardingPointButton = By.xpath("//*[@id=\"filter-block\"]/div/div[2]/div[6]/input");
    By fifthItemBoardingPoint = By.xpath("//*[@id=\"filter-block\"]/div/div[2]/div[7]/div[2]/div/div/div/div/div[4]/ul/li[2]");
    By selectBoarding = By.xpath("//*[@id=\"filter-block\"]/div/div[2]/div[7]/div[2]/div/div/div/div/div[5]/div[2]");


    By droppingPointButton = By.xpath("//*[@id=\"filter-block\"]/div/div[2]/div[8]/input");
    By getFifthItemDroppingPoint = By.xpath("//*[@id=\"filter-block\"]/div/div[2]/div[9]/div[2]/div/div/div/div/div[4]/ul/li[2]");
    By selectDropping = By.xpath("//*[@id=\"filter-block\"]/div/div[2]/div[9]/div[2]/div/div/div/div/div[5]/div[2]");

    By sortRatingsButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[2]/div[5]");

    public busFilterpage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAcFilter(){
        WebElement acFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(acFilterButton));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", acFilter);
        acFilter.click();
        System.out.println("Clicked ac filter");
    }
    public void clickSleeperFilter() {
        WebElement sleeperFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(sleeperFilterButton));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", sleeperFilter);
        sleeperFilter.click();
        System.out.println("Clicked sleeper filter");

    }
    public void selectBoardingPoint() {
        WebElement boardingPoint = wait.until(ExpectedConditions.visibilityOfElementLocated(boardingPointButton));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", boardingPoint);
        boardingPoint.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(overlay));
        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(fifthItemBoardingPoint));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);

        checkbox.click();

        driver.findElement(selectBoarding).click();

    }
    public void selectDroppingPoint() {
        WebElement droppingPoint = wait.until(ExpectedConditions.visibilityOfElementLocated(droppingPointButton));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", droppingPoint);
        droppingPoint.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(overlay));
        WebElement checkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(getFifthItemDroppingPoint));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox2);
        checkbox2.click();

        driver.findElement(selectDropping).click();

    }
    public void selectSortByRatings(){
        driver.findElement(sortRatingsButton).click();
    }
    public void handlePopup() {
        try {
            // Wait for the popup to appear
            wait.until(ExpectedConditions.presenceOfElementLocated(popupCloseButton));

            // Check if the popup close button is present
            if (!driver.findElements(popupCloseButton).isEmpty()) {
                WebElement closeButton = driver.findElement(popupCloseButton);
                closeButton.click();
                System.out.println("Popup closed successfully.");
            } else {
                System.out.println("Popup is not present.");
            }
        } catch (Exception e) {
            System.out.println("No popup appeared or could not be handled: " + e.getMessage());
        }
    }








}
