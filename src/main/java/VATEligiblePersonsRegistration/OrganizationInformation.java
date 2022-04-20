package VATEligiblePersonsRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class OrganizationInformation{
    WebDriver driver;

    public OrganizationInformation(WebDriver driver) {
        this.driver = driver;
    }

    private By companyIDField = By.xpath("//input[@formcontrolname='companyID']");
    private By mobileNumberField = By.xpath("(//input[@formcontrolname='mobileNumber'])");
    private By emailField = By.xpath("(//input[@formcontrolname='email'])");
    private By confirmedEmailField = By.xpath("(//input[@formcontrolname='confirmEmail'])");
    private By captchaField = By.xpath("(//input[@formcontrolname='captcha2'])");
    private By captchaText = By.xpath("(//input[@formcontrolname='captcha1'])");
    private By renewIcon = By.xpath("//mat-icon[normalize-space()='autorenew']");
    private By belowField_errorMessage = By.tagName("mat-error");
    private By page_errorMessage = By.tagName("li");
    private By page_errorMessage_closeButton = By.xpath("//li//notifier-notification//button//*[name()='svg']");
    private By label = By.xpath("//div[@class='registerLabelCss required']");
    private By backButton = By.xpath("//div [@class= 'pointer-hand text-left']");
    private By continueButton = By.xpath("(//button[@class='rounded btnCss save-btn mat-focus-indicator mat-button mat-button-base'])");


    public void fillForm(Map<String, String> data) {
        // enter company ID
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(companyIDField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("company_id") != null) {
            driver.findElement(companyIDField).sendKeys(data.get("company_id"));
        }

        // Click anywhere on page
        driver.findElement(By.xpath("//html")).click();

        // enter Mobile Number
        driver.findElement(mobileNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("mobile_number") != null) {
            driver.findElement(mobileNumberField).sendKeys(data.get("mobile_number"));
        }

        // enter Email
        driver.findElement(emailField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("email") != null) {
            driver.findElement(emailField).sendKeys(data.get("email"));
        }

        // enter Confirmed Email
        driver.findElement(confirmedEmailField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("confirm_email") != null) {
            driver.findElement(confirmedEmailField).sendKeys(data.get("confirm_email"));
        }

        // enter Captcha Text
        driver.findElement(captchaField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("captcha") != null) {
            if (data.get("captcha").equals("actual_captcha")) {
                String captcha = driver.findElement(captchaText).getAttribute("value");
                driver.findElement(captchaField).sendKeys(captcha);
            } else {
                driver.findElement(captchaField).sendKeys(data.get("captcha"));
            }
        }
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
    }


    //get Error Messages below the Fields
    public String getFields_ErrorMessages(Map<String, String> errorData) {
        List<WebElement> errorMessage = switch (errorData.get("error_element")) {
            case "company_id" -> driver.findElements(with(belowField_errorMessage).near(companyIDField, 70));
            case "mobile_number" -> driver.findElements(with(belowField_errorMessage).near(mobileNumberField,70));
            case "email" -> driver.findElements(with(belowField_errorMessage).near(emailField,70));
            case "confirm_email" -> driver.findElements(with(belowField_errorMessage).near(confirmedEmailField,70));
            case "captcha" -> driver.findElements(with(belowField_errorMessage).near(captchaField,70));
            default -> new ArrayList<>();
        };
        if (errorMessage.size() > 0) {
            return errorMessage.get(0).getText();
        }
        return null;

    }

    // get page error messages
    public String getPage_ErrorMessage (){
        List <WebElement> errorMessages = driver.findElements(page_errorMessage);
        if (errorMessages.size() > 0){
            // get first message
            String errorMessage = errorMessages.get(0).getText();
            // close messages
            List <WebElement> errorMessagesCloseButton = driver.findElements(page_errorMessage_closeButton);
            for (int index = errorMessagesCloseButton.size()-1; index >= 0; index--){
                errorMessagesCloseButton.get(index).click();
            }
            return errorMessage;
        }
        return null;
    }

    //get Fields Labels
    public String getFields_Labels (String theLable) {
        WebElement fieldLabel = switch(theLable){
            case "company_ID" -> driver.findElement(RelativeLocator.with(label).above(companyIDField));
            case "mobile_number" -> driver.findElement(with(label).above(mobileNumberField));
            case "email" -> driver.findElement(with(label).above(emailField));
            case "confirm_email" -> driver.findElement(with(label).above(confirmedEmailField));
            case "captcha" -> driver.findElement(with(label).above(captchaField));
            default -> null;

        };
        if (!fieldLabel.getText().isEmpty()){
            return fieldLabel.getText();
        }
        return null;

    }

    //get Captcha text
    public String getCaptchaText(){
        String captcha = driver.findElement(captchaText).getAttribute("value");
        driver.findElement(captchaField).sendKeys(captcha);
        return null;
    }

    //click on Renew Captch Icon
    public void renewCaptch() {
        driver.findElement(renewIcon).click();
    }

    //click on Continue Button
    public void clickOnContinueButton() {
        driver.findElement(continueButton).click();
    }

    //click on Back Button
    public void clickOnBackButton() {
        driver.findElement(backButton).click();
    }

    //check The Current Page
    public boolean checkOrganizationInformationPage() { return driver.findElement(companyIDField).isDisplayed();
    }
}


