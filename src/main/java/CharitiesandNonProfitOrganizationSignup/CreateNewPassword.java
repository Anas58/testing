package CharitiesandNonProfitOrganizationSignup;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class CreateNewPassword {
    WebDriver driver;

    public CreateNewPassword(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By newPassword = By.xpath("//input[@formcontrolname='pwd']");
    By confirmNewPassword = By.xpath("//input[@formcontrolname='cnfrmPwd']");
    By agreeToTheTermsAndConditions = By.xpath("//mat-checkbox[@formcontrolname='acceptTerms']");
    By confirmButton = By.xpath("//button[contains(@class,'mat-focus-indicator mat-raised-button mat-button-base btnCss')]//span[contains(@class,'mat-button-wrapper')]");

    //Locator terms and condition
    By termsAndConditionPopup = By.xpath("//div[@class='row ng-star-inserted']//span[@class='ng-star-inserted']");
    By closeTermsAndConditionPopup = By.xpath("//div[@id='exampleModal']//button[@class='close']");
    //Close Survey Image Locator
    By closeSurvey=By.xpath("//img[@alt='close-survey']");

    //Password Validation Messages
    By errorValidationMessage = By.cssSelector(".pwdcss.text-error");
    By successValidationMessage = By.cssSelector(".pwdcss.text-success");

    //Labels Locators
    By fieldLabels = By.className("pwdScreenCss");

    //Method Data Filling in Password and confirm Password
    public void fillData(Map<String, String> data) {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(newPassword).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("new_password") != null) {
            driver.findElement(newPassword).sendKeys(data.get("new_password"));
        }
        driver.findElement(confirmNewPassword).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("confirm_new_password") != null) {
            driver.findElement(confirmNewPassword).sendKeys(data.get("confirm_new_password"));
        }

    }
    //Close Survey Image;
    public void closeSurvey(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(300));
        //driver.manage().timeouts().
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeSurvey)).click();
    }

    //Check Messages
    public String fieldValidateErrorMessage(Map<String, String> errorData) {
        return passwordValidations(errorData.get("error_messages"));
    }

    //Method to verify Labels
    public String fieldsLabel(String key) {
        List<WebElement> list;
        list = switch (key) {
            case "new_password" -> driver.findElements(with(fieldLabels).above(newPassword));
            case "confirm_new_password" -> driver.findElements(with(fieldLabels).above(confirmNewPassword));
            default -> new ArrayList<>();
        };
        if (list.size() > 0) {
            return list.get(0).getText();
        }
        return null;
    }

    //Check Agree Check Box
    public void checkAgreeCheckBox() {
        driver.findElement(agreeToTheTermsAndConditions).click();
    }

    public boolean checkPasswordPageIsDisplayed(){
        return driver.findElement(newPassword).isDisplayed();
    }

    //click on confirm Button
    public void confirmButton() {
        driver.findElement(confirmButton).click();
    }

    //Get Validation Messages
    public String passwordValidations(String errorMessage) {
        List<WebElement> errorTexts = driver.findElements(with(errorValidationMessage).near(newPassword, 300));
        for (int i = 0; i < errorTexts.size(); i++) {
            if (errorTexts.get(i).getText().equals(errorMessage)) {
                return errorTexts.get(i).getText();
            }
        }
        if (errorTexts.size() > 0) {
            return errorTexts.get(0).getText();
        } else {
            return null;
        }
    }

    //check confirm Button is disabled or not
    public boolean confirmButtonDisabled() {
        return driver.findElement(confirmButton).isEnabled();
    }

    //Open terms and condition popup
    public void openTermsAndConditionPopup() {
        driver.findElement(termsAndConditionPopup).click();
        driver.findElement(closeTermsAndConditionPopup).click();
    }

    //Check Terms and condition check box checked or not
    public boolean termAndConditionCheckBoxChecked() {
        List<WebElement> agreeCheckBox = driver.findElements(agreeToTheTermsAndConditions);
        if (agreeCheckBox.size() > 0) {
            return true;
        }
        return false;
    }
}

