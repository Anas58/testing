package CompanyRegistrationPages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class AddNewBranch {

    WebDriver driver;

    public AddNewBranch(WebDriver driver) {
        this.driver = driver;
    }

    int flag=0;
    int attachmentFlag =0;
    boolean incremented;
    boolean incrementedAttach = false;
    private final By branchNameField = By.xpath("//input[@formcontrolname='branchName']");
    private final By companyMemorandumButton = By.xpath("//form[@formgroupname='branchDetails']//mat-form-field[contains(@class,'attachment-field')]");
    private final By companyMemorandumAttachment = By.xpath("//signup-attachments//input[contains(@type,'file')]"); // //signup-attachments//mat-card-content[contains(@class,'mat-card-content')]
    private final By attachmentContinueButton = By.xpath("(//button[@class='rounded btnCss mat-focus-indicator mat-button mat-button-base'])");
    private final By branchDetailsTab = By.xpath("//*[@id=\"cdk-step-label-1-0\"]");
    private final By activityDetailsTab = By.xpath("//*[@id=\"cdk-step-label-1-1\"]");
    private final By addressDetailsTab = By.xpath("//*[@id=\"cdk-step-label-1-2\"]");
    private final By contactPersonTab = By.xpath("//*[@id=\"cdk-step-label-1-3\"]");
    private final By addCommercialNumberOptionButton = By.xpath("//input[@formcontrolname='crTypeSelected']/parent::label");
    private final By addLicensesNumberOptionButton = By.xpath("//input[@formcontrolname='licenseTypeSelected']/parent::label");
    private final By licenseDetailTab = By.xpath("//mat-panel-title[contains(text(),'License Details')]");
    private final By commercialNumberDetailsTab = By.xpath("//mat-panel-title[contains(text(),'Commercial Number ')]");
    private final By mainActivityButton = By.xpath("//mat-checkbox[@formcontrolname='isMainActivity']");
    private final By issueCountryList = By.xpath("//mat-select[@formcontrolname='issueCountry']");
    private final By issueByList = By.xpath("//mat-select[@formcontrolname='issuedBy']");
    private final By issuedCityList = By.xpath("//mat-select[@formcontrolname='issuedCity'] | //input[@formcontrolname='issuedCity']");
    private final By licenseNumberField = By.xpath("//input[@formcontrolname='licenseNumber']");
    private final By validFromField = By.xpath("//branches-details//input[@formcontrolname='validFrom']");
    private final By copyOfLicenseAttachmentButton = By.xpath("//form[@formgroupname='activityDetails']//mat-form-field[contains(@class,'attachment-field')]");
    private final By copyOfLicenseAttachment = By.xpath("//signup-attachments//input[contains(@type,'file')]");
    private final By licenseAttachmentContinueButton = By.xpath("//button[@class='rounded btnCss mat-focus-indicator mat-button mat-button-base']");
    private final By mainGroupActivityList = By.xpath("//mat-select[@formcontrolname='mainGroupActivity']");
    private final By subGroupActivityList = By.xpath("//mat-select[@formcontrolname='subGroupActivity']");
    private final By mainActivityList = By.xpath("//mat-select[@formcontrolname='mainActivity']");
    private final By addLicenseNumberButton = By.xpath("(//button[@class='mt-4 mb-2 mat-focus-indicator mat-button mat-button-base btnGCss ng-star-inserted'])"); // TODO validate this xpath
    private final By deleteLicenseDetailsButton = By.xpath("//mat-icon[contains(text(),'delete')]");
    private final By countryList = By.xpath("//mat-select[@formcontrolname='country']");
    private final By provinceList = By.xpath("//mat-select[@formcontrolname='province']");
    private final By cityList = By.xpath("//mat-select[@formcontrolname='city']");
    private final By districtField = By.xpath("//input[@formcontrolname='district']");
    private final By streetNameField = By.xpath("//input[@formcontrolname='streetName']");
    private final By buildingNumberField = By.xpath("//input[@formcontrolname='buildingNumber']");
    private final By zipCodeField = By.xpath("//input[@formcontrolname='zipCode']");
    private final By additionalNumberField = By.xpath("//input[@formcontrolname='additionalNumber']");
    private final By unitNumberField = By.xpath("//input[@formcontrolname='unitNumber']");
    private final By tinNumberField = By.xpath("//input[@formcontrolname='tinNumber']");
    private final By idTypeList = By.xpath("//form[@formgroupname='contactPersonDetails']//mat-select[@formcontrolname='idType']");
    private final By idNumberField = By.xpath("//input[@formcontrolname='idNumber']");
    private final By dateOfBirthField = By.xpath("//input[@formcontrolname='dateOfBirth']");
    private final By firstNameField = By.xpath("//input[@formcontrolname='firstName']");
    private final By lastNameField = By.xpath("//input[@formcontrolname='lastName']");
    private final By startDateField = By.xpath("//input[@formcontrolname='startDate']");
    private final By mobileNumberField = By.xpath("//form[@formgroupname='contactPersonDetails']//input[@formcontrolname='mobileNumber']");
    private final By emailField = By.xpath("//form[@formgroupname='contactPersonDetails']//input[@formcontrolname='email']");
    private final By confirmEmailField = By.xpath("//form[@formgroupname='contactPersonDetails']//input[@formcontrolname='confirmEmail']");
    private final By copyOfGeneralManagerIDButton = By.xpath("//form[@formgroupname='contactPersonDetails']//mat-form-field[contains(@class,'attachment-field')]");
    private final By copyOfGeneralManagerIDAttachment = By.xpath("//signup-attachments//input[contains(@type,'file')]");
    private final By copyOfGeneralManagerIDContinueButton = By.xpath("//signup-attachments//button[@class='rounded btnCss mat-focus-indicator mat-button mat-button-base']");
    private final By saveButton = By.xpath("//button[@class='rounded btnCss save-btn mat-focus-indicator mat-raised-button mat-button-base']");
    private final By cancelButton = By.xpath("//button[@class='rounded text-muted cancel-button mat-focus-indicator mat-button mat-button-base']");
    private final By options = By.tagName("mat-option");
    private final By errorMessage = By.tagName("mat-error");
    private final By activityDetailsErrorPopup = By.xpath("//div[@id='brErrorMsgsModal']//div[contains(@class, 'modal-body')]//h6"); // find errors from popup validation after clicking save button
    private final By closePopupMessageButton = By.xpath("//div[@id='brErrorMsgsModal']//button[@class='rounded save-btn btn-bg-success w-auto mat-focus-indicator mat-raised-button mat-button-base']");
    private final By pageError = By.xpath("//p[contains(@class,'notifier__notification-message')]");
    private final By pageErrorCloseButton = By.xpath("//button[contains(@class,'notifier__notification-button')]");
    private final By issueCountryListContactForm = By.xpath("//form[@formgroupname='contactPersonDetails']//mat-select[@formcontrolname='issueCountry']");
    private final By editAddNewBranch = By.xpath("(//branches-details//tbody//button)[1]");
    private final By tabs = By.tagName("mat-expansion-panel");
    private final By addressCard = By.xpath("//mat-card[contains (@class, 'address-card')]");



    public void fillBranchDetails(Map<String, String> data) {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(branchNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("branch_name") != null) {
            driver.findElement(branchNameField).sendKeys(data.get("branch_name"));
        }
        if (data.get("company_memorandum_attachment") != null) {
            File file = new File(data.get("company_memorandum_attachment"));
            driver.findElement(companyMemorandumButton).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(companyMemorandumAttachment).sendKeys(file.getAbsolutePath());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(attachmentContinueButton).click();
        }
    }

    public void fillActivityDetails(Map<String, String> data) {
        incrementedAttach = false;
        List<WebElement> allTabs;
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        String commercialButtonIsActive = driver.findElement(addCommercialNumberOptionButton).getAttribute("class");
        if (data.get("add_commercial_number") != null) {
            if (data.get("add_commercial_number").equals("yes")) {
                if (!commercialButtonIsActive.contains("active")) {
                    flag = 0;
                    attachmentFlag = 0;
                    incremented = false;
                    driver.findElement(addCommercialNumberOptionButton).click();
                    allTabs = driver.findElements(tabs); //find all tab elements, click on the one that use currently on by using the flag.
                    allTabs.get(flag).click();
                }
                if (data.get("com_MainActivityBtn") != null) {
                    String isChecked = driver.findElements(mainActivityButton).get(flag).getAttribute("class");
                    if (data.get("com_MainActivityBtn").equals("yes")) {
                        if (!isChecked.contains("checked")) {
                            driver.findElements(mainActivityButton).get(flag).click();
                        }
                    } else if (data.get("Lic_MainActivityButton").equals("no")) {
                        if (isChecked.contains("checked")) driver.findElements(mainActivityButton).get(flag).click();
                    }
                }
                if (data.get("com_IssueBy") != null) {
                    String disabled = driver.findElements(issueByList).get(flag).getAttribute("disabled");
                    if (disabled != null) {
                        selectIssueBy(data.get("com_IssueBy"));
                    }
                }
                if (data.get("com_IssuedCity") != null) {
                    String disabled = driver.findElements(issuedCityList).get(flag).getAttribute("disabled");
                    if (disabled != null) {
                        selectIssuedCity(data.get("com_IssuedCity"));
                    }
                }
                driver.findElements(licenseNumberField).get(flag).sendKeys(selectAll + Keys.BACK_SPACE);
                if (data.get("com_CRnumber") != null) {
                    driver.findElements(licenseNumberField).get(flag).sendKeys(data.get("com_CRnumber"));
                    // tmp solution to click anywhere on page, because licenseNumberField is affecting other fields if it wasn't verified from Database
                    driver.findElement(By.xpath("//html")).click();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (data.get("com_ValidFrom") != null) {
                    String t = driver.findElements(validFromField).get(flag).getAttribute("disabled");
                    if (t == null) {
                        driver.findElements(validFromField).get(flag).sendKeys(selectAll + Keys.BACK_SPACE);
                        driver.findElements(validFromField).get(flag).sendKeys(data.get("com_ValidFrom"));
                    }
                }
                if (data.get("com_CopyofCR") != null) {
                    driver.findElement(By.xpath("//html")).click();
                    File file = new File(data.get("com_CopyofCR"));
                    driver.findElements(copyOfLicenseAttachmentButton).get(attachmentFlag).click();
                    driver.findElement(copyOfLicenseAttachment).sendKeys(file.getAbsolutePath());
                    driver.findElement(licenseAttachmentContinueButton).click();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (data.get("com_TransferCopyofCR") != null) {
                    File file = new File(data.get("com_TransferCopyofCR"));
                    driver.findElements(copyOfLicenseAttachmentButton).get(attachmentFlag).click();
                    driver.findElement(copyOfLicenseAttachment).sendKeys(file.getAbsolutePath());
                    driver.findElement(licenseAttachmentContinueButton).click();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (data.get("com_MainGroupOfActivity") != null) {
                    selectMainGroupOfActivity(data.get("com_MainGroupOfActivity"));
                }
                if (data.get("com_SubGroupOfActivity") != null) {
                    selectSubGroupOfActivity(data.get("com_SubGroupOfActivity"));
                }
                if (data.get("com_MainActivityField") != null) {
                    selectMainActivity(data.get("com_MainActivityField"));
                }
            }
        }
        String licensesButtonIsActive = driver.findElement(addLicensesNumberOptionButton).getAttribute("class");
        if (data.get("add_licenses_number") != null) {
            if (data.get("add_licenses_number").equals("yes")) {
                if (!licensesButtonIsActive.contains("active")) {
                    if (commercialButtonIsActive.contains("active")) {
                        flag++;
                        incremented = true;
                        attachmentFlag += 2;
                    }else{
                        flag=0;
                        attachmentFlag=0;
                    }
                    driver.findElement(addLicensesNumberOptionButton).click();
                    allTabs = driver.findElements(tabs);
                    allTabs.get(flag).click();
                }
                if (data.get("Lic_MainActivityButton") != null) {
                    String isChecked = driver.findElements(mainActivityButton).get(flag).getAttribute("class");
                    if (data.get("Lic_MainActivityButton").equals("yes")) {
                        if (!isChecked.contains("checked")) {
                            driver.findElements(mainActivityButton).get(flag).click();
                        }
                    } else if (data.get("Lic_MainActivityButton").equals("no")) {
                        if (isChecked.contains("checked")) driver.findElements(mainActivityButton).get(flag).click();
                    }
                }
                if (data.get("Lic_IssueBy") != null) {
                    String disabled = driver.findElements(issueByList).get(flag).getAttribute("aria-disabled");
                    if (disabled.contains("false")) {
                        selectIssueBy(data.get("Lic_IssueBy"));
                    }
                }
                if (data.get("Lic_IssuedCity") != null) {
                    selectIssuedCity(data.get("Lic_IssuedCity"));
                }
                driver.findElements(licenseNumberField).get(flag).sendKeys(selectAll + Keys.BACK_SPACE);
                if (data.get("Lic_LicenseNumber") != null) {
                    driver.findElements(licenseNumberField).get(flag).sendKeys(data.get("Lic_LicenseNumber"));
                    driver.findElement(By.xpath("//html")).click();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                driver.findElements(validFromField).get(flag).sendKeys(selectAll + Keys.BACK_SPACE);
                if (data.get("Lic_ValidFrom") != null) {
                    driver.findElements(validFromField).get(flag).sendKeys(data.get("Lic_ValidFrom"));
                }
                if (data.get("Lic_CopyOfLicense") != null) {
                    driver.findElement(By.xpath("//html")).click();
                    File file = new File(data.get("Lic_CopyOfLicense"));
                    driver.findElements(copyOfLicenseAttachmentButton).get(attachmentFlag).click();
                    driver.findElement(copyOfLicenseAttachment).sendKeys(file.getAbsolutePath());
                    driver.findElement(licenseAttachmentContinueButton).click();
                }
                if (data.get("Lic_MainGroupOfActivity") != null) {
                    selectMainGroupOfActivity(data.get("Lic_MainGroupOfActivity"));
                }
                if (data.get("Lic_SubGroupOfActivity") != null) {
                    selectSubGroupOfActivity(data.get("Lic_SubGroupOfActivity"));
                }
                if (data.get("Lic_MainActivityField") != null) {
                    selectMainActivity(data.get("Lic_MainActivityField"));
                }
            }
        }
    }

    public void fillAddressDetails(Map<String, String> data) {
        if (existsElement(addressCard)) {
            if (driver.findElement(addressCard).isDisplayed()) {
                driver.findElement(addressCard).click();
            }
        }
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (data.get("country") != null) {
            selectCountry(data.get("country"));
        }
        if (data.get("province") != null) {
            selectProvince(data.get("province"));
        }
        if (data.get("city") != null) {
            selectCity(data.get("city"));
        }
        driver.findElement(districtField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("district") != null) {
            driver.findElement(districtField).sendKeys(data.get("district"));
        }
        driver.findElement(streetNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("street_name") != null) {
            driver.findElement(streetNameField).sendKeys(data.get("street_name"));
        }
        driver.findElement(buildingNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("building_number") != null) {
            driver.findElement(buildingNumberField).sendKeys(data.get("building_number"));
        }
        driver.findElement(zipCodeField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("zip_code") != null) {
            driver.findElement(zipCodeField).sendKeys(data.get("zip_code"));
        }
        driver.findElement(additionalNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("additional_number") != null) {
            driver.findElement(additionalNumberField).sendKeys(data.get("additional_number"));
        }
        driver.findElement(unitNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("unit_number") != null) {
            driver.findElement(unitNumberField).sendKeys(data.get("unit_number"));
        }
    }

    public void fillContactPerson(Map<String, String> data) {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(tinNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("tin") != null) {
            driver.findElement(tinNumberField).sendKeys(data.get("tin"));
            driver.findElement(By.xpath("//html")).click();
        }
        if (data.get("ID_type") != null) {
            selectIdType(data.get("ID_type"));
        }
        if (data.get("issued_country") != null) {
            selectIssuedCountryContactForm(data.get("issued_country"));
        }
        if (!isElementDisabled(idNumberField)) {
            driver.findElement(idNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if (data.get("ID_number") != null) {
            if (!isElementDisabled(idNumberField)) {
                driver.findElement(idNumberField).sendKeys(data.get("ID_number"));
            }
        }
        if (!isElementDisabled(dateOfBirthField)) {
            driver.findElement(dateOfBirthField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if (data.get("date_of_birth") != null) {
            if (!isElementDisabled(dateOfBirthField)) {
                driver.findElement(dateOfBirthField).sendKeys(data.get("date_of_birth"));
            }
        }
        if (!isElementDisabled(firstNameField)) {
            driver.findElement(firstNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if (data.get("first_name") != null) {
            if (!isElementDisabled(firstNameField)) {
                driver.findElement(firstNameField).sendKeys(data.get("first_name"));
            }
        }
        if (!isElementDisabled(lastNameField)) {
            driver.findElement(lastNameField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if (data.get("last_name") != null) {
            if (!isElementDisabled(lastNameField)) {
                driver.findElement(lastNameField).sendKeys(data.get("last_name"));
            }
        }
        driver.findElement(startDateField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("start_date") != null) {
            driver.findElement(startDateField).sendKeys(data.get("start_date"));
        }

        if (!isElementDisabled(mobileNumberField)) {
            driver.findElement(mobileNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if (data.get("mobile_number") != null) {
            if (!isElementDisabled(mobileNumberField)) {
                driver.findElement(mobileNumberField).sendKeys(data.get("mobile_number"));
            }
        }
        if (!isElementDisabled(emailField)) {
            driver.findElement(emailField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if (data.get("email") != null) {
            if (!isElementDisabled(emailField)) {
                driver.findElement(emailField).sendKeys(data.get("email"));
            }
        }
        if (!isElementDisabled(confirmEmailField)) {
            driver.findElement(confirmEmailField).sendKeys(selectAll + Keys.BACK_SPACE);
        }
        if (data.get("confirm_email") != null) {
            if (!isElementDisabled(confirmEmailField))  {
                driver.findElement(confirmEmailField).sendKeys(data.get("confirm_email"));
            }
        }
        if (data.get("copyOfGeneralManagerID") != null) {
            File file = new File(data.get("copyOfGeneralManagerID"));
            driver.findElement(copyOfGeneralManagerIDButton).click();
            driver.findElement(copyOfGeneralManagerIDAttachment).sendKeys(file.getAbsolutePath());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(copyOfGeneralManagerIDContinueButton).click();
        }
    }

    public boolean isElementDisabled(By e) {
        String t = driver.findElement(e).getAttribute("outerHTML");
        return t.contains("disabled");
    }

    public String getBranchDetailsErrorMessages(Map<String, String> rowOfData) {
        // find error related to element
        List<WebElement> errorMessages = switch (rowOfData.get("error_element")) {
            case "branch_name" -> driver.findElements(with(errorMessage).below(branchNameField));
            case "company_memorandum_attachment" -> driver.findElements(with(errorMessage).near(companyMemorandumButton, 50));
            default -> new ArrayList<>();
        };
        // return text of the first element if any
        if (errorMessages.size() > 0) {
            return errorMessages.get(0).getText();
        }
        return null;
    }

    public String getActivityDetailsPopupErrors(String value) {
        List<WebElement> elements = driver.findElements(activityDetailsErrorPopup);
        for (WebElement element : elements) {
            if (element.getText().equals(value)) {
                return element.getText();
            }
        }
        return null;
    }

    public String getActivityDetailsErrorMessages(Map<String, String> rowOfData) {
        // find error related to element
        List<WebElement> errorMessages = switch (rowOfData.get("error_element")) {
            case "com_CRnumber", "Lic_LicenseNumber" -> driver.findElements(with(errorMessage).near(driver.findElements(licenseNumberField).get(flag), 50));
            case "com_CopyofCR", "Lic_CopyOfLicense" -> driver.findElements(with(errorMessage).near(driver.findElements(copyOfLicenseAttachmentButton).get(attachmentFlag), 50));
            case "com_MainGroupOfActivity", "Lic_MainGroupOfActivity" -> driver.findElements(with(errorMessage).near(driver.findElements(mainGroupActivityList).get(flag), 100));
            case "com_SubGroupOfActivity", "Lic_SubGroupOfActivity" -> driver.findElements(with(errorMessage).near(driver.findElements(subGroupActivityList).get(flag), 50));
            case "com_MainActivityField", "Lic_MainActivityField" -> driver.findElements(with(errorMessage).near(driver.findElements(mainActivityList).get(flag), 50));
            case "com_MainActivityBtn", "Lic_MainActivityButton" -> driver.findElements(with(errorMessage).near(driver.findElements(mainActivityButton).get(flag), 50));
            case "Lic_IssueBy" -> driver.findElements(with(errorMessage).near(driver.findElements(issueByList).get(flag), 50));
            case "Lic_IssuedCity" -> driver.findElements(with(errorMessage).near(driver.findElements(issuedCityList).get(flag), 50));
            case "Lic_ValidFrom" -> driver.findElements(with(errorMessage).near(driver.findElements(validFromField).get(flag), 50));
            default -> new ArrayList<>();
        };

        // return text of the first element if any
        if (errorMessages.size() > 0) {
            return errorMessages.get(0).getText();
        }
        return null;
    }

    public String getAddressDetailsErrorMessages(Map<String, String> rowOfData) {
        // find error related to element
        List<WebElement> errorMessages = switch (rowOfData.get("error_element")) {
            case "country" -> driver.findElements(with(errorMessage).below(countryList));
            case "province" -> driver.findElements(with(errorMessage).below(provinceList));
            case "city" -> driver.findElements(with(errorMessage).below(cityList));
            case "district" -> driver.findElements(with(errorMessage).below(districtField));
            case "street_name" -> driver.findElements(with(errorMessage).below(streetNameField));
            case "building_number" -> driver.findElements(with(errorMessage).below(buildingNumberField));
            case "zip_code" -> driver.findElements(with(errorMessage).below(zipCodeField));
            case "additional_number" -> driver.findElements(with(errorMessage).below(additionalNumberField));
            case "unit_number" -> driver.findElements(with(errorMessage).below(unitNumberField));
            default -> new ArrayList<>();
        };
        // return text of the first element if any
        if (errorMessages.size() > 0) {
            return errorMessages.get(0).getText();
        }
        return null;
    }

    public String getAddressDetailsPageErrorMessage() {
        List<WebElement> errorMessages = driver.findElements(pageError);
        if (errorMessages.size() > 0) {
            // get first message only
            String errorMessage = errorMessages.get(0).getText();
            // closer all messages
            List<WebElement> errorMessagesCloseButton = driver.findElements(pageErrorCloseButton);
            for (int index = errorMessagesCloseButton.size() - 1; index >= 0; index--) {
                errorMessagesCloseButton.get(index).click();
            }
            return errorMessage;
        }
        return null;
    }

    public String getContactPersonErrorMessages(Map<String, String> rowOfData) {
        // find error related to element
        List<WebElement> errorMessages = switch (rowOfData.get("error_element")) {
            case "ID_number" -> driver.findElements(with(errorMessage).near(idNumberField, 50));
            case "date_of_birth" -> driver.findElements(with(errorMessage).near(dateOfBirthField, 50));
            case "first_name" -> driver.findElements(with(errorMessage).near(firstNameField, 50));
            case "last_name" -> driver.findElements(with(errorMessage).near(lastNameField, 50));
            case "mobile_number" -> driver.findElements(with(errorMessage).near(mobileNumberField, 50));
            case "email" -> driver.findElements(with(errorMessage).near(emailField, 50));
            case "confirm_email" -> driver.findElements(with(errorMessage).near(confirmEmailField, 50));
            case "attachment" -> driver.findElements(with(errorMessage).near(copyOfGeneralManagerIDButton, 50));
            case "issue_country" -> driver.findElements(with(errorMessage).near(issueCountryListContactForm, 50));
            case "tin" -> driver.findElements(with(errorMessage).near(tinNumberField, 50));
            default -> new ArrayList<>();
        };
        // return text of the first element if any
        if (errorMessages.size() > 0) {
            return errorMessages.get(0).getText();
        }
        return null;
    }

    public String getContactPersonPageErrorMessage() {
        List<WebElement> errorMessages = driver.findElements(pageError);
        if (errorMessages.size() > 0) {
            // get first message only
            String errorMessage = errorMessages.get(0).getText();
            // closer all messages
            List<WebElement> errorMessagesCloseButton = driver.findElements(pageErrorCloseButton);
            for (int index = errorMessagesCloseButton.size() - 1; index >= 0; index--) {
                errorMessagesCloseButton.get(index).click();
            }
            return errorMessage;
        }
        return null;
    }

    public void resetFlags(Map<String, String> rowOfData){
        if(rowOfData.get("add_commercial_number") != null) {
            if (rowOfData.get("add_commercial_number").equals("yes")) {
                flag = 0;
                attachmentFlag = 0;
            }
        }
        if(rowOfData.get("add_licenses_number") != null) {
            if (rowOfData.get("add_licenses_number").equals("yes")) {
                flag = 1;
                attachmentFlag = 2;
            }
        }
    }

    public void clickEditAddNewBranch(){
        driver.findElement(editAddNewBranch).click();
    }
    public void clickCompanyMemorandumButton() {
        driver.findElement(companyMemorandumButton).click();
    }

    public void uploadCompanyMemorandumAttachment(String path) {
        driver.findElement(companyMemorandumAttachment).sendKeys(path);
    }

    public void clickContinueAttachmentButton() {
        driver.findElement(attachmentContinueButton).click();
    }

    public void clickActivityDetailsTab() {
        driver.findElement(activityDetailsTab).click();
    }

    public void clickBranchDetailsTab() {
        driver.findElement(branchDetailsTab).click();
    }

    public void clickAddressDetailsTab() {
        driver.findElement(addressDetailsTab).click();
    }

    public void clickContactPersonTab() {
        driver.findElement(contactPersonTab).click();
    }

    public void clickClosePopupMessage() {
        driver.findElement(closePopupMessageButton).click();
    }

    public void clickLicenseDetailsTab() {
        driver.findElement(licenseDetailTab).click();
    }

    public boolean checkActivityPageIsDisplayed() {
        return driver.findElement(addCommercialNumberOptionButton).isDisplayed();
    }

    public boolean checkAddressDetailsPageIsDisplayed() {
        return driver.findElement(unitNumberField).isDisplayed();
    }

    public boolean addressCardVisible(){
        return existsElement(addressCard);
    }

    public void clickAddressCard() {
        if (existsElement(addressCard)) {
            if (driver.findElement(addressCard).isDisplayed()) {
                driver.findElement(addressCard).click();
            }
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
    public boolean checkActivityDetailsButtonsClicked() {
        String commercialButtonIsActive = driver.findElement(addCommercialNumberOptionButton).getAttribute("class");
        String licensesButtonIsActive = driver.findElement(addLicensesNumberOptionButton).getAttribute("class");
        if (commercialButtonIsActive.contains("active") | licensesButtonIsActive.contains("active")) {
            return true;
        }
        return false;
    }
    public boolean contactPageIsClosed() {
        try {
            driver.findElement(emailField);
        } catch (NoSuchElementException e){
            return false;
        }
        return true;
    }

    public boolean checkContactPageIsDisplayed() {
        return driver.findElement(emailField).isDisplayed();
    }


    public void clickMainActivityButton() {
        driver.findElement(mainActivityButton).click();
    }

    public boolean selectFromListActivity(By e, String value) {
        WebElement element = driver.findElements(e).get(flag);
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

    public boolean selectIssueCountry(String value) {
        return selectFromListActivity(issueCountryList, value);
    }

    public boolean selectIssueBy(String Value) {
        return selectFromListActivity(issueByList, Value);
    }

    public boolean selectIssuedCity(String value) {
        return selectFromListActivity(issuedCityList, value);
    }

    public boolean selectIssuedCountryContactForm(String value) {
        return selectFromList(issueCountryListContactForm, value);
    }

    public void enterLicenseNumber(String value) {
        driver.findElement(licenseNumberField).clear();
        driver.findElement(licenseNumberField).sendKeys(value);

    }

    public void enterValidFrom(String value) {
        driver.findElement(validFromField).clear();
        driver.findElement(validFromField).sendKeys(value);
    }


    public boolean selectMainGroupOfActivity(String value) {
        return selectFromListActivity(mainGroupActivityList, value);
    }

    public boolean selectSubGroupOfActivity(String value) {
        return selectFromListActivity(subGroupActivityList, value);
    }

    public boolean selectMainActivity(String value) {
        return selectFromListActivity(mainActivityList, value);
    }

    public boolean selectCountry(String value) {
        return selectFromList(countryList, value);
    }

    public boolean selectProvince(String value) {
        return selectFromList(provinceList, value);
    }

    public boolean selectCity(String value) {
        return selectFromList(cityList, value);
    }

    public boolean selectIdType(String value) {
        return selectFromList(idTypeList, value);
    }

    public void clickDeleteLicenseButton() {
        driver.findElement(deleteLicenseDetailsButton).click();
    }

    public boolean deleteLicenseButtonIsDisplayed() {
        boolean result = !driver.findElements(deleteLicenseDetailsButton).isEmpty();
        if (result) {
            return true;
        }
        return false;
    }


    public void clickAddLicenseNumberOptionButton(){
        driver.findElement(addLicensesNumberOptionButton).click();
    }
    public void clickCopyOfLicenseAttachment(){
        driver.findElement(copyOfLicenseAttachmentButton).click();
    }
    public void uploadCopyOfLicenseAttachment(String path){
        driver.findElement(copyOfLicenseAttachment).sendKeys(path);
    }
    public void clickCopyOfManagerIdAttachment(){
        driver.findElement(copyOfGeneralManagerIDButton).click();
    }

    public void uploadCopyOfGeneralManagerID(String path){
        driver.findElement(copyOfGeneralManagerIDAttachment).sendKeys(path);
    }
    public void clickCopyOfGeneralManagerAttachmentContinue(){
        driver.findElement(copyOfGeneralManagerIDContinueButton).click();
    }
    public void clickCancelButton(){
        driver.findElement(cancelButton).click();
    }
    public void clickSaveButton(){
        driver.findElement(saveButton).click();
    }

}