package NonResidentCompanyRegistration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class AddNewShareholders {

    WebDriver driver;

    public AddNewShareholders(WebDriver driver){
        this.driver = driver;
    }

    private final By shareCapitalField = By.xpath("//input[@formcontrolname='shareCapital']");
    private final By startDateOfSharingCapitalField = By.xpath("//input[@formcontrolname='shareCapitalStartDate']");
    private final By shareProfitField = By.xpath("//input[@formcontrolname='shareProfit']");
    private final By startDateOfSharingProfitField = By.xpath("//input[@formcontrolname='shareProfitStartDate']");
    private final By shareholderDetailsTab = By.xpath("//mat-step-header[@id=\"cdk-step-label-2-1\"]");
    private final By shareholderTypeList = By.xpath("//mat-select[@formcontrolname='shareHolderType']");
    private final By tinNumberField = By.xpath("//form[@formgroupname='shareHolderPercentageDetails']//input[@formcontrolname='tinNumber']");
    private final By idTypeList = By.xpath("//form[@formgroupname='shareHolderPercentageDetails']//mat-select[@formcontrolname='idType']");
    private final By idNumberField = By.xpath("//form[@formgroupname='shareHolderPercentageDetails']//input[@formcontrolname='idNumber']");
    private final By companyNameField = By.xpath("//form[@formgroupname='shareHolderPercentageDetails']//input[@formcontrolname='companyName']");
    private final By dateOfBirthField = By.xpath("//form[@formgroupname='shareHolderPercentageDetails']//input[@formcontrolname='dateOfBirth']");
    private final By firstNameField = By.xpath("//form[@formgroupname='shareHolderPercentageDetails']//input[@formcontrolname='firstName']");
    private final By lastNameField = By.xpath("//form[@formgroupname='shareHolderPercentageDetails']//input[@formcontrolname='lastName']");
    private final By startDateField = By.xpath("//form[@formgroupname='shareHolderPercentageDetails']//input[@formcontrolname='startDate']");
    private final By attachmentButton = By.xpath("//form[@formgroupname='shareHolderPercentageDetails']//mat-form-field[contains(@class, 'attachment-field')]");
    private final By uploadAttachment = By.xpath("//div[@id='addShareholderDetailsModal']//signup-attachments//input[contains(@type,'file')]");
    private final By attachmentContinueButton = By.xpath("//div[@id='addShareholderDetailsModal']//button[@class='rounded btnCss mat-focus-indicator mat-button mat-button-base']");
    private final By issueCountryList = By.xpath("//mat-select[@formcontrolname='issueCountry']");
    private final By typeOfSharePercentageOfCapitalButtons = By.xpath("//div[contains(@class, 'typeofsharepercentage')]//span");
    private final By saudiGCCShareField = By.xpath("//input[@formcontrolname='saudiGccShare']");
    private final By foreignShareField = By.xpath("//input[@formcontrolname='foreignShare']");
    private final By communicationDetailsTab = By.xpath("//mat-step-header[@id=\"cdk-step-label-2-2\"]");
    private final By options = By.tagName("mat-option");
    private final By mobileNumberField = By.xpath("//form[@formgroupname='communicationDetails']//input[@formcontrolname='mobileNumber']");
    private final By emailField = By.xpath("//form[@formgroupname='communicationDetails']//input[@formcontrolname='email']");
    private final By saveButton = By.xpath("//shareholders-details//button[@class='rounded btnCss save-btn mat-focus-indicator mat-raised-button mat-button-base']");
    private final By errorMessage = By.tagName("mat-error");
    private final By okButtonTinInYourCountry = By.xpath("//div[@id='tinInYourCountryInfoModal']//button[@class='rounded save-btn btn-bg-success w-auto mat-focus-indicator mat-raised-button mat-button-base']");
    private final By pageError = By.xpath("//p[contains(@class,'notifier__notification-message')]");
    private final By pageErrorCloseButton = By.xpath("//button[contains(@class,'notifier__notification-button')]");
    private final By ShareholderDetailsErrorPopup = By.xpath("//div[@id='shErrorMsgsModal']//div[contains(@class, 'modal-body')]//h6");
    private final By popupOkButton = By.xpath("//div[@id='shErrorMsgsModal']//button[@class='rounded save-btn btn-bg-success w-auto mat-focus-indicator mat-raised-button mat-button-base']");
    private final By addNewShareholderPage = By.xpath("//div[@id='addShareholderDetailsModal']");
    public String defaultValueCapital,defaultValueProfit;


    public void fillShareholderPercentageDetails(Map<String, String> data){
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(shareCapitalField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("share_capital") != null){
            driver.findElement(shareCapitalField).sendKeys(data.get("share_capital"));
        }

        if(data.get("getDefaultDateFlag") != null) {
            if (data.get("getDefaultDateFlag").equals("x")) {
                defaultValueCapital = driver.findElement(startDateOfSharingCapitalField).getAttribute("value");
                defaultValueProfit = driver.findElement(startDateOfSharingProfitField).getAttribute("value");
            }
        }
        driver.findElement(startDateOfSharingCapitalField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("startDateOfSharingCapital") != null){
            if(data.get("startDateOfSharingCapital").equals("default")){
                driver.findElement(startDateOfSharingCapitalField).sendKeys(defaultValueCapital);
            }else{
                driver.findElement(startDateOfSharingCapitalField).sendKeys(data.get("startDateOfSharingCapital"));
            }
        }
        driver.findElement(shareProfitField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("share_profit") != null){
            driver.findElement(shareProfitField).sendKeys(data.get("share_profit"));
        }

        driver.findElement(startDateOfSharingProfitField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("startDateOfSharingProfit") != null){
            if(data.get("startDateOfSharingProfit").equals("default")){
                driver.findElement(startDateOfSharingProfitField).sendKeys(defaultValueProfit);
            }else{
                driver.findElement(startDateOfSharingProfitField).sendKeys(data.get("startDateOfSharingProfit"));
            }
        }
    }

    public void fillShareholdersDetails(Map<String, String> data){
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        if(data.get("shareholder_type") != null){
            selectShareholderType(data.get("shareholder_type"));
        }
        if(data.get("tin") != null){
            driver.findElement(tinNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
            driver.findElement(tinNumberField).sendKeys(data.get("tin"));
        }
        if(data.get("id_type") != null){
            selectIdType(data.get("id_type"));
        }
        if(data.get("issue_country") != null){
            selectIssueCountry(data.get("issue_country"));
        }
        driver.findElement(idNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("id_number") != null){
            driver.findElement(idNumberField).sendKeys(data.get("id_number"));
            // tmp solution to click anywhere on page, because CompanyID is affecting other fields if it wasn't verified from Database
            driver.findElement(By.xpath("//html")).click();
        }
        if(data.get("company_name") != null){
            if(existsElement(companyNameField)){
                driver.findElement(companyNameField).sendKeys(selectAll + Keys.BACK_SPACE);
                driver.findElement(companyNameField).sendKeys(data.get("company_name"));
            }
        }
        driver.findElement(startDateField).sendKeys(selectAll + Keys.BACK_SPACE);
        if(data.get("start_date") != null){
            driver.findElement(startDateField).sendKeys(data.get("start_date"));
        }
        if(!isElementDisabled(dateOfBirthField)){
            driver.findElement(dateOfBirthField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if(data.get("dateOfBirth") != null){
            if(!isElementDisabled(dateOfBirthField)){
                driver.findElement(dateOfBirthField).sendKeys(data.get("dateOfBirth"));
                driver.findElement(By.xpath("//html")).click();
            }
        }
        if(!isElementDisabled(firstNameField)){
            driver.findElement(firstNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if(data.get("first_name") != null){
            if(!isElementDisabled(firstNameField)){
                driver.findElement(firstNameField).sendKeys(data.get("first_name"));
            }
        }
        if(!isElementDisabled(lastNameField)){
            driver.findElement(lastNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if(data.get("last_name") != null){
            if(!isElementDisabled(lastNameField)) {
                driver.findElement(lastNameField).sendKeys(data.get("last_name"));
            }
        }
        if(data.get("copyOfAttachment") != null){
            driver.findElement(attachmentButton).click();
            File file = new File(data.get("copyOfAttachment"));
            driver.findElement(uploadAttachment).sendKeys(file.getAbsolutePath());
            driver.findElement(attachmentContinueButton).click();
        }
        if(data.get("typeOfSharePercentageOfCapital") != null){
            selectTypeOfSharePercentageOfCapital(data.get("typeOfSharePercentageOfCapital"));
        }
        if(data.get("saudiGCC_share") != null){
            if(existsElement(saudiGCCShareField)){
                driver.findElement(saudiGCCShareField).sendKeys(selectAll + Keys.BACK_SPACE);
                driver.findElement(saudiGCCShareField).sendKeys(data.get("saudiGCC_share"));
            }
        }
        if(data.get("foreign_share") != null){
            if(existsElement(foreignShareField)){
                driver.findElement(foreignShareField).sendKeys(selectAll + Keys.BACK_SPACE);
                driver.findElement(foreignShareField).sendKeys(data.get("foreign_share"));
            }
        }

    }

    public void fillCommunicationDetails(Map<String, String> data){
        if(data.get("mobile_Number") != null){
            driver.findElement(mobileNumberField).sendKeys(data.get("mobile_Number"));
        }
        if(data.get("email") != null){
            driver.findElement(emailField).sendKeys(data.get("email"));
        }
    }

    public String getShareholderPerDetailsErrorMessages(Map<String, String> rowOfData) {
        List<WebElement> errorMessages = switch (rowOfData.get("error_element")) {
            case "share_capital" -> driver.findElements(with(errorMessage).near(shareCapitalField,50));
            case "share_profit" -> driver.findElements(with(errorMessage).near(shareProfitField,50));
            case "date_capital" -> driver.findElements(with(errorMessage).near(startDateOfSharingCapitalField,50));
            case "date_profit" -> driver.findElements(with(errorMessage).near(startDateOfSharingProfitField,50));
            default -> new ArrayList<>();
        };
        if (errorMessages.size() > 0) {
            return errorMessages.get(0).getText();
        }
        return null;
    }
    public String getShareholderDetailsErrorMessages(Map<String, String> rowOfData) {
        List<WebElement> errorMessages = switch (rowOfData.get("error_element")) {
            case "id_number" -> driver.findElements(with(errorMessage).near(idNumberField,50));
            case "DOB" -> driver.findElements(with(errorMessage).near(dateOfBirthField,50));
            case "start_date" -> driver.findElements(with(errorMessage).near(startDateField,50));
            case "attachment" -> driver.findElements(with(errorMessage).near(attachmentButton,50));
            case "first_name" -> driver.findElements(with(errorMessage).near(firstNameField,50));
            case "last_name" -> driver.findElements(with(errorMessage).near(lastNameField,50));
            case "company_name" -> driver.findElements(with(errorMessage).near(companyNameField,50));
            case "saudiGCC_share" -> driver.findElements(with(errorMessage).near(saudiGCCShareField,50));
            case "foreign_share" -> driver.findElements(with(errorMessage).near(foreignShareField,50));
            case "issue_country" -> driver.findElements(with(errorMessage).near(issueCountryList,50));
            case "typeOfSharePercentageOfCapital" -> driver.findElements(with(errorMessage).near(typeOfSharePercentageOfCapitalButtons,50));
            default -> new ArrayList<>();
        };
        if (errorMessages.size() > 0) {
            return errorMessages.get(0).getText();
        }
        return null;
    }

    public String getShareholderDetailsPageErrorMessage() {
        List<WebElement> errorMessages = driver.findElements(pageError);
        if(errorMessages.size() > 0) {
            String errorMessage = errorMessages.get(0).getText();
            List<WebElement> errorMessagesCloseButton =  driver.findElements(pageErrorCloseButton);
            for(int index = errorMessagesCloseButton.size() - 1; index >= 0; index--){
                errorMessagesCloseButton.get(index).click();
            }
            return errorMessage;
        }
        return null;
    }

    public String getShareholderDetailsPopupErrors(String value) {
        List<WebElement> elements = driver.findElements(ShareholderDetailsErrorPopup);
        for (WebElement element : elements) {
            if (element.getText().equals(value)) {
                return element.getText();
            }
        }
        return null;
    }


    public void selectTypeOfSharePercentageOfCapital(String value){
        List <WebElement> elements = driver.findElements(typeOfSharePercentageOfCapitalButtons);
        for(WebElement element: elements){
            String t = element.getText().replaceAll("[\\u203C\\u3010\\u3011\\u300A\\u200C\\u202A\\u202C\\u2049\\u20E3]", "");
            if(t.equals(value)){
                element.click();
            }
        }
    }

    public boolean isElementDisabled(By e) {
        String t = driver.findElement(e).getAttribute("outerHTML");
        return t.contains("disabled");
    }
    public boolean existsElement(By element) {
        try {
            driver.findElement(element);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
    public void clickShareholderDetailsTab(){
        driver.findElement(shareholderDetailsTab).click();
    }
    public void clickCommunicationDetailsTab(){
        driver.findElement(communicationDetailsTab).click();
    }
    public void clickSaveButton(){
        driver.findElement(saveButton).click();
    }
    public void clickPopupOkButton(){driver.findElement(popupOkButton).click();}
    public boolean checkCommunicationDetailsIsDisplayed(){
        return driver.findElement(mobileNumberField).isDisplayed();
    }

    public boolean checkShareholderDetailsPage(){
        // verify element from next page is displayed
        return driver.findElement(tinNumberField).isDisplayed();
    }
    public boolean checkShareholderPage(){
        return existsElement(addNewShareholderPage);
    }
    public boolean selectShareholderType(String value){
        return selectFromList(shareholderTypeList, value);
    }
    public boolean selectIdType(String value){
        return selectFromList(idTypeList, value);
    }
    public boolean selectIssueCountry(String value){
        return selectFromList(issueCountryList, value);
    }


    public boolean selectFromList(By e, String value){
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
}
