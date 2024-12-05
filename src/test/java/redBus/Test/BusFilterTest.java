package redBus.Test;

import org.testng.annotations.Test;
import redBus.Base.baseClass;
import redBus.PageObjectModel.busFilterpage;

public class BusFilterTest extends baseClass {
    busFilterpage filterPage;

    @Test(priority = 2)
    public void filterBusTest() throws InterruptedException {
        test = extent.createTest("Filter Buses Test", "Verifies the bus filter functionality");
        filterPage = new busFilterpage(driver);

        filterPage.handlePopup();
        Thread.sleep(3000);
        filterPage.clickAcFilter();
        test.pass("Applied AC filter");
        Thread.sleep(3000);
        filterPage.clickSleeperFilter();
        test.pass("Applied Sleeper filter");
        Thread.sleep(3000);
        filterPage.selectBoardingPoint();
        test.pass("Selected Boarding Point");
        Thread.sleep(3000);
        filterPage.selectDroppingPoint();
        test.pass("Selected Dropping Point");
        Thread.sleep(3000);
        filterPage.selectSortByRatings();
        test.pass("Applied Sort by Ratings");

        Thread.sleep(4000);
       // String screenshotPath = captureScreenshot(driver,"busFilter.jpg");

        // Add the relative path to the report
        String relativePath1 = "screenshots/busFilter.jpg";  // relative to the report location
        test.pass("Screenshot after searching for buses:").addScreenCaptureFromPath(relativePath1);





    }


}
