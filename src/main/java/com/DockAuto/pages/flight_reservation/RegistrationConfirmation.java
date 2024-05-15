package com.DockAuto.pages.flight_reservation;

import com.DockAuto.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmation extends AbstractPage {
    @FindBy(id = "go-to-flights-search")
    private WebElement confirmationButton;
    @FindBy(xpath = "//div[@id='registration-confirmation-section']//p//b")
    private WebElement firstNameElement;

    public RegistrationConfirmation(WebDriver driver){
        super(driver);
    }
    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmationButton));
        return this.confirmationButton.isDisplayed();
    }

    public String getFirstName(){
        return this.firstNameElement.getText();
    }
    public void goToFlightSearch(){
        this.confirmationButton.click();
    }
}
