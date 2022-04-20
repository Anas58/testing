package CompanyRegistrationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class CompanyRegistration {
    WebDriver driver;

    public CompanyRegistration(WebDriver driver) {
        this.driver = driver;
    }

    private final By companyIDField = By.xpath("//input[@formcontrolname='companyID']");
    private final By companyNameField = By.xpath("//input[@formcontrolname='companyName']");

    private final By mobileNumberField = By.xpath("//input[@formcontrolname='mobileNumber']");
    private final By emailField = By.xpath("//input[@formcontrolname='email']");
    private final By confirmEmailField = By.xpath("//input[@formcontrolname='confirmEmail']");
    private final By verificationCodeField = By.xpath("//input[@formcontrolname='captcha2']");
    private final By refreshCodeButton = By.cssSelector(".renewIcon");

    private final By errorMessage = By.tagName("mat-error");
    private final By continueButton = By.xpath("//signup-residentiary-details/preceding::button");
    private final By label = By.xpath("//div[@class='registerLabelCss required']");
    private final By captchaField = By.xpath("//input[@formcontrolname='captcha2']");
    private final By captchaText = By.xpath("//input[@formcontrolname='captcha1']");

    private final By pageError = By.xpath("//p[contains(@class,'notifier__notification-message')]");
    private final By BackButton = By.xpath("//signup-header//i");
    private final By pageErrorCloseButton = By.xpath("//button[contains(@class,'notifier__notification-button')]");




    public void fillData(Map<String, String> data){
        // company ID
        String selectAll = Keys.chord(Keys.CONTROL,"a");
        driver.findElement(companyIDField).sendKeys(selectAll + Keys.BACK_SPACE);
        //driver.findElement(companyIDField).clear();
        if(data.get("company_id") != null){
            driver.findElement(companyIDField).sendKeys(data.get("company_id"));
        }
        // tmp solution to click anywhere on page, because CompanyID is affecting other fields if it wasn't verified from Database
        driver.findElement(By.xpath("//html")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // company Name
        driver.findElement(companyNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        //driver.findElement(companyNameField).clear();
        if(data.get("company_name") != null){
            driver.findElement(companyNameField).sendKeys(data.get("company_name"));
        }

        // mobile Number
        driver.findElement(mobileNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        //driver.findElement(mobileNumberField).clear();
        if(data.get("mobile_number") != null){
            driver.findElement(mobileNumberField).sendKeys(data.get("mobile_number"));

        }

        // email
        //driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("email") != null){
            driver.findElement(emailField).sendKeys(data.get("email"));
        }

        // confirm Email
        //driver.findElement(confirmEmailField).clear();
        driver.findElement(confirmEmailField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("confirm_email") != null){
            driver.findElement(confirmEmailField).sendKeys(data.get("confirm_email"));
        }


        // captcha
        driver.findElement(captchaField).sendKeys(selectAll + Keys.BACK_SPACE);
        //driver.findElement(captchaField).clear();
        if(data.get("captcha") != null){
            if(data.get("captcha").equals("actual_captcha")){
                String captcha = driver.findElement(captchaText).getAttribute("value");
                driver.findElement(captchaField).sendKeys(captcha);
            } else {
                driver.findElement(captchaField).sendKeys(data.get("captcha"));
            }
        }
    }

    public boolean checkPage(){
        return driver.findElement(companyIDField).isDisplayed();
    }

    public void clickBackButton(){
        driver.findElement(BackButton).click();
    }

    public void clickRefreshButton(){
        driver.findElement(refreshCodeButton).click();
    }
    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }


    public String getCaptchaText(){
        return driver.findElement(captchaText).getAttribute("value");
    }
    public void enterCaptchaField(String captcha){
        driver.findElement(captchaField).sendKeys(captcha);
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

    public String getFieldsLabels(String key){
        WebElement element_label = switch(key){
                case "company_id" -> driver.findElement(with(label).above(companyIDField));
                case "company_name" -> driver.findElement(with(label).above(companyNameField));
                case "mobile_number" -> driver.findElement(with(label).above(mobileNumberField));
                case "email" -> driver.findElement(with(label).above(emailField));
                case "confirm_email" -> driver.findElement(with(label).above(confirmEmailField));
                case "captcha" -> driver.findElement(with(label).above(verificationCodeField));
                default -> null;
            };
        // return label of the first element if any
        if(!element_label.getText().isEmpty()) {
            return element_label.getText();
        }
        return null;
    }

}
