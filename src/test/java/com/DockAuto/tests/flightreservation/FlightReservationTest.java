package com.DockAuto.tests.flightreservation;

import com.DockAuto.pages.flight_reservation.*;
import com.DockAuto.tests.AbstractTest;
import com.DockAuto.tests.flightreservation.model.FlightReservationTestData;
import com.DockAuto.util.Config;
import com.DockAuto.util.Constants;
import com.DockAuto.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters({"testDataPath"})
    public void setPageObject(String testDataPath) {

        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCreds(testData.email(), testData.password());
        registrationPage.enterAddress(testData.city(), testData.street(), testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegTest")
    public void regConfirmTest() {
        RegistrationConfirmation registrationConfirmation = new RegistrationConfirmation(driver);
        Assert.assertTrue(registrationConfirmation.isAt());
        Assert.assertEquals(registrationConfirmation.getFirstName(), testData.firstName());
        registrationConfirmation.goToFlightSearch();
    }

    @Test(dependsOnMethods = "regConfirmTest")
    public void flightSearchTest() {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassenger(testData.passengersCount());
        flightSearchPage.searchFlight();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest() {
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
