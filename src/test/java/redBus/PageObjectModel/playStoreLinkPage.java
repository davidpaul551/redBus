package redBus.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import redBus.Base.baseClass;

import java.time.Duration;

public class playStoreLinkPage{
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    By playStoreLink = By.xpath("//*[@id=\"homeV2-root\"]/div[4]/div[2]/div/div/div/div/div[2]/div/div[2]/div/img[1]");

    public playStoreLinkPage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickDownloadLink(){
        WebElement playStoreDownloadLink = wait.until(ExpectedConditions.visibilityOfElementLocated(playStoreLink));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", playStoreDownloadLink);
        wait.until(ExpectedConditions.elementToBeClickable(playStoreDownloadLink));
        js.executeScript("arguments[0].click();", playStoreDownloadLink);


    }

}
