package EstablishmentRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class CompleteInformation {
    WebDriver driver;

    public CompleteInformation(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By reportingBranch = By.xpath("//mat-select[@formcontrolname='Augrp']");
    By reportingBranchOption = By.tagName("mat-option");
    By resident = By.xpath("//mat-radio-group[@formcontrolname='resStatus']//mat-radio-button[@value='1']");
    By nonResident = By.xpath("//mat-radio-group[@formcontrolname='resStatus']//mat-radio-button[@value='2']");
    By permanentEstablishment = By.xpath("//mat-radio-group[@formcontrolname='Orgnonresident']//mat-radio-button[@value='1']");
    By nonPermanentEstablishment = By.xpath("//mat-radio-group[@formcontrolname='Orgnonresident']//mat-radio-button[@value='2']");
    By selectCompanyType = By.xpath("//mat-select[@formcontrolname='compType']");
    // By companyTypeOption=By.xpath("mat-option");
    By continueButton = By.xpath("//financial-details/following-sibling::div//button[@mat-button]");
    //Error Messages
    By errorMessage = By.tagName("mat-error");
    By legalEntityErrorMessage=By.xpath("//mat-error[contains(@class,'custom-mat-error')]");
    // Labels
    By labels = By.xpath("//div[contains(@class ,'registerLabelCss')]");
    By radioButtonLabels=By.xpath("//p[contains(@class ,'registerLabel')]");
    //Add Branch Button
    By addNewBranchButton = By.xpath("//branches-details//button[@mat-button and @type]");

    //Method filling data
    public void fillData(Map<String, String> data) {
        //Select reporting Branch
        if (data.get("reporting_branch") != null) {
            selectReportingBranch(data.get("reporting_branch"));
        }
        if (data.get("residency_status") != null) {
            if (data.get("residency_status").equals("Resident")) {
                driver.findElement(resident).click();
            } else {
                driver.findElement(nonResident).click();
            }
        }
        if (data.get("legal_entity") != null) {
            if (!data.get("residency_status").equals("Resident")) {
                if (data.get("legal_entity").equals("Permanent  Establishment")) {
                    driver.findElement(permanentEstablishment).click();
                } else {
                    driver.findElement(nonPermanentEstablishment).click();
                }
            }
        }
        if (data.get("legal_entity_dropdown") != null) {
            selectLegalEntity(data.get("legal_entity_dropdown"));
        }

    }
    // Verifying Error messages.
    public String fieldValidateErrorMessage(Map<String, String> errorData) {
        List<WebElement> error = switch (errorData.get("error_element")) {
            case "reporting_branch" -> driver.findElements(with(errorMessage).below(reportingBranch));
            case "residency_status" -> driver.findElements(with(errorMessage).near(resident,250));
            //case "residency_status" -> driver.findElements(with(errorMessage)).below(resident);
            case "legal_entity" -> driver.findElements(legalEntityErrorMessage);
            case "legal_entity_dropdown" -> driver.findElements(with(errorMessage).below(selectCompanyType));
            //case "legal_entity_dropdown" -> driver.findElements(with(errorMessage).below(nonPermanentEstablishment));
            default -> new ArrayList<>();
        };
        if (error.size() > 0) {
            return error.get(0).getText();
        }
        return null;
    }
    //Method to verify Labels
    public String facilityInformationFieldsLabel(String residencyStatus, String key) {
        List<WebElement> list = null;
        if (residencyStatus.equals("Resident")) {
            list = switch (key) {
                case "reporting_branch" -> driver.findElements(with(labels).above(reportingBranch));
                case "residency_status_labels" -> driver.findElements(with(radioButtonLabels).above(resident));
                default -> new ArrayList<>();
            };
            }
        else{
            list = switch (key) {
                case "reporting_branch" -> driver.findElements(with(labels).above(reportingBranch));
                case "residency_status_labels" -> driver.findElements(with(radioButtonLabels).above(resident));
                case "legal_entity" -> driver.findElements(with(radioButtonLabels).above(permanentEstablishment));
                default -> new ArrayList<>();
            };
            }
            if (list.size() > 0) {
                return list.get(0).getText();
            }

            return null;
    }

    //Selecting Reporting Branch
    public boolean selectReportingBranch(String value) {
        return selectFromList(reportingBranch, value);
    }

    //selecting value from legal Entity Dropdown
    public boolean selectLegalEntity(String value) {
        return selectFromList(selectCompanyType, value);
    }

    //Method Select Value From dropdown
    public boolean selectFromList(By element, String value) {
        driver.findElement(element).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> alldropdownoption = driver.findElements(reportingBranchOption);
        for (int i = 0; i <= alldropdownoption.size(); i++) {
            if (alldropdownoption.get(i).getText().equals(value)) {
                alldropdownoption.get(i).click();
                return true;
            }
        }
        return false;
    }

    //Click on Continue Button
    public void clickContinueButton() {
        driver.findElement(continueButton).click();

    }

    //Click on Add Branch Button.
    public void clickAddBranch() {
        driver.findElement(addNewBranchButton).click();
    }

    //Method To select Residency Status
    public void selectResidencyStatus(Map<String, String> dataLabels) {
        if (dataLabels.get("residency_status").equals("Resident ")) {
            driver.findElement(resident).click();
        } else {
            driver.findElement(nonResident).click();
        }
    }

    public boolean existsElement(By element) {
        try {
            driver.findElement(element);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean checkAddNewBranchIsDisplayedButton(){
        boolean result = false;
        if(existsElement(addNewBranchButton)){
            result = driver.findElement(addNewBranchButton).isDisplayed();
        }
        return result;
    }
}

