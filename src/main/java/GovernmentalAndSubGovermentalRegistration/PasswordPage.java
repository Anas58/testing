package GovernmentalAndSubGovermentalRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class PasswordPage {

    WebDriver driver;

    //Labels
    private By label = By.xpath("//p[@class = 'pwdScreenCss']");

    //The colour displayed by the password strength
    private By passwordStrengthColour = By.xpath("//p[@class = 'pwdcss text-error']");

    //Hide password button
    private By hidePassword = By.xpath("(//mat-icon[@class = 'mat-icon notranslate material-icons mat-icon-no-color'])[1]");

    //Password field
    private By passwordField = By.xpath("//input[@formcontrolname = 'pwd']");

    //Hide confirm password
    private By hideConfirmPassword = By.xpath("(//mat-icon[@class = 'mat-icon notranslate material-icons mat-icon-no-color'])[2]");

    //Confirm password field
    private By confirmPasswordField = By.xpath("//input[@formcontrolname = 'cnfrmPwd']");

    //Terms and conditions link
    private By termsAndConditionsLink = By.xpath("//span[@class = 'ng-star-inserted']");

    //Terms and conditions pop up page
    private By termsAndConditionsPopUp = By.xpath("(//button[@class = 'close'])[1]");

    //Terms and conditions check
    private By termsAndConditionCheck = By.xpath("//mat-checkbox[@formcontrolname = 'acceptTerms']");

    //Confirm button
    private By confirmButton = By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base btnCss']");



    public PasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPasswordLabel() {
        return driver.findElement(with(label).above(passwordField)).getText();
    }

    public String getConfirmPasswordLabel() {
        return driver.findElement(with(label).above(confirmPasswordField)).getText();

    }

    //This inputs the password and confirm password from data from excel
    public void fillData(Map<String, String> data) {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(passwordField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("new_password") != null) {
            driver.findElement(passwordField).sendKeys(data.get("new_password"));
        }

        driver.findElement(confirmPasswordField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("confirm_new_password") != null) {
            driver.findElement(confirmPasswordField).sendKeys(data.get("confirm_new_password"));
        }
    }

    public String getPasswordStrengthErrorMessage() {
        List<WebElement> elements = driver.findElements(passwordStrengthColour);
        if (elements.size() > 0) {
            return elements.get(0).getText();
        }
        return null;
    }

    public String getPasswordStrengthColour() {
        List<WebElement> elements = driver.findElements(passwordStrengthColour);
        if (elements.size() > 0) {
            return elements.get(0).getCssValue("color");
        }
        return null;
    }

    public String getFieldsLabels(String key) {
        List<WebElement> fieldErrors = switch (key) {
            case "new_password" -> driver.findElements(with(label).above(passwordField));
            case "confirm_NewPassword" -> driver.findElements(with(label).above(confirmPasswordField));
            default -> new ArrayList<>();

        };
        if (fieldErrors.size() > 0) {
            return fieldErrors.get(0).getText();
        }
        return null;
    }
    //This clicks the hide password button
    public void clickHidePassword() {
        driver.findElement(hidePassword).click();

    }

    //This clicks the hide confirm password button
    public void clickHideConfirmPassword() {
        driver.findElement(hideConfirmPassword).click();

    }

    //This clicks the terms and condition
    public void clickTermsAndConditionsLink() {
        driver.findElement(termsAndConditionsLink).click();

    }

    //This closes the terms and condition
    public void closeTermsAndConditionsPopUp() {
        driver.findElement(termsAndConditionsPopUp).click();

    }

    //This checks terms and condition radio button
    public void checkTermsAndCondition() {
        driver.findElement(termsAndConditionCheck).click();

    }

    //This clicks the confirm button
    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();

    }

}
