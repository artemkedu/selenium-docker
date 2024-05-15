package com.DockAuto.pages.flight_reservation;

import com.DockAuto.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);
    @FindBy(xpath = "//div[@class='row justify-content-center']//div[contains(text(),'Total Price')]/..//p")
    private WebElement totalPriceElement;

    @FindBy(xpath = "//div[@class='row justify-content-center']//div[contains(text(),'Flight Confirmation')]/..//p")
    private WebElement flightConfirmationElement;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(totalPriceElement));
        return this.totalPriceElement.isDisplayed();
    }

    public String getPrice() {
        String confirmation = this.flightConfirmationElement.getText();
        String price = this.totalPriceElement.getText();
        log.info("Flight Confirmation Number : {}", confirmation);
        log.info("Total Price : {}", price);
        return price;
    }
}
