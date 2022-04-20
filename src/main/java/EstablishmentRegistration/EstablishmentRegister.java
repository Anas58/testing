package EstablishmentRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class EstablishmentRegister {
    WebDriver driver;
    public EstablishmentRegister(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By idTypeField = By.xpath("//mat-select[@formcontrolname='idType']");
    By options = By.tagName("mat-option");
    By idNumber = By.xpath("//input[@formcontrolname='idNumber']");
    By openCalender = By.xpath("//gazt-datepicker-toggle//span//*[name()='svg']");
    By openCalenderType = By.xpath("//mat-select[@placeholder='Calendar Format']");
    By changeCalenderType = By.xpath("//mat-option[1]//span[1]");
    By dateOfBirth = By.xpath("//input[@formcontrolname='dateOfBirth']");
    By clickOnPage = By.xpath("//html");
    By firstName = By.xpath("//input[@formcontrolname='firstName']");
    By lastName = By.xpath("//input[@formcontrolname='lastName']");
    By mobileNumber = By.xpath("//input[@formcontrolname='mobileNumber']");
    By email = By.xpath("//input[@formcontrolname='email']");
    By confirmEmail = By.xpath("//input[@formcontrolname='confirmEmail']");
    By captchaText = By.xpath("//input[@formcontrolname='captcha1']");
    By captchaValue = By.xpath("//input[@formcontrolname='captcha2']");
    By continueButton = By.xpath("//button[@class='rounded btnCss save-btn mat-focus-indicator mat-button mat-button-base']");
    private final By pageError = By.xpath("//p[contains(@class,'notifier__notification-message')]");
    private final By pageErrorCloseButton = By.xpath("//button[contains(@class,'notifier__notification-button')]");
    By closePageValidation = By.xpath("//button[@title='dismiss']//*[name()='svg']");
    //FOR IQAMA And GCC Locators (For Passport)
    By passportNumber = By.xpath("//input[@formcontrolname='passportNo']");
    By passportIssueCountry = By.xpath("//mat-form-field//mat-select[@formcontrolname='issueCountry']");
    By countryOption = By.tagName("mat-option");
    By passportIssueDate = By.xpath("//input[@formcontrolname='validFrom']");
    By passportExpiryDate = By.xpath("//input[@formcontrolname='validTo']");
    By copyOfPassportButton = By.xpath("//mat-form-field[contains(@class,'attachment-field')]");
    By copyOfPassportAttachment = By.xpath("//signup-attachments//input[contains(@type,'file')]");
    By continueButtonPassportAttachment = By.xpath("//button[@class='rounded btnCss mat-focus-indicator mat-button mat-button-base']");
    By removeAttachment = By.xpath("//div//img[@class='pointer-hand width-18']");
    //Message Locators
    By errorMessage = By.tagName("mat-error");
    //Labels Locators
    By fieldLabels = By.xpath("//div[contains(@class ,'registerLabelCss')]");
    By refreshIcon = By.xpath("//mat-icon[@class='cursor-pointer mat-icon notranslate material-icons mat-icon-no-color renewIcon']");
    //Page Back Button
    By backButton = By.xpath("//div[@class='pointer-hand text-left']");
    String tpFullName;

    //Fill data on page.
    public void fillData(Map<String, String> data) {
        //Select ID Type
        if (data.get("id_type") != null) {
            if(data.get("test_case_type")!= "no"){
            selectIdType(data.get("id_type"));
        }
        }
        //ID Number
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(idNumber).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("id_number") != null)
        {
            driver.findElement(idNumber).sendKeys(data.get("id_number"));
        }
        //Date of Birth
        driver.findElement(dateOfBirth).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("date_of_birth") != null) {
            changeCalendarType();
            driver.findElement(dateOfBirth).sendKeys(data.get("date_of_birth"));
            //driver.findElement(clickOnPage).click();

        }
        //First Name
        driver.findElement(firstName).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("first_name") != null) {
            driver.findElement(firstName).click();
            driver.findElement(firstName).sendKeys(data.get("first_name"));
        }
        //Last Name
        driver.findElement(lastName).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("last_name") != null) {
            if (driver.findElement(lastName).isEnabled()) {
                driver.findElement(lastName).sendKeys(data.get("last_name"));
            }
        }

        //Enter Passport Number
        if (data.get("id_type") != null && !data.get("id_type").equals("National ID")) {
            //driver.findElement(passportNumber).sendKeys(selectAll + Keys.BACK_SPACE);
            driver.findElement(passportNumber).sendKeys(selectAll + Keys.BACK_SPACE);
            if (data.get("passport_number") != null) {
                driver.findElement(passportNumber).sendKeys(data.get("passport_number"));
            }
            //Select Passport issue Country
            if (data.get("issue_country") != null) {
                //driver.findElement(passportIssueCountry).sendKeys(selectAll + Keys.BACK_SPACE);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                selectCountry((data.get("issue_country")));

            }

            //Select Passport Issue Date
            driver.findElement(passportIssueDate).sendKeys(selectAll + Keys.BACK_SPACE);
            if (data.get("issue_date") != null) {
                driver.findElement(passportIssueDate).sendKeys(data.get("issue_date"));
            }
            //Select Passport Expiry Date
            driver.findElement(passportExpiryDate).sendKeys(selectAll + Keys.BACK_SPACE);
            if (data.get("expiry_date") != null) {
                driver.findElement(passportExpiryDate).sendKeys(data.get("expiry_date"));
            }
            //Uploading Attachment
            if (data.get("copy_of_passport") != null) {
                driver.findElement(copyOfPassportButton).click();
                if (removeAttachment()) {
                    driver.findElement(removeAttachment).click();
                }
                uploadAttachment(copyOfPassportAttachment, data.get("copy_of_passport"));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //Mobile Number
        driver.findElement(mobileNumber).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("mobile_number") != null) {

            driver.findElement(mobileNumber).click();
            driver.findElement(mobileNumber).sendKeys(data.get("mobile_number"));
        }
        //Email
        driver.findElement(email).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("email") != null) {

            driver.findElement(email).sendKeys(data.get("email"));
        }
        //ConfirmEmail
        driver.findElement(confirmEmail).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("confirm_email") != null) {

            driver.findElement(confirmEmail).sendKeys(data.get("confirm_email"));
        }
        //Captcha
        driver.findElement(captchaValue).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("verification_code") != null) {
            if (data.get("verification_code").equals("Actual")) {
                String captcha = driver.findElement(captchaText).getAttribute("value");
                driver.findElement(captchaValue).sendKeys(captcha);
            } else {
                driver.findElement(captchaValue).sendKeys(data.get("verification_code"));
            }
        }

    }

    //Method to Click Continue Button
    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public void backButton() {
        driver.findElement(backButton).click();

    }

    // Method select ID Type
    public boolean selectIdType(String value) {
        return selectFromList(idTypeField, value);
    }

    //Method Select Country
    public boolean selectCountry(String value) {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(passportIssueCountry));
        return selectFromList(passportIssueCountry, value);
    }

    //Method to Read Error Messages;
    public String fieldValidateErrorMessage(Map<String, String> errorData) {
        List<WebElement> error = switch (errorData.get("error_element")) {
            case "id_type" -> driver.findElements(with(errorMessage).below(idTypeField));
            case "id_number" -> driver.findElements(with(errorMessage).below(idNumber));
            case "date_of_birth" -> driver.findElements(with(errorMessage).below(dateOfBirth));
            case "first_name" -> driver.findElements(with(errorMessage).below(firstName));
            case "last_name" -> driver.findElements(with(errorMessage).below(lastName));
            case "passport_number" -> driver.findElements(with(errorMessage).below(passportNumber));
            case "issue_country" -> driver.findElements(with(errorMessage).below(passportIssueCountry));
            case "issue_date" -> driver.findElements(with(errorMessage).below(passportIssueDate));
            case "expiry_date" -> driver.findElements(with(errorMessage).below(passportExpiryDate));
            case "copy_of_passport" -> driver.findElements(with(errorMessage).below(copyOfPassportButton));
            case "mobile_number" -> driver.findElements(with(errorMessage).below(mobileNumber));
            case "email" -> driver.findElements(with(errorMessage).below(email));
            case "confirm_email" -> driver.findElements(with(errorMessage).below(confirmEmail));
            case "verification_code" -> driver.findElements(with(errorMessage).below(captchaText));
            default -> new ArrayList<>();
        };
        if (error.size() > 0) {
            return error.get(0).getText();
        }
        return null;
    }

    //Method to verify page level message
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

    //Method to verify Labels
    public String fieldsLabel(String idType, String key) {
        List<WebElement> list;
        if (idType.equals("National ID")) {
            list = switch (key) {
                case "id_type_field" -> driver.findElements(with(fieldLabels).above(idTypeField));
                case "id_number" -> driver.findElements(with(fieldLabels).above(idNumber));
                case "date_of_birth" -> driver.findElements(with(fieldLabels).above(dateOfBirth));
                case "first_name" -> driver.findElements(with(fieldLabels).above(firstName));
                case "last_name" -> driver.findElements(with(fieldLabels).above(lastName));
                case "mobile_number" -> driver.findElements(with(fieldLabels).above(mobileNumber));
                case "email" -> driver.findElements(with(fieldLabels).above(email));
                case "confirm_email" -> driver.findElements(with(fieldLabels).above(confirmEmail));
                case "verification_code" -> driver.findElements(with(fieldLabels).above(captchaValue));
                default -> new ArrayList<>();
            };
        } else {
            list = switch (key) {
                case "id_type_field" -> driver.findElements(with(fieldLabels).above(idTypeField));
                case "id_number" -> driver.findElements(with(fieldLabels).above(idNumber));
                case "date_of_birth" -> driver.findElements(with(fieldLabels).above(dateOfBirth));
                case "first_name" -> driver.findElements(with(fieldLabels).above(firstName));
                case "last_name" -> driver.findElements(with(fieldLabels).above(lastName));
                case "passport_number" -> driver.findElements(with(fieldLabels).above(passportNumber));
                case "issue_country" -> driver.findElements(with(fieldLabels).above(passportIssueCountry));
                case "issue_date" -> driver.findElements(with(fieldLabels).above(passportIssueDate));
                case "expiry_date" -> driver.findElements(with(fieldLabels).above(passportExpiryDate));
                case "copy_of_passport" -> driver.findElements(with(fieldLabels).above(copyOfPassportButton));
                case "mobile_number" -> driver.findElements(with(fieldLabels).above(mobileNumber));
                case "email" -> driver.findElements(with(fieldLabels).above(email));
                case "confirm_email" -> driver.findElements(with(fieldLabels).above(confirmEmail));
                case "verification_code" -> driver.findElements(with(fieldLabels).above(captchaValue));
                default -> new ArrayList<>();
            };
        }

        if (list.size() > 0) {
            return list.get(0).getText();
        }
        return null;
    }


    //Method to refresh Captcha
    public void refreshCaptcha() {
        driver.findElement(refreshIcon).click();
    }

    //Method store Captcha value
    public String storeCaptcha() {
        return driver.findElement(captchaText).getAttribute("value");

    }

    //Method to select value from Dropdown
    public boolean selectFromList(By element, String value) {
        driver.findElement(element).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> alldropdownoption = driver.findElements(options);
        for (int i = 0; i <= alldropdownoption.size(); i++) {
            if (alldropdownoption.get(i).getText().equals(value)) {
                alldropdownoption.get(i).click();
                return true;
            }
        }
        return false;
    }

    //Method to upload Document
    public void uploadAttachment(By file, String fileName) {

        File path = new File(fileName);
        driver.findElement(file).sendKeys(path.getAbsolutePath());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(continueButtonPassportAttachment).click();
    }

    //Method Change Calendar Type
    public void changeCalendarType() {
        driver.findElement(openCalender).click();
        driver.findElement(openCalenderType).click();
        driver.findElement(changeCalenderType).click();

    }

    //Method Select Date
    /*public void selectdate(String datevalue) {
        driver.findElement(dateOfBirth).sendKeys(datevalue);
        //driver.findElement(dateofBirth).click();
    }*/
    //Method Check ID type is visible or not On Page
    public boolean checkPage() {
        return driver.findElement(idTypeField).isDisplayed();
    }

    public boolean removeAttachment() {
        List<WebElement> deleteButtons = driver.findElements(removeAttachment);
        if (deleteButtons.size() > 0) {
            return true;
        }
        return false;
    }

    //Method to close Page validation
    public void closePageValidation() {
        driver.findElement(closePageValidation).click();
    }
    //Store First And Last Name
    public void storeName(){
       String first= driver.findElement(firstName).getAttribute("value");
        String last=driver.findElement(lastName).getAttribute("value");
        tpFullName= String.join(" ", first, last);
        System.out.println(tpFullName);
    }
    public String getTpFullName(){
        return tpFullName;
    }


}


