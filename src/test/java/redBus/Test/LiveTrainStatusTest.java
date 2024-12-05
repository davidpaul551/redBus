package redBus.Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import redBus.Base.baseClass;
import redBus.PageObjectModel.trainLiveStatusPage;
import redBus.PageObjectModel.trainSearchPage;

public class LiveTrainStatusTest extends baseClass {
    trainLiveStatusPage trainLiveObj;
    trainSearchPage trainSearchObj;


    @Test(priority = 4)
    public void trainLiveStatus() throws InterruptedException {
        test = extent.createTest("Live Train status", "checks live train location");
        trainSearchObj = new trainSearchPage(driver);
        trainLiveObj = new trainLiveStatusPage(driver);
        trainSearchObj.clickTrainTicketButton();
        //trainSearchObj.clickTrainTicketButtonONLive();

        trainLiveObj.liveTrainStatus();
        test.pass("clicked live train status button");

        trainLiveObj.enterTrainNumber("1277");
        test.pass("Entered train number");

        trainLiveObj.clickCheckStatus();
        test.pass("clicked check train status");
        Thread.sleep(4000);
        if(trainLiveObj.isTrainDisplayed()) {
            String displayedText = trainLiveObj.getTrainNumberDisplayed();

            // Extract the train number using string manipulation or regex
            String actualTrainNumber = displayedText.split("\\|")[0].trim(); // Extracts "12773"
            String expectedTrainNumber = "12773";
            Assert.assertEquals(actualTrainNumber, expectedTrainNumber, "Train number does not match!");

            // String screenshotPath = captureScreenshot(driver,"trainLiveStatusSC.jpg");

            // Add the relative path to the report
            //String relativePath3 = "screenshots/trainLiveStatusSC.jpg";  // relative to the report location
            test.pass("Train Live Status is checked for train number " + actualTrainNumber);
        }else{
            test.fail("Selected Train is not running currently");
        }


    }

}
