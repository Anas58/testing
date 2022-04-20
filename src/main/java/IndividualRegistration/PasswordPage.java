package IndividualRegistration;

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
    private final By label = By.xpath("//p[@class = 'pwdScreenCss']");

    //The colour displayed by the password strength
    private final By passwordStrengthColourError = By.xpath("//p[@class = 'pwdcss text-error']");

    //Hide password button
    private final By hidePassword = By.xpath("(//mat-icon[@class = 'mat-icon notranslate material-icons mat-icon-no-color'])[1]");

    //Password field
    private final By passwordField = By.xpath("//input[@formcontrolname = 'pwd']");

    //Hide confirm password
    private final By hideConfirmPassword = By.xpath("(//mat-icon[@class = 'mat-icon notranslate material-icons mat-icon-no-color'])[2]");

    //Confirm password field
    private final By confirmPasswordField = By.xpath("//input[@formcontrolname = 'cnfrmPwd']");

    //The colour displayed by the password strength when password input in successful
    private final By passwordStrengthColourSuccess = By.xpath("//p[@class = 'pwdcss text-success']");

    //Terms and conditions link
    private final By termsAndConditionsLink = By.xpath("//span[@class = 'ng-star-inserted']");

    //Terms and conditions pop up page
    private final By termsAndConditionsPopUp = By.xpath("(//button[@class = 'close'])[1]");

    //Terms and conditions check
    private final By termsAndConditionCheck = By.xpath("//mat-checkbox[@formcontrolname = 'acceptTerms']");

    //Confirm button
    private final By confirmButton = By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base btnCss']");


    public PasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPasswordLabel(){
        return driver.findElement(with(label).above(passwordField)).getText();

    }

    public String getConfirmPasswordLabel(){
        return driver.findElement(with(label).above(confirmPasswordField)).getText();

    }

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
        List<WebElement> elements = driver.findElements(passwordStrengthColourError);
        if (elements.size() > 0){
            return elements.get(0).getText();
        }
        return null;
    }

    public String getPasswordStrengthColourError(){
        List<WebElement> elements = driver.findElements(passwordStrengthColourError);
        if (elements.size() > 0){
            return elements.get(0).getCssValue("color");
        }
        return null;
    }

    public void clickHidePassword() {
        driver.findElement(hidePassword).click();

    }

    public boolean checkPasswordPageIsDisplayed(){
        return driver.findElement(passwordField).isDisplayed();
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

    public void clickHideConfirmPassword() {
        driver.findElement(hideConfirmPassword).click();

    }


    public String getPasswordStrengthColourSuccess() {
        return driver.findElement(passwordStrengthColourSuccess).getCssValue("color");

    }

    public void clickTermsAndConditionsLink() {
        driver.findElement(termsAndConditionsLink).click();

    }

    public void closeTermsAndConditionsPopUp() {
        driver.findElement(termsAndConditionsPopUp).click();

    }

    public void checkTermsAndCondition() {
        driver.findElement(termsAndConditionCheck).click();

    }

    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();

    }

}
