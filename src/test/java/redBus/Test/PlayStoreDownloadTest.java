package redBus.Test;

import org.testng.annotations.Test;
import redBus.Base.baseClass;
import redBus.PageObjectModel.busSearchPage;
import redBus.PageObjectModel.playStoreLinkPage;

import java.util.Set;

public class PlayStoreDownloadTest extends baseClass {
    playStoreLinkPage playStoreObj;
    busSearchPage searchPage;



    @Test(priority = 1)
    public void playStoreIcon() throws InterruptedException {
        test = extent.createTest("PlayStore Icon Test", "Verifies the PlayStore download link functionality");
        playStoreObj = new playStoreLinkPage(driver);
        searchPage = new busSearchPage(driver);

        searchPage.clickBusTicketButton();
        String mainWindowHandle = driver.getWindowHandle();

        playStoreObj.clickDownloadLink();
        test.pass("To verify the download link of PlayStore");

        Thread.sleep(4000);
         Set<String> allWindowHandles = driver.getWindowHandles();

        if (allWindowHandles.size() == 1) {
            String screenshotPath = captureScreenshot("verifyTabSwitchFailure");
            System.out.println("No new tab opened, or the action reused the main tab.");
            test.fail("New tab did not open, the main tab was overwritten.").addScreenCaptureFromPath(screenshotPath);
        } else {
            for (String handle : allWindowHandles) {
                if (!handle.equals(mainWindowHandle)) {
                    driver.switchTo().window(handle);
                    String newUrl = driver.getCurrentUrl();
                    System.out.println("New tab opened successfully. Current URL: " + newUrl);
                    test.pass("New tab opened with a link to the Play Store.");
                    driver.close();
                    driver.switchTo().window(mainWindowHandle);
                }
            }
        }}

}
