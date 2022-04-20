package NonRegularTaxpayer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    //Organization Information
    private final By companyNameField = By.xpath("//input[@formcontrolname='nameBCE']");
    private final By gccIDField = By.xpath("//input[@formcontrolname='gccId']");
    private final By crNumberField = By.xpath("//input[@formcontrolname='cr']");
    private final By crAttachmentButton = By.xpath("//input[@formcontrolname='copyOfCommercialNumberFile']/following-sibling::span[contains(@class, 'icons')]");
    private final By crAttachment = By.xpath("//signup-attachments//input[@type='file']");
    private final By attachmentContinueButton = By.xpath("//signup-attachments//button[contains(@class, 'mat-button')]");
    private final By businessDescriptionField = By.xpath("//textarea[@formcontrolname='description']");

    //Address in GCC State
    private final By gccCountryList = By.xpath("//mat-select[@formcontrolname='country']");
    private final By gccCityField = By.xpath("//input[@formcontrolname='city']");
    private final By streetNameField = By.xpath("//input[@formcontrolname='streetName']");
    private final By houseNumberField = By.xpath("//input[@formcontrolname='buildingNumber']");
    private final By poBoxField = By.xpath("//input[@formcontrolname='additionalNumber']");
    private final By postalCodeField = By.xpath("//input[@formcontrolname='zipCode']");
    private final By gccPhoneNumberField = By.xpath("//input[@formcontrolname='phoneNumber']");
    private final By companyWebsiteField = By.xpath("//input[@formcontrolname='website']");

    //Representative Information
    private final By designationField = By.xpath("//input[@formcontrolname='designation']");
    private final By idTypeList = By.xpath("//mat-select[@formcontrolname='idType']");
    private final By idNumberField = By.xpath("//input[@formcontrolname='idNumber']");
    private final By dateOfBirth = By.xpath("//input[@formcontrolname='dateOfBirth']");
    private final By nameField = By.xpath("//input[@formcontrolname='firstName']");
    private final By nationalityList = By.xpath("//mat-select[@formcontrolname='country1']");
    private final By mobileNumberField = By.xpath("//input[@formcontrolname='mobileNumber']");
    private final By emailField = By.xpath("//input[@formcontrolname='email']");
    private final By confirmEmailField = By.xpath("//input[@formcontrolname='confirmEmail']");
    private final By copyOfIDAttachmentButton = By.xpath("//input[@formcontrolname='copyOfGMID']/following-sibling::span[contains(@class, 'icons')]");
    private final By copyOfIDAttachment = By.xpath("//signup-attachments//input[@type='file']");
    private final By refreshCodeButton = By.cssSelector(".renewIcon");

    private final By errorMessage = By.tagName("mat-error");
    private final By continueButton = By.xpath("//signup-create-password/preceding::div/child::button");
    private final By label = By.xpath("//div[@class='registerLabelCss required']");
    private final By captchaField = By.xpath("//input[@formcontrolname='captcha2']");
    private final By captchaText = By.xpath("//input[@formcontrolname='captcha1']");

    private final By pageError = By.xpath("//notifier-notification[contains(@class,'notifier__notification')]");
    private final By BackButton = By.xpath("//i[contains(@class, 'arrow-left')]");
    private final By pageErrorCloseButton = By.xpath("//button[contains(@class,'notifier__notification-button')]");
    private final By options = By.tagName("mat-option");
    private final By tmp = By.xpath("//mat-select[@formcontrolname='country']//span");
    private String storePopupMessage;




    public void fillData(Map<String, String> data){
        String selectAll = Keys.chord(Keys.CONTROL,"a");
        driver.findElement(companyNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("company_name") != null){
            driver.findElement(companyNameField).sendKeys(data.get("company_name"));
        }
        // tmp solution to click anywhere on page, because element is affecting other fields if it wasn't verified from Database
        driver.findElement(By.xpath("//html")).click();

        driver.findElement(gccIDField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("ggc_ID") != null){
            driver.findElement(gccIDField).sendKeys(data.get("ggc_ID"));
        }

        driver.findElement(crNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("CR_Number") != null){
            driver.findElement(crNumberField).sendKeys(data.get("CR_Number"));
        }

        if (data.get("CR_attachment") != null) {
//            driver.findElement(By.xpath("//html")).click();
            File file = new File(data.get("CR_attachment"));
            driver.findElement(crAttachmentButton).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(crAttachment).sendKeys(file.getAbsolutePath());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(attachmentContinueButton).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        driver.findElement(businessDescriptionField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("business_description") != null){
            driver.findElement(businessDescriptionField).sendKeys(data.get("business_description"));
        }

        if (data.get("gcc_country") != null) {
            selectFromList(gccCountryList, data.get("gcc_country"));
            driver.findElement(By.xpath("//html")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        driver.findElement(gccCityField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("gcc_city") != null){
            driver.findElement(gccCityField).sendKeys(data.get("gcc_city"));
        }

        driver.findElement(streetNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("street_name") != null){
            driver.findElement(streetNameField).sendKeys(data.get("street_name"));
        }

        driver.findElement(houseNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("house_number") != null){
            driver.findElement(houseNumberField).sendKeys(data.get("house_number"));
        }

        driver.findElement(poBoxField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("PO_box") != null){
            driver.findElement(poBoxField).sendKeys(data.get("PO_box"));
        }

        driver.findElement(postalCodeField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("postal_code") != null){
            driver.findElement(postalCodeField).sendKeys(data.get("postal_code"));
        }

        driver.findElement(gccPhoneNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("phone_number") != null){
            driver.findElement(gccPhoneNumberField).sendKeys(data.get("phone_number"));
        }

        driver.findElement(companyWebsiteField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("company_website") != null){
            driver.findElement(companyWebsiteField).sendKeys(data.get("company_website"));
        }

        driver.findElement(designationField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("designation") != null){
            driver.findElement(designationField).sendKeys(data.get("designation"));
        }

        if(data.get("id_type") != null) {
            selectFromList(idTypeList, data.get("id_type"));
            driver.findElement(By.xpath("//html")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        driver.findElement(idNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("id_number") != null){
            driver.findElement(idNumberField).sendKeys(data.get("id_number"));
            driver.findElement(By.xpath("//html")).click();
        }

        if (!isElementDisabled(dateOfBirth)) {
            driver.findElement(dateOfBirth).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if (data.get("DOB") != null) {
            if (!isElementDisabled(dateOfBirth)) {
                driver.findElement(dateOfBirth).sendKeys(data.get("DOB"));
                driver.findElement(By.xpath("//html")).click();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storePopupMessage = getPageErrorMessage();
            }
        }

        if (!isElementDisabled(nameField)) {
            driver.findElement(nameField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if(data.get("name") != null){
            if (!isElementDisabled(nameField)) {
                driver.findElement(nameField).sendKeys(data.get("name"));
            }
        }

        if(data.get("nationality") != null) {
            selectFromList(nationalityList, data.get("nationality"));
            driver.findElement(By.xpath("//html")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

        if (data.get("copyOfID") != null) {
//            driver.findElement(By.xpath("//html")).click();
            File file = new File(data.get("copyOfID"));
            driver.findElement(copyOfIDAttachmentButton).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(copyOfIDAttachment).sendKeys(file.getAbsolutePath());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(attachmentContinueButton).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        driver.findElement(captchaField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("captcha") != null){
            if(data.get("captcha").equals("actual_captcha")){
                String captcha = driver.findElement(captchaText).getAttribute("value");
                driver.findElement(captchaField).sendKeys(captcha);
            } else {
                driver.findElement(captchaField).sendKeys(data.get("captcha"));
            }
            driver.findElement(By.xpath("//html")).click();
        }
    }

    public String getStorePopupMessage(){
        return storePopupMessage;
    }

    public boolean checkPage(){
        return driver.findElement(companyNameField).isDisplayed();
    }

    public boolean selectFromList(By e, String value) {
        WebElement element = driver.findElement(e);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        List<WebElement> allOptions = driver.findElements(options);
        if(!value.equals("empty")){ //if to click only on the list without selecting any value to trigger the error message.
            for (int i = 0; i <= allOptions.size() - 1; i++) {
                if (allOptions.get(i).getText().equals(value)) {
                    allOptions.get(i).click();
                    return true;
                }
            }
        }
        return false;
    }
    public void clickBackButton(){
        driver.findElement(BackButton).click();
    }

    public void clickRefreshButton(){
        driver.findElement(refreshCodeButton).click();
    }
    public void clickContinueButton(){
        String disabled = driver.findElement(continueButton).getAttribute("disabled");
        if(disabled == null){
            driver.findElement(continueButton).click();
        }
    }
    public boolean isElementDisabled(By e) {
        String t = driver.findElement(e).getAttribute("outerHTML");
        return t.contains("disabled");
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
            case "company_name" -> driver.findElements(with(errorMessage).near(companyNameField, 50));
            case "ggc_ID" -> driver.findElements(with(errorMessage).near(gccIDField, 50));
            case "CR_Number" -> driver.findElements(with(errorMessage).near(crNumberField, 50));
            case "CR_attachment" -> driver.findElements(with(errorMessage).near(crAttachment, 50));
            case "business_description" -> driver.findElements(with(errorMessage).near(businessDescriptionField, 80));
            case "gcc_country" -> driver.findElements(with(errorMessage).near(gccCountryList,50));
            case "gcc_city" -> driver.findElements(with(errorMessage).near(gccCityField,50));
            case "street_name" -> driver.findElements(with(errorMessage).near(streetNameField, 50));
            case "house_number" -> driver.findElements(with(errorMessage).near(houseNumberField,50));
            case "PO_box" -> driver.findElements(with(errorMessage).near(poBoxField,50));
            case "postal_code" -> driver.findElements(with(errorMessage).near(postalCodeField,50));
            case "phone_number" -> driver.findElements(with(errorMessage).near(gccPhoneNumberField, 50));
            case "company_website" -> driver.findElements(with(errorMessage).near(companyWebsiteField,50));
            case "designation" -> driver.findElements(with(errorMessage).near(designationField,80));
            case "id_type" -> driver.findElements(with(errorMessage).near(idTypeList,50));
            case "id_number" -> driver.findElements(with(errorMessage).near(idNumberField, 50));
            case "DOB" -> driver.findElements(with(errorMessage).near(dateOfBirth,50));
            case "name" -> driver.findElements(with(errorMessage).near(nameField,50));
            case "nationality" -> driver.findElements(with(errorMessage).near(nationalityList,50));
            case "mobile_number" -> driver.findElements(with(errorMessage).near(mobileNumberField, 50));
            case "email" -> driver.findElements(with(errorMessage).near(emailField,50));
            case "confirm_email" -> driver.findElements(with(errorMessage).near(confirmEmailField,50));
            case "copyOfID" -> driver.findElements(with(errorMessage).near(copyOfIDAttachment,50));
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
            //close the popup message
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
                //case "company_id" -> driver.findElement(with(label).above(companyIDField));
                //case "company_name" -> driver.findElement(with(label).above(companyNameField));
                case "mobile_number" -> driver.findElement(with(label).above(mobileNumberField));
                case "email" -> driver.findElement(with(label).above(emailField));
                case "confirm_email" -> driver.findElement(with(label).above(confirmEmailField));
                case "captcha" -> driver.findElement(with(label).above(captchaField));
                default -> null;
            };
        // return label of the first element if any
        if(!element_label.getText().isEmpty()) {
            return element_label.getText();
        }
        return null;
    }

}
