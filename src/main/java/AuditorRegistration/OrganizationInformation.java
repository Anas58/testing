package AuditorRegistration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class OrganizationInformation {
    WebDriver driver;

    public OrganizationInformation(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameField = By.xpath("//input[@formcontrolname='name']");
    private final By tinField = By.xpath("//input[@formcontrolname='tin']");
    private final By licenseNumberField = By.xpath("//input[@formcontrolname='licenseNo']");
    private final By licenseIssuedByList = By.xpath("//mat-select[@formcontrolname='licenseBy']");
    private final By licenseIssuedCityList = By.xpath("//mat-select[@formcontrolname='licenseCity']");
    private final By copyOfPracticingCertificateButton = By.xpath("//input[@formcontrolname='attach1']/parent::label");
    private final By attachment = By.xpath("//signup-auditor-attachment//input[contains(@type,'file')]");
    private final By attachmentContinueButton = By.xpath("//signup-auditor-attachment//button");
    private final By copyOfLicenseButton = By.xpath("//input[@formcontrolname='attach2']/parent::label");
    private final By copyOfPracticingCertificateButton2 = By.xpath("(//signup-auditor-attachment/preceding::p[@class='fileName']/parent::div/parent::div)[1]"); // xpath of the attachment button is changed after uploading once, to re-click use this xpath
    private final By copyOfLicenseButton2 = By.xpath("(//signup-auditor-attachment/preceding::p[@class='fileName']/parent::div/parent::div)[2]"); // same with above, xpath changed
    private final By deleteAttachmentButton = By.xpath("//signup-auditor-attachment//mat-card//img[contains(@src,'delete')]");
    private final By tmp = By.xpath("//signup-auditor-attachment/preceding::p[@class='fileName']/parent::div/parent::div");



    private final By mobileNumberField = By.xpath("//input[@formcontrolname='mobile']");
    private final By emailField = By.xpath("//input[@formcontrolname='email']");
    private final By confirmEmailField = By.xpath("//input[@formcontrolname='cemail']");
    private final By verificationCodeField = By.xpath("//input[@formcontrolname='captcha2']");
    private final By refreshCodeButton = By.cssSelector(".renewIcon");

    private final By errorMessage2 = By.tagName("mat-error");
    private final By errorMessage = By.xpath("//div[@class ='ng-star-inserted']");
    private final By continueButton = By.xpath("//app-auditor//form//button");
    private final By label = By.xpath("//div[@class='registerLabelCss required']");
    private final By captchaField = By.xpath("//input[@formcontrolname='captcha2']");
    private final By captchaText = By.xpath("//input[@formcontrolname='captcha1']");

    private final By pageError = By.xpath("//notifier-notification[contains(@class,'notifier__notification')]");
    private final By backButton = By.xpath("//i[contains(@class, 'arrow-left')]");
    private final By pageErrorCloseButton = By.xpath("//button[contains(@class,'notifier__notification-button')]");
    private final By options = By.tagName("mat-option");




    public void fillData(Map<String, String> data){
        String selectAll = Keys.chord(Keys.CONTROL,"a");
        driver.findElement(tinField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("TIN") != null){
            driver.findElement(tinField).sendKeys(data.get("TIN"));
        }
        // tmp solution to click anywhere on page, because CompanyID is affecting other fields if it wasn't verified from Database
        driver.findElement(By.xpath("//html")).click();

        driver.findElement(nameField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("name") != null){
            driver.findElement(nameField).sendKeys(data.get("name"));
        }

        driver.findElement(licenseNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("license_number") != null){
            driver.findElement(licenseNumberField).sendKeys(data.get("license_number"));
        }

        if(data.get("licenseNumberIssuer") != null) {
            selectFromList(licenseIssuedByList, data.get("licenseNumberIssuer"));
        }

        if(data.get("licenseNumberIssuedCity") != null) {
            selectFromList(licenseIssuedCityList, data.get("licenseNumberIssuedCity"));
        }

        if (data.get("copyOfLicense") != null) {
            if(isCopyOfLicenseButtonVisible()) {
                driver.findElement(copyOfLicenseButton).click();
                driver.findElement(attachment).sendKeys(data.get("copyOfLicense"));
                driver.findElement(attachmentContinueButton).click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                driver.findElement(copyOfLicenseButton2).click();
                driver.findElement(deleteAttachmentButton).click();
                File file = new File("/dummy.pdf");
                driver.findElement(attachment).sendKeys(file.getAbsolutePath());
                driver.findElement(attachmentContinueButton).click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }else{
            List<WebElement> deleteList = driver.findElements(tmp);
            if (deleteList.size() > 0){
                deleteList.get(0).click();
                driver.findElement(deleteAttachmentButton).click();
                driver.findElement(attachmentContinueButton).click();
            }
        }

        if (data.get("copyOfPracticingCertificate") != null) {
            if(isCopyOfPracticingCertificateButtonVisible()) {
                driver.findElement(copyOfPracticingCertificateButton).click();
                driver.findElement(attachment).sendKeys(data.get("copyOfPracticingCertificate"));
                driver.findElement(attachmentContinueButton).click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                driver.findElement(copyOfPracticingCertificateButton2).click();
                driver.findElement(deleteAttachmentButton).click();
                File file = new File("/dummy2.pdf");
                driver.findElement(attachment).sendKeys(file.getAbsolutePath());
                driver.findElement(attachmentContinueButton).click();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }else{
            List<WebElement> deleteList = driver.findElements(tmp);
            if (deleteList.size() > 0){
                deleteList.get(0).click();
                driver.findElement(deleteAttachmentButton).click();
                driver.findElement(attachmentContinueButton).click();
            }
        }


        // mobile Number
        driver.findElement(mobileNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("mobile_number") != null){
            driver.findElement(mobileNumberField).sendKeys(data.get("mobile_number"));

        }

        // email
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
        return driver.findElement(tinField).isDisplayed();
    }

    public boolean selectFromList(By e, String value) {
        WebElement element = driver.findElement(e);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        List<WebElement> allOptions = driver.findElements(options);
        for (int i = 0; i <= allOptions.size() - 1; i++) {
            if (allOptions.get(i).getText().equals(value)) {
                allOptions.get(i).click();
                return true;
            }
        }
        return false;
    }
    public void clickBackButton(){
        driver.findElement(backButton).click();
    }

    public void clickRefreshButton(){
        driver.findElement(refreshCodeButton).click();
    }
    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
    public boolean isCopyOfPracticingCertificateButtonVisible() {
        try {
            driver.findElement(copyOfPracticingCertificateButton);
        } catch (NoSuchElementException e){
            return false;
        }
        return true;
    }

    public boolean isCopyOfLicenseButtonVisible() {
        try {
            driver.findElement(copyOfLicenseButton);
        } catch (NoSuchElementException e){
            return false;
        }
        return true;
    }
    public void uploadCopyOfPracticingCertificate(Map<String, String> data){
        File file = new File(data.get("copyOfPracticingCertificate"));
        driver.findElement(copyOfPracticingCertificateButton).click();
        driver.findElement(attachment).sendKeys(file.getAbsolutePath());
        driver.findElement(attachmentContinueButton).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            case "licenseNumberIssuer" -> driver.findElements(with(errorMessage).near(licenseIssuedByList, 300));
            case "licenseNumberIssuedCity" -> driver.findElements(with(errorMessage).near(licenseIssuedCityList, 300));
            case "mobile_number" -> driver.findElements(with(errorMessage).near(mobileNumberField, 300));
            case "email" -> driver.findElements(with(errorMessage).near(emailField, 300));
            case "confirm_email" -> driver.findElements(with(errorMessage).near(confirmEmailField, 300));
            case "captcha" -> driver.findElements(with(errorMessage2).near(captchaField,300));
            case "copyOfPracticingCertificate" -> driver.findElements(with(errorMessage).near(copyOfPracticingCertificateButton, 450));
            case "copyOfLicense" -> driver.findElements(with(errorMessage).near(copyOfLicenseButton,450));
            case "TIN" -> driver.findElements(with(errorMessage).near(tinField, 300));
            case "name" -> driver.findElements(with(errorMessage).near(nameField,300));
            case "license_number" -> driver.findElements(with(errorMessage).near(licenseNumberField,300));
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
            // this page doesn't have close btn, that's why below code was commented
//            List<WebElement> errorMessagesCloseButton =  driver.findElements(pageErrorCloseButton);
//            for(int index = errorMessagesCloseButton.size() - 1; index >= 0; index--){
//                errorMessagesCloseButton.get(index).click();
//            }
            return errorMessage;
        }
        return null;
    }

    public String getFieldsLabels(String key){
        WebElement element_label = switch(key){
                //case "company_id" -> driver.findElement(with(label).above(companyIDField));
                //case "company_name" -> driver.findElement(with(label).above(companyNameField));
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
