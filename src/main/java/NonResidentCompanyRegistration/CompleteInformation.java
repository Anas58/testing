package NonResidentCompanyRegistration;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class CompleteInformation {
    WebDriver driver;

    public CompleteInformation(WebDriver driver){
        this.driver = driver;
    }


    private final By residency_statusButtons = By.xpath("//mat-radio-group[@formcontrolname='resStatus']");
    private final By legal_entity_nonResident = By.xpath("//mat-radio-group[@formcontrolname='Orgnonresident']");
    private final By radio_buttons = By.tagName("mat-radio-button");

    private final By non_Resident = By.xpath("(//mat-radio-button[@value='2'])[1]");
    private final By continueButton = By.xpath("//financial-details/following-sibling::div//button[@mat-button]");
    private final By companyType = By.xpath("//mat-select[@formcontrolname='compType']");
    private final By options = By.tagName("mat-option");
    private final By reportingBranch = By.xpath("//mat-select[@formcontrolname='Augrp']");
    private final By addNewBranchButton = By.xpath("//branches-details//button[@mat-button and @type]");
    private final By errorMessage = By.tagName("mat-error");
    private final By label = By.xpath("//div[contains(@class, 'registerLabelCss')]");
    private final By label2 = By.xpath("//p[contains(@class, 'registerLabel2Css')]");
    private final By addNewShareholdersButton = By.xpath("//shareholders-details//button[@class='w-auto mat-focus-indicator mat-button mat-button-base btnGCss']");


    public void fillData(Map<String, String> data) {

        if(data.get("residency_status") != null) {
            List<WebElement> residency_status_buttons = driver.findElement(residency_statusButtons).findElements(radio_buttons);
            for (int i = 0; i < residency_status_buttons.size(); i++) {
                if (residency_status_buttons.get(i).getText().equals(data.get("residency_status"))) {
                    residency_status_buttons.get(i).click();
                }
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(data.get("legal_entity_nonResident") != null) {
            List<WebElement> legal_entity_buttons = driver.findElement(legal_entity_nonResident).findElements(radio_buttons);
            for (int i = 0; i < legal_entity_buttons.size(); i++) {
                if (legal_entity_buttons.get(i).getText().equals(data.get("legal_entity_nonResident"))) {
                    legal_entity_buttons.get(i).click();
                }
            }
        }
        if(data.get("company_type") != null){
            selectCompanyType(data.get("company_type"));
        }
    }

    public void clickNonResident(){
        driver.findElement(non_Resident).click();
    }

    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
    public void clickAddNewBranch(){
        driver.findElement(addNewBranchButton).click();
    }

    public String getErrorMessages(Map<String, String> data){
        List<WebElement> errorMessages = switch(data.get("error_element")){
            case "company_type" -> driver.findElements(with(errorMessage).below(companyType));
            case "legal_entity" -> driver.findElements(with(errorMessage).near(legal_entity_nonResident,200));
            default -> new ArrayList<>();
        };
        if(errorMessages.size() > 0) {
            return errorMessages.get(0).getText();
        }
        return null;
    }

    public String getFieldsLabels(String key){
        WebElement element_label = switch(key){
            case "residency_status" -> driver.findElement(with(label2).above(residency_statusButtons));
            case "legal_entity_nonResident" -> driver.findElement(with(label2).above(legal_entity_nonResident));

            default -> null;
        };
        // return label of the first element if any
        if(!element_label.getText().isEmpty()) {
            return element_label.getText();
        }
        return null;
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
    public void clickAddNewShareholders(){
        driver.findElement(addNewShareholdersButton).click();
    }
    public boolean selectCompanyType(String value){
        return selectFromList(companyType, value);
    }
    public boolean checkAddNewShareholdersIsDisplayed(){
        return driver.findElement(addNewShareholdersButton).isDisplayed();
    }

    public boolean checkAddNewBranchIsDisplayedButton(){
        boolean result = false;
        if(existsElement(addNewBranchButton)){
            result = driver.findElement(addNewBranchButton).isDisplayed();
        }
        return result;
    }

    public boolean existsElement(By element) {
        try {
            driver.findElement(element);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }


}
