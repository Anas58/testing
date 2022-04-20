package NonResidentCompanyRegistration;

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

import static org.testng.Assert.*;

@Listeners({TestListener.class})
public class NonResidentCompanyRegistrationTest {

    WebDriver driver;
    OrganizationInformation organizationInformation;
    OTP otpPage;
    CompleteInformation completeInformation;
    AddNewBranch addNewBranch;
    AddNewShareholders addNewShareholders;
    FinancialDetails financialDetails;
    CreateNewPassword createNewPassword;
    Acknowledgement acknowledgement;
    boolean negativeActivityDetailsRun;
    File file = new File("../resources/ExcelData/NonResidentCompanyRegistration.xlsx");

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
        // TODO url must be a variable
        driver.get("https://angularqa.gazt.gov.sa/angular/#/signup/others/nonResidentCompany");
        driver.manage().window().maximize();
        organizationInformation = new OrganizationInformation(driver);
    }

    @DataProvider(name = "negative_organizationInfo")
    public Object[][] negativeOrganizationInfo(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "organizationInfo", "english", "negative");
        return data;
    }
    @DataProvider(name = "positive_organizationInfo")
    public Object[][] positiveOrganizationInfo(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "organizationInfo", "english", "positive");
        return data;
    }
    @DataProvider(name = "negative_CompleteInfo")
    public Object[][] negativeCompleteInfo(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "completeInfo", "english", "negative");
        return data;
    }
    @DataProvider(name = "positive_CompleteInfo")
    public Object[][] positiveCompleteInfo(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "completeInfo", "english", "positive");
        return data;
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

    @Test(enabled = true, dataProvider = "negative_organizationInfo", groups={"regression"}, priority = 0)
    public void negativeOrganizationTest(Map<String, String> data){
        organizationInformation.fillData(data);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        organizationInformation.clickContinueButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(organizationInformation.checkPage(),"Verify page hasn't changed");

        if(!organizationInformation.checkPage()) organizationInformation.clickBackButton();

        if(data.get("error_message") != null){
            softAssertion.assertEquals(organizationInformation.getErrorMessage(data), data.get("error_message"));
        }
        if(data.get("page_error") != null) {
            softAssertion.assertEquals(organizationInformation.getPageErrorMessage(), data.get("page_error"));
        }
        softAssertion.assertAll();
    }

    @Test(enabled = true, dataProvider = "positive_organizationInfo", groups={"regression", "smoke"}, priority = 1)
    public void positiveOrganizationTest(Map<String, String> data){
        organizationInformation.fillData(data);
        organizationInformation.clickContinueButton();
        SoftAssert softAssertion = new SoftAssert();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // verify page has changed
        otpPage = new OTP(driver);
        softAssertion.assertTrue(otpPage.verifyOTP(), "verify page has changed");
        softAssertion.assertAll();
        otpPage.enterOTP();
    }

    @Test(enabled = true, dataProvider = "negative_CompleteInfo", groups={"regression"}, priority = 2)
    public void negativeCompleteInfo(Map<String, String> data){
        completeInformation = new CompleteInformation(driver);
        completeInformation.fillData(data);
        completeInformation.clickContinueButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(completeInformation.getErrorMessages(data), data.get("error_message"));
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "positive_CompleteInfo", groups={"regression", "smoke"}, priority = 3)
    public void positiveCompleteInfo(Map<String, String> data){
        completeInformation = new CompleteInformation(driver);
        SoftAssert softAssert = new SoftAssert();
        completeInformation.fillData(data);
        completeInformation.clickContinueButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        softAssert.assertTrue(completeInformation.checkAddNewBranchIsDisplayedButton(), "Verify Page has changed");
        softAssert.assertAll();

        completeInformation.clickAddNewBranch();
        addNewBranch = new AddNewBranch(driver);
    }

    @Test(enabled = true, dataProvider = "negative_branchDetails", groups={"regression"}, priority = 4)
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

    @Test(enabled = true, dataProvider = "positive_branchDetails" , groups={"regression", "smoke"}, priority = 5)
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

    @Test(enabled = true, dataProvider = "negative_activityDetails", groups={"regression"}, priority = 6)
    public void activityDetailsNegativeTest(Map<String, String> data){
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.clickActivityDetailsTab();
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

    @Test(enabled = true, dataProvider = "positive_activityDetails", groups={"regression", "smoke"}, priority = 7)
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

    @Test(enabled = true, dataProvider = "negative_addressDetails", groups={"regression"}, priority = 8)
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

    @Test(enabled = true, dataProvider = "positive_addressDetails", groups={"regression", "smoke"}, priority = 9)
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

    @Test(enabled = true, dataProvider = "negative_contactPerson", groups={"regression"}, priority = 10)
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

    @Test(enabled = true, dataProvider = "positive_contactPerson", groups={"regression", "smoke"}, priority = 11)
    public void contactPersonPositiveTest(Map<String, String> data){
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.fillContactPerson(data);
        addNewBranch.clickSaveButton();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        completeInformation.clickContinueButton();
        softAssert.assertTrue(completeInformation.checkAddNewShareholdersIsDisplayed(), "Verify Page has changed");
        completeInformation.clickAddNewShareholders();
        addNewShareholders = new AddNewShareholders(driver);
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "negative_shareholderPerDetails", groups={"regression"}, priority = 12)
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
    @Test(enabled = true, dataProvider = "positive_shareholderPerDetails", groups={"regression", "smoke"}, priority = 13)
    public void positiveShareholderPercentageDetails(Map<String, String> data){
        if(!addNewShareholders.checkShareholderPage()){
            completeInformation.clickContinueButton();
            completeInformation.clickAddNewShareholders();
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

    @Test(enabled = true, dataProvider = "negative_shareholderDetails", groups={"regression"}, priority = 14)
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

    @Test(enabled = true, dataProvider = "positive_shareholderDetails", groups={"regression", "smoke"}, priority = 15)
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

    @Test(enabled = true, dataProvider = "positive_communicationDetails", groups={"regression", "smoke"}, priority = 16)
    public void communicationDetailsPositive(Map<String, String> data){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addNewShareholders.fillCommunicationDetails(data);
        addNewShareholders.clickSaveButton();
    }

    @Test(enabled = true, dataProvider = "positive_financialDetails", groups={"regression", "smoke"}, priority = 17)
    public void positive_financialDetails(Map<String, String> data){
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        completeInformation.clickContinueButton();
        financialDetails = new FinancialDetails(driver);

        financialDetails.fillFinancialData(data);
        completeInformation.clickContinueButton();
        SoftAssert softAssert = new SoftAssert();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createNewPassword = new CreateNewPassword(driver);
        softAssert.assertTrue(createNewPassword.checkPasswordPageIsDisplayed(), "Verify page has changed");

        softAssert.assertAll();
    }


    @Test(enabled = true, dataProvider = "positive_password", groups={"regression", "smoke"}, priority = 18)
    public void positivePasswordTest(Map<String, String> data){

        createNewPassword.fillData(data);
        createNewPassword.checkAgreeCheckBox();
        createNewPassword.confirmButton();
        createNewPassword.closeSurvey();

    }
    @Test(enabled = true, groups={"regression", "smoke"}, priority = 19)
    public void acknowledgmentTest(){
        acknowledgement = new Acknowledgement(driver);
        acknowledgement.getApplicationNumber();
        acknowledgement.getTpName();
        acknowledgement.getSystemDateAndCompare();

    }



}