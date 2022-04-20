package IndividualRegistration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AcknowledgementPage {

    WebDriver driver;

    //Ratings pop up
    private By ratingPopUp = By.xpath("//img[@class = 'close_s2c_survey']");

    private By mainSuccessMessage = By.xpath("//p[@class = 'mainTitle']");
    private By subSuccessMessage = By.xpath("//div[@class = 'subTitle']");

    //Go to Login Page button
    private By goToLoginPage = By.xpath("(//button[@class = 'btnCss mat-focus-indicator mat-button mat-button-base'])[1]");

    public AcknowledgementPage(WebDriver driver) {
        this.driver = driver;

    }

    public String getMainSuccessMessage() {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(30))
        .until(ExpectedConditions.visibilityOf(driver.findElement(mainSuccessMessage)));
        driver.findElement(mainSuccessMessage).isDisplayed();
        return driver.findElement(mainSuccessMessage).getText();

    }

    public String getSubSuccessMessage() {
        driver.findElement(subSuccessMessage).isDisplayed();
        return driver.findElement(subSuccessMessage).getText();

    }

    public void clickGoToLoginPage() {
        driver.findElement(goToLoginPage).click();

    }

    public boolean ratingPopUp(){
        return driver.findElement(ratingPopUp).isDisplayed();
    }

    public void closeRatingPopUp(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(300));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ratingPopUp)).click();
    }

}
