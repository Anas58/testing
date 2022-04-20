package IndividualRegistration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ReviewInformationPage {


    WebDriver driver;

    //Valid ID Number Success Message
    private By pageSuccessMessage = By.xpath("//p[contains(@class, 'notifier__notification-message')]");

    //Individual Register Visible
    private By  reviewPageVisible = By.xpath("//mat-icon[contains(@class, 'renewIcon')]");

    //Labels on review information page
    private By label = By.className("registerLabelCss");

    //Errors on review information page
    private By reviewInformationFieldsErrorMsg = By.xpath("//div[contains(@class,'invalid-feedback')]");


    //Individual Register - Name field
    private By nameField = By.name("name");

    //National Address - City
    private By cityField = By.name("city2");
    private By cityTypeOption = By.tagName("mat-option");

    //National Address - Neighbourhood
    private By neighbourhoodField = By.name("DistrictName");

    //National Address - StreetName
    private By streetNameField = By.name("StreetName");

    //National Address - Postal Code
    private By postalCodeField = By.name("Zipcode");

    //National Address - Building number
    private By buildingNumberField = By.name("BuildingNo");

    //National Address - Unit Number
    private By unitNumberField = By.name("UnitNo");

    //Contact Information - Mobile Number
    private By mobileNumberField = By.name("MobileNo2");

    //Contact Information - Email
    private By emailField = By.xpath("(//input[@name='email'])[1]");

    //Contact Information - Confirm Email
    private By confirmEmailField = By.xpath("(//input[@name='email'])[2]");

    //Contact Information - Renew Verification Code
    private By renewVerificationCode = By.xpath("//mat-icon[contains(@class, 'renewIcon')]");

    //Contact Information - Captcha
    private By captchaText = By.xpath("//input[@class='w-0 mat-input-element mat-form-field-autofill-control cdk-text-field-autofill-monitored ng-untouched ng-pristine']");

    //Contact Information - Verification Code
    private By verificationCodeField = By.xpath("//mat-form-field[contains(@class, 'w-50 mat-form-field')]//input");

    //Continue
    private By continueButton = By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base btnCss']");

    //This is to go back to the Individual Register Page
    private By individualRegisterIcon = By.xpath("//i[contains(@class,  'fa fa-check-circle stepperIcon')]");

    private By clickIndividualButton = By.xpath("(//div[@class='card-body'])[1]");


    public ReviewInformationPage(WebDriver driver) {
        this.driver = driver;

    }

    public String getPageSuccessMessage() {

        return driver.findElement(pageSuccessMessage).getText();

    }

    public String getNameLabel() {
        return driver.findElement(with(label).above(nameField)).getText();
    }

    public String getCityLabel() {
        return driver.findElement(with(label).above(cityField)).getText();

    }

    public String getNeighbourhoodLabel() {
        return driver.findElement(with(label).above(neighbourhoodField)).getText();

    }

    public String getStreetNameLabel() {
        return driver.findElement(with(label).above(streetNameField)).getText();

    }

    public String getPostalCodeLabel() {
        return driver.findElement(with(label).above(postalCodeField)).getText();

    }

    public String getBuildingNumberLabel() {
        return driver.findElement(with(label).above(buildingNumberField)).getText();

    }

    public String getUnitNumberLabel() {
        return driver.findElement(with(label).above(unitNumberField)).getText();

    }

    public String getMobileNumberLabel() {
        return driver.findElement(with(label).above(mobileNumberField)).getText();

    }

    public String getEmailLabel() {
        return driver.findElement(with(label).above(emailField)).getText();

    }

    public String getConfirmEmailLabel() {

        return driver.findElement(with(label).above(confirmEmailField)).getText();

    }

    public String getVerificationCodeLabel() {
        return driver.findElement(with(label).above(verificationCodeField)).getText();

    }

    public boolean selectFromList(By e, String value) {
        WebElement element = driver.findElement(e);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        List<WebElement> allOptions = driver.findElements(cityTypeOption);
        for (int i = 0; i <= allOptions.size() - 1; i++) {
            if (allOptions.get(i).getText().equals(value)) {
                allOptions.get(i).click();
                return true;
            }
        }
        return false;
    }

    public void fillData(Map<String, String> data) {

        String selectAll = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(nameField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("name") != null) {
            driver.findElement(nameField).sendKeys(data.get("name"));
        }

        driver.findElement(cityField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("city") != null) {
            selectFromList(cityField, data.get("city"));
        }

        driver.findElement(neighbourhoodField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("neighbourhood") != null) {
            driver.findElement(neighbourhoodField).sendKeys(data.get("neighbourhood"));
        }


        driver.findElement(streetNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("street_name") != null) {
            driver.findElement(streetNameField).sendKeys(data.get("street_name"));
        }

        driver.findElement(postalCodeField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("postal_code") != null) {
            driver.findElement(postalCodeField).sendKeys(data.get("postal_code"));
        }

        driver.findElement(buildingNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("building_number") != null) {
            driver.findElement(buildingNumberField).sendKeys(data.get("building_number"));
        }

        driver.findElement(unitNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("unit_number") != null) {
            driver.findElement(unitNumberField).sendKeys(data.get("unit_number"));
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


    }

    public String getCaptchatext() {
        return driver.findElement(captchaText).getAttribute("value");

    }

    public void clickRenewVerificationCode() {
        driver.findElement(renewVerificationCode).click();

    }

    public void enterVerificationCode(String codeAfterRenew) {
        driver.findElement(verificationCodeField).clear();
        driver.findElement(verificationCodeField).sendKeys(codeAfterRenew);

    }


    public String getFieldLevelError(Map<String, String> errorData) {
        List<WebElement> fieldErrors = switch (errorData.get("error_element")) {
            case "name" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(nameField, 150));
            case "city" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(cityField, 150));
            case "neighbourhood" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(neighbourhoodField, 150));
            case "street_name" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(streetNameField, 150));
            case "postal_code" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(postalCodeField, 150));
            case "building_number" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(buildingNumberField, 150));
            case "unit_number" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(unitNumberField, 150));
            case "mobile_number" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(mobileNumberField, 150));
            case "email" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(emailField, 150));
            case "confirm_email" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(confirmEmailField, 150));
            case "verificationCode" -> driver.findElements(with(reviewInformationFieldsErrorMsg).near(verificationCodeField, 150));
            default -> new ArrayList<>();

        };
        if (fieldErrors.size() > 0) {
            return fieldErrors.get(0).getText();
        }
        return null;
    }

    public String getFieldsLabels(String key){
        WebElement element_label = switch(key){
            case "name" -> driver.findElement(with(label).above(nameField));
            case "city" -> driver.findElement(with(label).above(cityField));
            case "neighbourhood" -> driver.findElement(with(label).above(neighbourhoodField));
            case "street_name" -> driver.findElement(with(label).above(streetNameField));
            case "postal_code" -> driver.findElement(with(label).above(postalCodeField));
            case "building_number" -> driver.findElement(with(label).above(buildingNumberField));
            case "unit_number" -> driver.findElement(with(label).above(unitNumberField));
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


    public void clickContinueButton() {
        driver.findElement(continueButton).click();

    }

    public boolean verifyReviewPageIsVisible(){
        return driver.findElement(reviewPageVisible).isDisplayed();
    }
    public boolean existsElement(By element) {
        try {
            driver.findElement(element);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
    public boolean isReviewPageVisible(){
        boolean result = false;
        if(existsElement(neighbourhoodField)){
            result = driver.findElement(neighbourhoodField).isDisplayed();
        }
        return result;
    }

    public void clickIndividualRegisterIcon(){
        driver.findElement(individualRegisterIcon).click();
    }

    public void clickIndividualButton(){
        driver.findElement(clickIndividualButton).click();
    }

}


