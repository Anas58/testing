package NonResidentCompanyRegistration;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class OrganizationInformation {

    WebDriver driver;

    public OrganizationInformation(WebDriver driver) {
        this.driver = driver;
    }

    private final By companyIDField = By.xpath("//input[@formcontrolname='companyID']");
    private final By companyNameField = By.xpath("//input[@formcontrolname='companyName']");
    private final By companyInYourCountryNumber = By.xpath("//input[@formcontrolname='compInyourCountryNumber']");
    private final By tinInYourCountryNumber = By.xpath("//input[@formcontrolname='tinInyourCountryNumber']") ;

    private final By mobileNumberField = By.xpath("//input[@formcontrolname='mobileNumber']");
    private final By emailField = By.xpath("//input[@formcontrolname='email']");
    private final By confirmEmailField = By.xpath("//input[@formcontrolname='confirmEmail']");
    private final By verificationCodeField = By.xpath("//input[@formcontrolname='captcha2']");
    private final By refreshCodeButton = By.cssSelector(".renewIcon");
    private final By accountingButtons = By.xpath("//mat-radio-group[@formcontrolname='accounting']");
    private final By radio_buttons = By.tagName("mat-radio-button");
    private final By reportingBranch = By.xpath("//mat-select[@formcontrolname='reportingBranch']");

    private final By errorMessage = By.tagName("mat-error");
    private final By continueButton = By.xpath("//signup-residentiary-details/preceding::button");
    private final By label = By.xpath("//div[@class='registerLabelCss required']");
    private final By captchaField = By.xpath("//input[@formcontrolname='captcha2']");
    private final By captchaText = By.xpath("//input[@formcontrolname='captcha1']");
    private final By options = By.tagName("mat-option");

    private final By pageError = By.xpath("//p[contains(@class,'notifier__notification-message')]");
    private final By BackButton = By.xpath("//i[contains(@class, 'arrow-left')]");
    private final By pageErrorCloseButton = By.xpath("//button[contains(@class,'notifier__notification-button')]");


    public void fillData(Map<String, String> data){
        String selectAll = Keys.chord(Keys.CONTROL,"a");
        driver.findElement(companyIDField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("company_id") != null){
            driver.findElement(companyIDField).sendKeys(data.get("company_id"));
        }
        // click anywhere on page, because CompanyID is affecting other fields if it wasn't verified from Database
        driver.findElement(By.xpath("//html")).click();

        driver.findElement(companyNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("company_name") != null){
            driver.findElement(companyNameField).sendKeys(data.get("company_name"));
        }
        if(data.get("radio_button") != null) {
            List<WebElement> residency_status_buttons = driver.findElement(accountingButtons).findElements(radio_buttons);
            for (int i = 0; i < residency_status_buttons.size(); i++) {
                if (residency_status_buttons.get(i).getText().equals(data.get("radio_button"))) {
                    residency_status_buttons.get(i).click();
                }
            }
        }

        if(existsElement(companyInYourCountryNumber)){
            driver.findElement(companyInYourCountryNumber).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if(data.get("companyID_country") != null){
            driver.findElement(companyInYourCountryNumber).sendKeys(data.get("companyID_country"));
        }
        if(existsElement(tinInYourCountryNumber)){
            driver.findElement(tinInYourCountryNumber).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if(data.get("TIN_country") != null){
            driver.findElement(tinInYourCountryNumber).sendKeys(data.get("TIN_country"));
        }
        if(data.get("reporting_branch") != null ){
            selectReportingBranch(data.get("reporting_branch"));
        }
        driver.findElement(mobileNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("mobile_number") != null){
            driver.findElement(mobileNumberField).sendKeys(data.get("mobile_number"));
        }
        driver.findElement(emailField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("email") != null){
            driver.findElement(emailField).sendKeys(data.get("email"));
        }

        driver.findElement(confirmEmailField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("confirm_email") != null){
            driver.findElement(confirmEmailField).sendKeys(data.get("confirm_email"));
        }

        driver.findElement(captchaField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("captcha") != null){
            if(data.get("captcha").equals("actual_captcha")){
                String captcha = driver.findElement(captchaText).getAttribute("value");
                driver.findElement(captchaField).sendKeys(captcha);
            } else {
                driver.findElement(captchaField).sendKeys(data.get("captcha"));
            }
        }
    }

    public String getErrorMessage(Map<String, String> rowOfData) {
        // find error related to element
        List<WebElement> errorMessages = switch(rowOfData.get("error_element")){
            case "company_id" -> driver.findElements(with(errorMessage).near(companyIDField, 50));
            case "company_name" -> driver.findElements(with(errorMessage).near(companyNameField, 50));
            case "mobile_number" -> driver.findElements(with(errorMessage).near(mobileNumberField, 50));
            case "email" -> driver.findElements(with(errorMessage).near(emailField, 50));
            case "confirm_email" -> driver.findElements(with(errorMessage).near(confirmEmailField, 50));
            case "captcha" -> driver.findElements(with(errorMessage).near(captchaField,50));
            case "companyID_country" -> driver.findElements(with(errorMessage).near(companyInYourCountryNumber,50));
            case "tin_country" -> driver.findElements(with(errorMessage).near(tinInYourCountryNumber,50));
            case "reporting_branch" -> driver.findElements(with(errorMessage).near(reportingBranch,50));
            default -> new ArrayList<>();
        };
        // return text of the first element if any
        if(errorMessages.size() > 0) {
            return errorMessages.get(0).getText();
        }
        return null;
    }

    public String getPageErrorMessage() {
        List<WebElement> errorMessages = driver.findElements(pageError);
        if(errorMessages.size() > 0) {
            // get first message only
            String errorMessage = errorMessages.get(0).getText();
            // closer all messages
            List<WebElement> errorMessagesCloseButton =  driver.findElements(pageErrorCloseButton);
            for(int index = errorMessagesCloseButton.size() - 1; index >= 0; index--){
                errorMessagesCloseButton.get(index).click();
            }
            return errorMessage;
        }
        return null;
    }


    public boolean selectReportingBranch(String branch) {
        return selectFromList(reportingBranch, branch);
    }
    public boolean selectFromList(By e, String value){
        driver.findElement(e).click();
        List<WebElement> allOptions = driver.findElements(options);
        for (int i = 0; i <= allOptions.size() - 1; i++) {
            if (allOptions.get(i).getText().equals(value)) {
                allOptions.get(i).click();
                return true;
            }
        }

        return false;
    }
    public boolean existsElement(By element) {
        try {
            driver.findElement(element);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }

    public boolean checkPage(){
        return driver.findElement(companyIDField).isDisplayed();
    }

    public void clickBackButton(){
        driver.findElement(BackButton).click();
    }
}
