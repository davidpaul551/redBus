package redBus.Test;

import org.testng.annotations.Test;
import redBus.Base.baseClass;
import redBus.PageObjectModel.trainSearchPage;

public class TrainSearchTest extends baseClass {
    trainSearchPage trainSearchObj;

    @Test(priority = 1)
    public void trainSearch() throws InterruptedException {
        test = extent.createTest("trainSearchTest", "Verifies the train search functionality");
        trainSearchObj = new trainSearchPage(driver);
        trainSearchObj.clickTrainTicketButton();

        trainSearchObj.enterTrainFromAddress("chennai");
        System.out.println("From ddres entered");
        Thread.sleep(3000);

        trainSearchObj.selectTrainSecondFromItem();

        System.out.println("From add selected");
        String Destination = "Kacheguda";
        trainSearchObj.enterTrainToAddress(Destination);
        Thread.sleep(3000);
        trainSearchObj.selectTrainSecondToItem();

        trainSearchObj.clickTrainDateButton();

        trainSearchObj.clickTrainNextMonthButton();

        trainSearchObj.selectTrainDate();

        trainSearchObj.clickSearchTrainButton();
        Thread.sleep(3000);

        if(trainSearchObj.isAvailableDisplayed()){
            Thread.sleep(3000);
            trainSearchObj.selectSleeper();
            if(trainSearchObj.isGoAheadVisible()){
                Thread.sleep(3000);
                trainSearchObj.clickGoAhead();
                Thread.sleep(3000);
                String DestinationName = trainSearchObj.getDestinationName();
                System.out.println(DestinationName);
                if (DestinationName.toLowerCase().contains(Destination.toLowerCase())) {
                    test.pass("Destination name is validated");
                    String finalFare = trainSearchObj.getFare();
                    test.pass("The train details are searched and price is " + finalFare);
                }
            }else{
                String finalFare = trainSearchObj.getFare();
                test.pass("The train details are searched and price is "+finalFare);
            }
        }else{
            test.fail("Trains are not available");
        }
       // String screenshotPath = captureScreenshot(driver,"trainSearch.jpg");

        // Add the relative path to the report
        //String relativePath2 = "screenshots/trainSearch.jpg";  // relative to the report location
        test.pass("Searched for trains");
    }
}
