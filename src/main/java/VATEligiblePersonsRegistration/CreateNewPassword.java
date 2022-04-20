package VATEligiblePersonsRegistration;

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
        By termsAndConditionsCheckBox = By.xpath("//label[@for='mat-checkbox-1-input']//div[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin']");
        By declarationCheckBox = By.xpath("//label[@for='mat-checkbox-2-input']//div[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin']");
        By authorizationCheckBox = By.xpath("//label[@for='mat-checkbox-3-input']//div[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin']");
        By confirmButton = By.xpath("//signup-create-password/descendant::mat-card/following-sibling::div/child::button");
        //Locator terms and condition
        By termsAndConditionPopup = By.xpath("//span[normalize-space()='Terms & Conditions']");
        By closeTermsAndConditionPopup = By.xpath("//div[@id='exampleModal']//button[@class='close']");
        //Close Survey Image Locator
        By closeSurvey=By.xpath("//img[@alt='close-survey']");
        //Check Registration Successful page
        By goToLoginButton = By.xpath("//span[normalize-space()='Go To Login']");
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
            List<WebElement> list = switch (key) {
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
            driver.findElement(termsAndConditionsCheckBox).click();
        }

        //Check Declaration Check Box
        public void checkDeclarationCheckBox(){
            driver.findElement(declarationCheckBox).click();
    }

        //Check Authorization Check Box
        public void checkAuthorizationCheckBox(){
            driver.findElement(authorizationCheckBox).click();
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

        //check confirm Button is enabled or not
        public boolean isContinueButtonEnabled() {
            String disabled = driver.findElement(confirmButton).getAttribute("disabled");
            System.out.println(disabled);
            if(disabled == null)
            {
                return true;
            }
            return false;
        }

        //Open terms and condition popup
        public void openTermsAndConditionPopup() {
            driver.findElement(termsAndConditionPopup).click();
            driver.findElement(closeTermsAndConditionPopup).click();
        }

        //Check Terms and condition check box checked or not
        public boolean termAndConditionCheckBoxChecked() {
            List<WebElement> agreeCheckBox = driver.findElements(termsAndConditionsCheckBox);
            if (agreeCheckBox.size() > 0) {
                return true;
            }
            return false;
        }
        //check Registration Successful page is displayed
        public boolean checkRegistrationSuccessfulPagesDisplayed() {
            return driver.findElement(goToLoginButton).isDisplayed();
        }
        //Click on goToLogin button
    public void clickOnGoToLoginButton(){
            driver.findElement(goToLoginButton).click();
    }
    }



