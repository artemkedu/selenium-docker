package com.DockAuto.tests.vendorportal;

import com.DockAuto.pages.vendorportal.DashboardPage;
import com.DockAuto.pages.vendorportal.LoginPage;
import com.DockAuto.tests.AbstractTest;
import com.DockAuto.tests.vendorportal.model.VendorPortalTestData;
import com.DockAuto.util.Config;
import com.DockAuto.util.Constants;
import com.DockAuto.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashBoardTest() {
        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarnings());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarnings());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getResultsCount(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashBoardTest")
    public void logout() {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
