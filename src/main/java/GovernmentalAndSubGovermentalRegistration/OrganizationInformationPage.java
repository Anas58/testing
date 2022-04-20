package GovernmentalAndSubGovermentalRegistration;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class OrganizationInformationPage {

    WebDriver driver;


    //Sign Up Button
    private final By signUpButton = By.xpath("(//a[@class = 'Dont-have-an-account'])[2]");

    //Other Button
    private final By otherButton = By.xpath("(//p[@class = 'insideCard'])[4]");

    //Governmental and Sub governmental Button
    private final By govtAndSubGovernmentalButton = By.xpath("(//div[@class = 'col-md-3 mt-4 mb-4'])[3]");

    //Labels
    private final By label = By.className("registerLabelCss");

    //Company ID Field
    private final By companyIDField = By.name("cId");

    //Government Agency Name Field
    private final By govtAgencyNameField = By.xpath("//input[@name = 'gName']");

    //TIN Number
    private final By tinNumber = By.xpath("//input[@name = 'num1']");

    //Mobile Number Field
    private final By mobileNumberField = By.xpath("//input[@mask]");

    //Contact Information Email Field
    private final By emailField = By.name("email");

    //Contact Information Confirm Email Field
    private final By confirmEmailField = By.name("cemail");

    //Contact Information Verification Code Field
    private final By verificationCodeField = By.xpath("//mat-form-field//input[@name='cemail']/ancestor::div/following-sibling::div/child::mat-form-field//input[@type]");

    //Contact Information - Renew Verification Code
    private final By renewVerificationCode = By.xpath("//mat-icon[contains(@class, 'renewIcon')]");

    //Contact Information - Captcha
    private final By captchaText = By.xpath("//mat-form-field[@id='captcha1']//input");

    //Continue
    private final By continueButton = By.xpath("//app-government//button");

    //Field Error Messages
    private final By fieldErrorMessages = By.xpath("//div[contains(@class,'invalid-feedback')]");

    //Page Error element
    private final By pageError = By.xpath("//notifier-notification[contains(@class,'notifier__notification')]");

    //Close button for page notification messages
    private final By pageErrorCloseButton = By.xpath("//button[contains(@class,'notifier__notification-button')]");
    private String storePopupMessage;



    public OrganizationInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    //This clicks the SignUp Button on Angular homepage
    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    //Clicks the other button
    public void clickOtherButton() {
        driver.findElement(otherButton).click();
    }

    //Clicks the Governmental and sub governmental button
    public void clickGovtAndSubGovernmentalButton() {
        driver.findElement(govtAndSubGovernmentalButton).click();

    }

    public String getCompanyIdLabel(){
        return driver.findElement(with(label).above(companyIDField)).getText();

    }
    public String getGovtAgencyNameLabel(){
        return driver.findElement(with(label).above(govtAgencyNameField)).getText();

    }
    public String getMobileNumberLabel(){
        return driver.findElement(with(label).above(mobileNumberField)).getText();

    }

    public String getEmailLabel(){
        return driver.findElement(with(label).above(emailField)).getText();

    }

    public String getConfirmEmailLabel(){
        return driver.findElement(with(label).above(confirmEmailField)).getText();

    }

    public String getVerificationCodeLabel(){
        return driver.findElement(with(label).above(verificationCodeField)).getText();

    }


    public boolean verifyTinNumberIsDisplayed() {
        return driver.findElement(tinNumber).isDisplayed();

    }

    //Inputs data from the excel into the company ID, Government agency name, mobile number, email and confirm email fields.
    public void fillData(Map<String, String> data) {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(companyIDField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("company_id") != null) {
            driver.findElement(companyIDField).sendKeys(data.get("company_id") + Keys.TAB);

        }
//        driver.findElement(By.xpath("//html")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!isElementDisabled(govtAgencyNameField)) {
            driver.findElement(govtAgencyNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if (data.get("government_agency_name") != null) {
            if (!isElementDisabled(govtAgencyNameField)) {
                driver.findElement(govtAgencyNameField).sendKeys(data.get("government_agency_name"));
            }
        }

        driver.findElement(mobileNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("mobile_number") != null) {
            driver.findElement(mobileNumberField).sendKeys(data.get("mobile_number"));
        }

        driver.findElement(emailField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("email") != null) {
            driver.findElement(emailField).sendKeys(data.get("email"));
        }

        driver.findElement(confirmEmailField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("confirm_email") != null) {
            driver.findElement(confirmEmailField).sendKeys(data.get("confirm_email"));
        }

        driver.findElement(verificationCodeField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("captcha") != null){
            if(data.get("captcha").equals("actual_captcha")){
                String captcha = driver.findElement(captchaText).getAttribute("value");
                driver.findElement(verificationCodeField).sendKeys(captcha);
            } else {
                driver.findElement(verificationCodeField).sendKeys(data.get("captcha"));
            }
        }
    }

    //This stores the captcha text
    public String getCaptchatext() {
        return driver.findElement(captchaText).getAttribute("value");

    }

    //Clicks the renew verification code button
    public void clickRenewVerificationCode() {
        driver.findElement(renewVerificationCode).click();

    }

    //This enters the verification code
    public void enterVerificationCode(String codeAfterRenew) {
        driver.findElement(verificationCodeField).clear();
        driver.findElement(verificationCodeField).sendKeys(codeAfterRenew);

    }

    //This clicks the continue button
    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    //This gets the errors on each of the fields
    public String getFieldErrorMsg(Map<String, String> errorData) {
        List<WebElement> fieldErrors = switch (errorData.get("error_element")) {
            case "company_id" -> driver.findElements(with(fieldErrorMessages).below(companyIDField));
            case "government_agency_name" -> driver.findElements(with(fieldErrorMessages).below(govtAgencyNameField));
            case "mobile_number" -> driver.findElements(with(fieldErrorMessages).below(mobileNumberField));
            case "email" -> driver.findElements(with(fieldErrorMessages).below(emailField));
            case "confirm_email" -> driver.findElements(with(fieldErrorMessages).below(confirmEmailField));
            case "Verification_Code" -> driver.findElements(with(fieldErrorMessages).below(verificationCodeField));
            default -> new ArrayList<>();

        };
        if (fieldErrors.size() > 0) {
            return fieldErrors.get(0).getText();
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
            case "company_ID" -> driver.findElement(with(label).above(companyIDField));
            case "government_agency_name" -> driver.findElement(with(label).above(govtAgencyNameField));
            case "mobile_number" -> driver.findElement(with(label).above(mobileNumberField));
            case "email" -> driver.findElement(with(label).above(emailField));
            case "confirm_email" -> driver.findElement(with(label).above(confirmEmailField));
            case "verification_code" -> driver.findElement(with(label).above(verificationCodeField));
            default -> null;
        };
        // return label of the first element if any
        if(!element_label.getText().isEmpty()) {
            return element_label.getText();
        }
        return null;
    }

    public String getStorePopupMessage(){
        return storePopupMessage;
    }

    public boolean isElementDisabled(By e) {
        String t = driver.findElement(e).getAttribute("outerHTML");
        return t.contains("disabled");
    }


}


