package CompanyRegistration;

import CompanyRegistrationPages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestListener;

import java.io.File;
import java.time.Duration;
import java.util.Map;

@Listeners({TestListener.class})
public class CompanyRegistrationTest {

    WebDriver driver;
    CompanyRegistration companyRegisterPage;
    CompleteInformation companyInformationPage;
    OTP otpPage;
    AddNewBranch addNewBranch;
    AddNewShareholders addNewShareholders;
    FinancialDetails financialDetails;
    CreateNewPassword createNewPassword;
    Acknowledgement acknowledgement;
    boolean negativeActivityDetailsRun;
    File file = new File("../resources/ExcelData/company_registration.xlsx");

    static {
        System.setProperty("extent.reporter.spark.start", "true");
        System.setProperty("extent.reporter.spark.config", "../resources/avent-config.xml");
        System.setProperty("extent.reporter.spark.out", "../resources/");
    }


    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "../resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://angularqa.gazt.gov.sa/angular/#/signup/company");
        driver.manage().window().maximize();
        companyRegisterPage = new CompanyRegistration(driver);

    }

    @DataProvider(name = "company_registration_English_labels")
    public Object[][] companyRegistrationLabels(){
        Object[][] labels = ExcelParser.passToDataProvider(file.getAbsolutePath(), "labels", "english", null);
        return labels;
    }

    @DataProvider(name = "negative_company_registration")
    public Object[][] negativeCompanyRegistrationData() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "company_registration", "english", "negative");
        return rowOfData;
    }

    @DataProvider(name = "positive_company_registration")
    public Object[][] positiveCompanyRegistrationData() {
        // TODO language must be a variable
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "company_registration", "english", "positive");
        return rowOfData;
    }

    @DataProvider(name = "positive_company_information")
    public Object[][] positiveCompanyInformationData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "company_information", "english", "positive");
        return data;
    }

    @DataProvider(name = "negative_company_information")
    public Object[][] negativeCompanyInformationData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "company_information", "english", "negative");
        return data;
    }

    @DataProvider(name = "company_information_English_labels")
    public Object[][] companyInformationLabels(){
        Object[][] labels = ExcelParser.passToDataProvider(file.getAbsolutePath(), "labels", "english", null);
        return labels;
    }

    @DataProvider(name = "positive_branchDetails")
    public Object[][] branchDetailsPositiveData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "branchDetails", "english", "positive");
        return data;
    }
    @DataProvider(name = "negative_branchDetails")
    public Object[][] branchDetailsNegativeData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "branchDetails", "english", "negative");
        return data;
    }
    @DataProvider(name = "positive_activityDetails")
    public Object[][] activityDetailsPositiveData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "activityDetails", "english", "positive");
        return data;
    }
    @DataProvider(name = "negative_activityDetails")
    public Object[][] activityDetailsNegativeData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "activityDetails", "english", "negative");
        return data;
    }

    @DataProvider(name = "negative_addressDetails")
    public Object[][] negativeAddressDetailsData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "addressDetails", "english", "negative");
        return data;
    }

    @DataProvider(name = "positive_addressDetails")
    public Object[][] positiveAddressDetailsData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "addressDetails", "english", "positive");
        return data;
    }

    @DataProvider(name = "negative_contactPerson")
    public Object[][] negativeContactPersonData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "contactPerson", "english", "negative");
        return data;
    }

    @DataProvider(name = "positive_contactPerson")
    public Object[][] positiveContactPersonData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "contactPerson", "english", "positive");
        return data;
    }

    @DataProvider(name = "negative_shareholderPerDetails")
    public Object[][] negativeShareholderPerDetailsData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "shareholderPerDetails", "english", "negative");
        return data;
    }

    @DataProvider(name = "positive_shareholderPerDetails")
    public Object[][] positiveShareholderPerDetailsData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "shareholderPerDetails", "english", "positive");
        return data;
    }

    @DataProvider(name = "negative_shareholderDetails")
    public Object[][] negativeShareholderDetailsData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "shareholderDetails", "english", "negative");
        return data;
    }

    @DataProvider(name = "positive_shareholderDetails")
    public Object[][] positiveShareholderDetailsData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "shareholderDetails", "english", "positive");
        return data;
    }

    @DataProvider(name = "positive_communicationDetails")
    public Object[][] positiveCommunicationDetailsData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "communicationDetails", "english", "positive");
        return data;
    }

    @DataProvider(name = "positive_financialDetails")
    public Object[][] positiveFinancialDetailsData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "financialDetails", "english", "positive");
        return data;
    }
    @DataProvider(name = "positive_password")
    public Object[][] positivePasswordData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "password", "english", "positive");
        return data;
    }

    @Test(enabled = true, dataProvider = "company_registration_English_labels", groups={"regression"}, priority = 0)
    public void verifyCompanyRegistrationLabels(Map<String, String> labels){
        SoftAssert softAssertion = new SoftAssert();
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String t = companyRegisterPage.getFieldsLabels(entry.getKey());
            softAssertion.assertEquals(t, entry.getValue());
        }
        softAssertion.assertAll();
    }

    @Test(enabled = true, priority = 1, groups={"regression"})
    public void refreshButtonInCompanyRegistration(){
        String t = companyRegisterPage.getCaptchaText();
        companyRegisterPage.clickRefreshButton();

        Assert.assertNotEquals(t, companyRegisterPage.getCaptchaText());
    }

    @Test(enabled = true, dataProvider = "negative_company_registration", groups={"regression"}, priority = 2)
    public void negativeCompanyRegistration(Map<String, String> rowOfData) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // fill form
        companyRegisterPage.fillData(rowOfData);
        // click continue
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        companyRegisterPage.clickContinueButton();
        // wait for possible navigation to the next page
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssertion = new SoftAssert();
        // verify page hasn't changed
        softAssertion.assertTrue(companyRegisterPage.checkPage(),"Verify page hasn't changed");
        // go back if page has changed
        if(!companyRegisterPage.checkPage()) companyRegisterPage.clickBackButton();
        // verify element error messages
        if(rowOfData.get("error_message") != null){
            softAssertion.assertEquals(companyRegisterPage.getErrorMessage(rowOfData), rowOfData.get("error_message"));
        }
        // verify page errors if any
        if(rowOfData.get("page_error") != null) {
            softAssertion.assertEquals(companyRegisterPage.getPageErrorMessage(), rowOfData.get("page_error"));
        }
        softAssertion.assertAll();

    }

    @Test(enabled = true, dataProvider = "positive_company_registration", groups={"regression", "smoke"}, priority = 3)
    public void positiveCompanyRegistration(Map<String, String> rowOfData) {
        // fill form
        companyRegisterPage.fillData(rowOfData);
        // click continue
        companyRegisterPage.clickContinueButton();
        SoftAssert softAssertion = new SoftAssert();
        // wait for possible navigation to the next page
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // verify page has changed
        otpPage = new OTP(driver);
        softAssertion.assertTrue(otpPage.verifyOTP(), "Verify page has changed");
        softAssertion.assertAll();
        otpPage.enterOTP();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        companyInformationPage = new CompleteInformation(driver);
    }
    @Test(enabled = true, dataProvider = "company_information_English_labels", groups={"regression"}, priority = 4)
    public void verifyCompanyInformationLabels(Map<String, String> labels){
        SoftAssert softAssertion = new SoftAssert();
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            if(entry.getKey().equals("legal_entity_nonResident")){
                companyInformationPage.clickNonResident();
                String t = companyInformationPage.getFieldsLabels(entry.getKey());
                softAssertion.assertEquals(t, entry.getValue());
            }else if(entry.getKey().equals("residency_status")){
                companyInformationPage.clickResidentButton();
                String t = companyInformationPage.getFieldsLabels(entry.getKey());
                softAssertion.assertEquals(t, entry.getValue());
            }else if(entry.getKey().equals("residency_status_KSA")){
                companyInformationPage.clickIsTopManagementInKSAButton();
                String t = companyInformationPage.getFieldsLabels(entry.getKey());
                softAssertion.assertEquals(t, entry.getValue());
            }

        }
        softAssertion.assertAll();
    }
    @Test(enabled = true, dataProvider = "negative_company_information", groups={"regression"}, priority = 5)
    public void negativeCompanyInformationTest(Map<String, String> data) throws InterruptedException {
        companyInformationPage.fillData(data);
        Thread.sleep(1000);
        companyInformationPage.clickContinueButton();
        Thread.sleep(2000);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(companyInformationPage.getErrorMessages(data), data.get("error_message"));
//        if(data.get("Attachment") != null){
//            softAssert.assertTrue(companyInformationPage.verifyDeleteAttachmentIsDisplayed());
//        }
        softAssert.assertAll();
    }


    @Test(enabled = true, dataProvider = "positive_company_information", groups={"regression", "smoke"}, priority = 6)
    public void positiveCompanyInformationTest(Map<String, String> data) throws InterruptedException {
        companyInformationPage = new CompleteInformation(driver);
        companyInformationPage.fillData(data);
        SoftAssert softAssert = new SoftAssert();
        if(companyInformationPage.verifyAttachmentIsUploaded()) {
            softAssert.assertFalse(companyInformationPage.verifyDeleteAttachmentIsDisplayed(), "Verify attachment delete button is not displaying");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        companyInformationPage.clickContinueButton();

        softAssert.assertTrue(companyInformationPage.checkAddNewBranchIsDisplayedButton(), "Verify Page has changed");
        softAssert.assertAll();

        companyInformationPage.clickAddNewBranch();
        Thread.sleep(1000);
        addNewBranch = new AddNewBranch(driver);
    }


    @Test(enabled = true, dataProvider = "negative_branchDetails", groups={"regression"}, priority = 7)
    public void branchDetailsNegativeTest(Map<String, String> data){
        addNewBranch.fillBranchDetails(data);
        addNewBranch.clickActivityDetailsTab();
        SoftAssert softAssert = new SoftAssert();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(data.get("error_message") != null){
            softAssert.assertEquals(addNewBranch.getBranchDetailsErrorMessages(data), data.get("error_message"));
        }
        if(addNewBranch.checkActivityPageIsDisplayed()) addNewBranch.clickBranchDetailsTab();
        softAssert.assertAll();

    }

    @Test(enabled = true, dataProvider = "positive_branchDetails", groups={"regression", "smoke"}, priority = 8)
    public void branchDetailsPositiveTest(Map<String, String> data){
        addNewBranch.fillBranchDetails(data);
        addNewBranch.clickActivityDetailsTab();
        SoftAssert softAssert = new SoftAssert();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssert.assertTrue(addNewBranch.checkActivityPageIsDisplayed(), "Verify Page has changed");
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "negative_activityDetails", groups={"regression"}, priority = 9)
    public void activityDetailsNegativeTest(Map<String, String> data){
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.fillActivityDetails(data);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!addNewBranch.checkActivityDetailsButtonsClicked()) {
            addNewBranch.clickSaveButton();
            if(data.get("popup_errors") != null) {
                softAssert.assertEquals(addNewBranch.getActivityDetailsPopupErrors(data.get("popup_errors")), data.get("popup_errors"));
            }
            addNewBranch.clickClosePopupMessage();
        }
        addNewBranch.clickAddressDetailsTab();
        if(data.get("error_message") != null) {
            softAssert.assertEquals(addNewBranch.getActivityDetailsErrorMessages(data), data.get("error_message"));
        }
        negativeActivityDetailsRun = true;
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "positive_activityDetails", groups={"regression", "smoke"}, priority = 10)
    public void activityDetailsPositiveTest(Map<String, String> data){
        if(addNewBranch.checkAddressDetailsPageIsDisplayed()) addNewBranch.clickActivityDetailsTab();

        if(negativeActivityDetailsRun){
            addNewBranch.resetFlags(data);
        }
        addNewBranch.fillActivityDetails(data);

        SoftAssert softAssert = new SoftAssert();
        addNewBranch.clickAddressDetailsTab();
        if(addNewBranch.addressCardVisible()){
            addNewBranch.clickAddressCard();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        softAssert.assertTrue(addNewBranch.checkAddressDetailsPageIsDisplayed(), "Verify Page has changed");
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "negative_addressDetails", groups={"regression"}, priority = 11)
    public void addressDetailsNegativeTest(Map<String, String> data){
        addNewBranch.fillAddressDetails(data);
        addNewBranch.clickContactPersonTab();
        SoftAssert softAssert = new SoftAssert();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssert.assertFalse(addNewBranch.checkContactPageIsDisplayed(),"Verify page hasn't changed");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(addNewBranch.checkContactPageIsDisplayed()) addNewBranch.clickAddressDetailsTab();
        if(data.get("error_message") != null){
            softAssert.assertEquals(addNewBranch.getAddressDetailsErrorMessages(data), data.get("error_message"));
        }
        if(data.get("page_error") != null){
            addNewBranch.clickSaveButton();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            softAssert.assertEquals(addNewBranch.getAddressDetailsPageErrorMessage(), data.get("page_error"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(addNewBranch.checkContactPageIsDisplayed()) addNewBranch.clickAddressDetailsTab();
        }
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "positive_addressDetails", groups={"regression", "smoke"}, priority = 12)
    public void addressDetailsPositiveTest(Map<String, String> data){
        addNewBranch.fillAddressDetails(data);
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.clickContactPersonTab();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssert.assertTrue(addNewBranch.checkContactPageIsDisplayed(), "Verify Page has changed");
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "negative_contactPerson", groups={"regression"}, priority = 13)
    public void contactPersonNegativeTest(Map<String, String> data){
        addNewBranch.fillContactPerson(data);
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.clickSaveButton();

        if(data.get("error_message") != null){
            softAssert.assertEquals(addNewBranch.getContactPersonErrorMessages(data), data.get("error_message"));
        }
        if(data.get("page_error") != null){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            softAssert.assertEquals(addNewBranch.getContactPersonPageErrorMessage(), data.get("page_error"));
        }

        if(addNewBranch.contactPageIsClosed()){
            softAssert.assertTrue(addNewBranch.checkContactPageIsDisplayed(),"Verify page hasn't changed");
        }
        if(!addNewBranch.contactPageIsClosed()){ //if a test case closes add branch, reopen it and navigate back to contact person
            addNewBranch.clickEditAddNewBranch();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addNewBranch.clickActivityDetailsTab();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addNewBranch.clickAddressDetailsTab();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addNewBranch.clickContactPersonTab();
        }
        softAssert.assertAll();

    }


    @Test(enabled = true, dataProvider = "positive_contactPerson", groups={"regression", "smoke"}, priority = 14)
    public void contactPersonPositiveTest(Map<String, String> data){
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.fillContactPerson(data);
        addNewBranch.clickSaveButton();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        companyInformationPage.clickContinueButton();
        softAssert.assertTrue(companyInformationPage.checkAddNewShareholdersIsDisplayed(), "Verify Page has changed");
        companyInformationPage.clickAddNewShareholders();
        addNewShareholders = new AddNewShareholders(driver);
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "negative_shareholderPerDetails", groups={"regression"}, priority = 15)
    public void negativeShareholderPercentageDetails(Map<String, String> data){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssert = new SoftAssert();
        addNewShareholders.fillShareholderPercentageDetails(data);
        addNewShareholders.clickShareholderDetailsTab();
        if(data.get("error_message") != null){
            softAssert.assertEquals(addNewShareholders.getShareholderPerDetailsErrorMessages(data), data.get("error_message"));
        }
        softAssert.assertFalse(addNewShareholders.checkShareholderDetailsPage(), "Verify page hasn't change");
        softAssert.assertAll();

    }

    @Test(enabled = true, dataProvider = "positive_shareholderPerDetails", groups={"regression", "smoke"}, priority = 16)
    public void positiveShareholderPercentageDetails(Map<String, String> data){
        if(!addNewShareholders.checkShareholderPage()){
            companyInformationPage.clickContinueButton();
            companyInformationPage.clickAddNewShareholders();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addNewShareholders.fillShareholderPercentageDetails(data);
        addNewShareholders.clickShareholderDetailsTab();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addNewShareholders.checkShareholderDetailsPage(), "Verify page has changed");

        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "negative_shareholderDetails", groups={"regression"}, priority = 17)
    public void shareholderDetailsNegative(Map<String, String> data) {
        SoftAssert softAssert = new SoftAssert();
        addNewShareholders.fillShareholdersDetails(data);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(data.get("popup_error") != null){
            softAssert.assertEquals(addNewShareholders.getShareholderDetailsPopupErrors(data.get("popup_error")),data.get("popup_error"));
            addNewShareholders.clickPopupOkButton();
        }
        addNewShareholders.clickCommunicationDetailsTab();
        if(data.get("error_message") != null){
            softAssert.assertEquals(addNewShareholders.getShareholderDetailsErrorMessages(data),data.get("error_message"));
        }
        if(data.get("page_error") != null){
            softAssert.assertEquals(addNewShareholders.getShareholderDetailsPageErrorMessage(), data.get("page_error"));
        }
        softAssert.assertFalse(addNewShareholders.checkCommunicationDetailsIsDisplayed(), "Verify page hasn't change");
        softAssert.assertAll();

    }

    @Test(enabled = true, dataProvider = "positive_shareholderDetails", groups={"regression", "smoke"}, priority = 18)
    public void shareholderDetailsPositive(Map<String, String> data){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addNewShareholders.fillShareholdersDetails(data);
        addNewShareholders.clickCommunicationDetailsTab();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addNewShareholders.checkCommunicationDetailsIsDisplayed(), "Verify page has changed");

        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "positive_communicationDetails", groups={"regression", "smoke"}, priority = 19)
    public void communicationDetailsPositive(Map<String, String> data){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addNewShareholders.fillCommunicationDetails(data);
        addNewShareholders.clickSaveButton();
    }

    @Test(enabled = true, dataProvider = "positive_financialDetails" , groups={"regression", "smoke"}, priority = 20)
    public void positive_financialDetails(Map<String, String> data){
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        companyInformationPage.clickContinueButton();
        financialDetails = new FinancialDetails(driver);

        financialDetails.fillFinancialData(data);
        companyInformationPage.clickContinueButton();
        SoftAssert softAssert = new SoftAssert();

        createNewPassword = new CreateNewPassword(driver);
        softAssert.assertTrue(createNewPassword.checkPasswordPageIsDisplayed(), "Verify page has changed");

        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "positive_password", groups={"regression", "smoke"}, priority = 21)
    public void positivePasswordTest(Map<String, String> data){
        createNewPassword.fillData(data);
        createNewPassword.checkAgreeCheckBox();
        createNewPassword.confirmButton();
        createNewPassword.closeSurvey();

    }
    @Test(enabled = true, groups={"regression", "smoke"}, priority = 22)
    public void acknowledgmentTest(){
        acknowledgement = new Acknowledgement(driver);
        acknowledgement.getApplicationNumber();
        acknowledgement.getTpName();
        acknowledgement.getSystemDateAndCompare();

    }

}