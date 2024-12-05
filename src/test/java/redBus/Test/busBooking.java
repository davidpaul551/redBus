package redBus.Test;

import org.testng.annotations.Test;
import redBus.Base.baseClass;
import redBus.PageObjectModel.busFilterpage;
import redBus.PageObjectModel.busSearchPage;

public class busBooking extends baseClass {
    busSearchPage searchPage;
    busFilterpage filterPage;
    @Test
    public void busBook() throws InterruptedException {
        test = extent.createTest("Bus Booking");
         searchPage = new busSearchPage(driver);
        filterPage = new busFilterpage(driver);


        searchPage.enterFromAddress("Chennai");
        searchPage.selectSecondFromItem();
        String ToAddress = "Hyderabad";
        searchPage.enterToAddress(ToAddress);
        searchPage.selectSecondToItem();
        Thread.sleep(3000);

        searchPage.clickDateButton();
        searchPage.clickNextMonthButton();
        searchPage.selectDate();
        Thread.sleep(3000);

        searchPage.clickSearchBusesButton();
        if(searchPage.isBussesFound()){
            filterPage.handlePopup();
            filterPage.clickAcFilter();
            filterPage.clickSleeperFilter();
            filterPage.selectBoardingPoint();
            filterPage.selectDroppingPoint();
            filterPage.selectSortByRatings();
            test.pass("Applied ratings");
            searchPage.clickViewSeats();
            if(searchPage.isSeatsAvailable()){
                Thread.sleep(3000);
                String DestName = searchPage.getDestinationName();
                if(DestName.toLowerCase().contains(ToAddress.toLowerCase())){
                    test.pass("Bus results are displayed with "+DestName);
                }else{
                    test.fail("No Busses are available");
                }
               // js.executeScript("window.scrollBy(0, 600);");
               // searchPage.clickOneSeat();
                Thread.sleep(2000);
                //searchPage.selectLocations();
               // String price = searchPage.getFareAmount();
            }else{
                test.fail("Seats are not available");
            }
        }else {
            test.fail("No any");
        }
    }
    @Test
    public void bookBusWithoutFilter() throws InterruptedException {
        test = extent.createTest("Bus Booking");
        searchPage = new busSearchPage(driver);
        filterPage = new busFilterpage(driver);


        searchPage.enterFromAddress("Chennai");
        searchPage.selectSecondFromItem();
        String ToAddress = "Hyderabad";
        searchPage.enterToAddress(ToAddress);
        searchPage.selectSecondToItem();
        Thread.sleep(3000);

        searchPage.clickDateButton();
        searchPage.clickNextMonthButton();
        searchPage.selectDate();
        Thread.sleep(3000);

        searchPage.clickSearchBusesButton();
        if(searchPage.isBussesFound()){
            filterPage.handlePopup();
            //filterPage.clickAcFilter();
            //filterPage.clickSleeperFilter();
            filterPage.selectBoardingPoint();
            filterPage.selectDroppingPoint();
            filterPage.selectSortByRatings();
            test.pass("Applied ratings");
            searchPage.clickViewSeats();
            if(searchPage.isSeatsAvailable()){
                Thread.sleep(3000);
                String DestName = searchPage.getDestinationName();
                if(DestName.toLowerCase().contains(ToAddress.toLowerCase())){
                    test.pass("Bus results are displayed with "+DestName);
                }else{
                    test.fail("No Busses are available");
                }
                // js.executeScript("window.scrollBy(0, 600);");
                // searchPage.clickOneSeat();
                Thread.sleep(2000);
                //searchPage.selectLocations();
                // String price = searchPage.getFareAmount();
            }else{
                test.fail("Seats are not available");
            }
        }else {
            test.fail("No any");
        }


    }


}
