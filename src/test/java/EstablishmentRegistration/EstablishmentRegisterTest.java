package EstablishmentRegistration;

import CompanyRegistrationPages.ExcelParser;
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
public class EstablishmentRegisterTest {

    WebDriver driver;
    EstablishmentRegister establishmentRegisterPage;
    Otp otpPage;
    CompleteInformation completeInformationPage;
    AddNewBranch addNewBranch;
    FinancialDetails financialDetailsPage;
    CreateNewPassword passwordPage;
    Acknowledgement acknowledgementPage;
    OfficerPortal officerPortalPage;
    String tpApplicationNumber;
    boolean negativeActivityDetailsRun;
    File file = new File("../resources/ExcelData/establishment_test_data.xlsx");

    static {
        System.setProperty("extent.reporter.spark.start", "true");
        System.setProperty("extent.reporter.spark.config", "../resources/avent-config.xml");
        System.setProperty("extent.reporter.spark.out", "../resources/");
    }

    @BeforeTest
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "../resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://angularqa.gazt.gov.sa/angular/#/signup/establishment");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        establishmentRegisterPage = new EstablishmentRegister(driver);
    }

    //verify validation message on fields and page levels.
    @DataProvider(name = "Negative_Establishment_Registration")
    public Object[][] testDataNegative() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "establishment", "english", "negative");
        return data;
    }

    //Verifying Labels Establishment Page
    @DataProvider(name = "labels")
    public Object[][] labelsData() {
        Object[][] labels = ExcelParser.passToDataProvider(file.getAbsolutePath(), "establishment_labels", "english", null);
        return labels;
    }

    //Entering Positive data to continue Next page.
    @DataProvider(name = "Positive_Establishment_Registration")
    public Object[][] testData() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "establishment", "english", "positive");
        return data;
    }

    //Verifying Labels Facility Page
    @DataProvider(name = "Facility_Information_Labels")
    public Object[][] labelsDataFacilityInformation() {
        Object[][] labels = ExcelParser.passToDataProvider(file.getAbsolutePath(), "facility_info_labels", "english", null);
        return labels;
    }

    //Verifying validation messages on fields and page levels. Negative TC
    @DataProvider(name = "negative_complete_Information")
    public Object[][] testDataFacilityInformationNegative() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "facility_information", "english", "negative");
        return data;
    }


    //Entering Positive data to continue Next Branch Page.
    @DataProvider(name = "positive_complete_Information")
    public Object[][] testDataFacilityInformation() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "facility_information", "english", "positive");
        return data;
    }

    //Filling Negative Branch Details
    @DataProvider(name = "Negative_Branch_Details")
    public Object[][] testBranchDetailsNegative() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "branch_details", "english", "negative");
        return data;
    }

    //Verifying Labels Branch Filed
    @DataProvider(name = "Branch_Name_Labels")
    public Object[][] branchNameLabels() {
        Object[][] labels = ExcelParser.passToDataProvider(file.getAbsolutePath(), "branch_labels", "english", null);
        return labels;
    }

    //Filling Branch Details positive and continue Activity Details
    @DataProvider(name = "Positive_Branch_Details")
    public Object[][] testBranchDetails() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "branch_details", "english", "positive");
        return data;
    }

    //Filling Activity Details Negative
    @DataProvider(name = "Negative_Activity_Details")
    public Object[][] testActivityDetailsNegative() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "activityDetails", "english", "negative");
        return data;
    }
    //Filling Activity Details positive and continue Address Details
    @DataProvider(name = "Positive_Activity_Details")
    public Object[][] testDataActivityDetails() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "activityDetails", "english", "positive");
        return data;
    }

    @DataProvider(name = "negative_Address_Details")
    public Object[][] testDataAddressDetailsNegative() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "addressDetails", "english", "negative");
        return data;
    }

    //Filling Address Details
    @DataProvider(name = "Positive_Address_Details")
    public Object[][] testDataAddressDetails() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "addressDetails", "english", "positive");
        return data;
    }

    //Filling Financial Details
    @DataProvider(name = "Positive_Financial_Details")
    public Object[][] testDataFinancialDetails() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "financialdetails", "english", "positive");
        return data;
    }

    //Filling Financial Details Negative test
    @DataProvider(name = "Negative_Financial_Details")
    public Object[][] testDataFinancialDetailsNegative() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "financialdetails", "english", "negative");
        return data;
    }

    //Password Positive Test Cases
    @DataProvider(name = "Positive_Password")
    public Object[][] testDataPasswordPositive() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "password", "english", "positive");
        return data;
    }

    //Password Negative Test Cases
    @DataProvider(name = "Negative_Password")
    public Object[][] testDataPasswordNegative() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "password", "english", "negative");
        return data;
    }


    @Test(enabled = true, dataProvider = "labels", groups={"regression"}, priority = 0)
    public void verifyLabels(Map<String, String> data) throws InterruptedException {
        establishmentRegisterPage.selectIdType(data.get("id_type"));
        Thread.sleep(1000);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (entry.getKey().equals("id_type") || entry.getValue() == null) {
                continue;
            }
            String result = establishmentRegisterPage.fieldsLabel(data.get("id_type"), entry.getKey());
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(result, entry.getValue());
            softAssert.assertAll();
        }
    }

    @Test(enabled = true, dataProvider = "Negative_Establishment_Registration", groups={"regression"}, priority = 1)
    public void negativeEstablishmentRegistrationTest(Map<String, String> data) throws InterruptedException {
        establishmentRegisterPage.fillData(data);
        Thread.sleep(2000);
        establishmentRegisterPage.clickContinueButton();
        Thread.sleep(3000);
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(establishmentRegisterPage.checkPage(), "Verify page hasn't changed");
        if (!establishmentRegisterPage.checkPage()) {
            establishmentRegisterPage.backButton();
        }
        if (data.get("page_error") != null) {
            softAssertion.assertEquals(establishmentRegisterPage.getPageErrorMessage(), data.get("page_error"));
        }
        if (data.get("error_message") != null) {
            softAssertion.assertEquals(establishmentRegisterPage.fieldValidateErrorMessage(data), data.get("error_message"));
        }
        softAssertion.assertAll();

        driver.navigate().refresh();
    }

    @Test(enabled = true, dataProvider = "Positive_Establishment_Registration", groups={"regression", "smoke"}, priority = 2)
    public void positiveEstablishmentRegistrationTest(Map<String, String> data) throws InterruptedException {
        establishmentRegisterPage.fillData(data);
        establishmentRegisterPage.storeName();
        establishmentRegisterPage.clickContinueButton();
        SoftAssert softAssertion = new SoftAssert();
        otpPage = new Otp(driver);
        softAssertion.assertTrue(otpPage.verifyOTP(), "Verify page has changed");

        softAssertion.assertAll();
    }


    //Entering Static Captcha
    @Test(enabled = false, priority = 2)
    public void verifyRefreshIcon() {
        String captchaValue = establishmentRegisterPage.storeCaptcha();
        System.out.println(captchaValue);
        establishmentRegisterPage.refreshCaptcha();
        Assert.assertNotEquals(establishmentRegisterPage.storeCaptcha(), captchaValue);
    }

    //Calling Otp Test Case pages
    @Test(enabled = true, groups={"regression", "smoke"}, priority = 3)
    public void EnteringOTP() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        otpPage.enterOTP();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        completeInformationPage = new CompleteInformation(driver);
    }

    @Test(enabled = true, dataProvider = "Facility_Information_Labels", groups={"regression"}, priority = 4)
    public void verifyLabelsFacilityInformation(Map<String, String> data) throws InterruptedException {
        completeInformationPage.selectResidencyStatus(data);
        Thread.sleep(1000);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (entry.getKey().equals("residency_status") || entry.getValue() == null) {
                continue;
            }
            String result = completeInformationPage.facilityInformationFieldsLabel(data.get("residency_status"), entry.getKey());
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(result, entry.getValue());
            softAssert.assertAll();
        }
    }

    @Test(enabled = true, dataProvider = "negative_complete_Information", groups={"regression"}, priority = 5)
    public void negativeCompleteInfoTest(Map<String, String> data) {
        completeInformationPage.fillData(data);
        completeInformationPage.clickContinueButton();
        SoftAssert softAssertion = new SoftAssert();
        //softAssertion.assertTrue(page.checkPage());
        if (data.get("error_messages") != null) {
            softAssertion.assertEquals(completeInformationPage.fieldValidateErrorMessage(data), data.get("error_messages"));
        }
        softAssertion.assertAll();

    }


    @Test(enabled = true, dataProvider = "positive_complete_Information", groups={"regression", "smoke"}, priority = 6)
    public void positiveCompleteInfoTest(Map<String, String> data) {
        SoftAssert softAssert = new SoftAssert();
        completeInformationPage.fillData(data);
        completeInformationPage.clickContinueButton();

        softAssert.assertTrue(completeInformationPage.checkAddNewBranchIsDisplayedButton(), "Verify Page has changed");
        softAssert.assertAll();

        completeInformationPage.clickAddBranch();
        addNewBranch = new AddNewBranch(driver);
    }


    @Test(enabled = true, dataProvider = "Branch_Name_Labels", groups={"regression"}, priority = 7)
    public void verifyBranchNameLabel(Map<String, String> data) throws InterruptedException {
        Thread.sleep(1000);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(addNewBranch.verifyBranchLabel(),data.get("branch_name"));
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "Negative_Branch_Details", groups={"regression"}, priority = 8)
    public void branchDetailsNegativeTest(Map<String, String> data) {
        addNewBranch.fillBranchDetails(data);
        addNewBranch.clickActivityDetailsTab();
        SoftAssert softAssertion = new SoftAssert();
        if (data.get("error_message") != null) {
            softAssertion.assertEquals(addNewBranch.getBranchDetailsErrorMessages(data), data.get("error_messages"));
        }
        if(addNewBranch.checkActivityPageIsDisplayed()) addNewBranch.clickBranchDetailsTab();
        softAssertion.assertAll();
    }

    @Test(enabled = true, dataProvider = "Positive_Branch_Details", groups={"regression", "smoke"}, priority =9)
    public void branchDetailsPositiveTest(Map<String, String> data) {
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


    @Test(enabled = true, dataProvider = "Negative_Activity_Details", groups={"regression"}, priority = 10)
    public void activityDetailsNegativeTest(Map<String, String> data) {
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

    @Test(enabled = true, dataProvider = "Positive_Activity_Details", groups={"regression", "smoke"}, priority = 11)
    public void activityDetailsPositiveTest(Map<String, String> data) {
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

    @Test(enabled = true, dataProvider = "negative_Address_Details", groups={"regression"}, priority = 12)
    public void addressDetailsNegativeTest(Map<String, String> data){
        addNewBranch.fillAddressDetails(data);
        addNewBranch.clickSaveButton();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.clickClosePopupMessage();
        if(data.get("error_message") != null){
            softAssert.assertEquals(addNewBranch.getAddressDetailsErrorMessages(data), data.get("error_message"));
        }
        if(data.get("page_error") != null){
            softAssert.assertEquals(addNewBranch.getAddressDetailsPageErrorMessage(), data.get("page_error"));
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssert.assertTrue(addNewBranch.checkAddressPageIsDisplayed(), "Verify page hasn't changed");

        if(!addNewBranch.checkAddressPageIsDisplayed()) { //if a test case closes add branch, reopen it and navigate back to address details
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
        }
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "Positive_Address_Details", groups={"regression", "smoke"}, priority = 13)
    public void addressDetailsPositiveTest(Map<String, String> data) {
        addNewBranch.fillAddressDetails(data);
        addNewBranch.clickSaveButton();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addNewBranch.clickContinueButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        financialDetailsPage = new FinancialDetails(driver);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(financialDetailsPage.checkFinancialPageIsDisplayed(), "Verify Page has Changed");

        softAssert.assertAll();

    }

    @Test(enabled = true, dataProvider = "Negative_Financial_Details", groups={"regression"}, priority = 14)
    public void financialDetailsNegativeTest(Map<String, String> data) {
        financialDetailsPage.fillFinancialData(data);
        financialDetailsPage.clickOnContinueButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssertion = new SoftAssert();
        if (data.get("error_messages") != null) {
            softAssertion.assertEquals(financialDetailsPage.fieldValidateErrorMessage(data), data.get("error_messages"));
        }
        softAssertion.assertTrue(financialDetailsPage.checkFinancialPageIsDisplayed(), "Verify page hasn't changed");
        softAssertion.assertAll();
    }

    @Test(enabled = true, dataProvider = "Positive_Financial_Details", groups={"regression", "smoke"}, priority = 15)
    public void financialDetailsPositiveTest(Map<String, String> data) {
        financialDetailsPage.fillFinancialData(data);
        financialDetailsPage.clickOnContinueButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssert = new SoftAssert();
        passwordPage = new CreateNewPassword(driver);

        softAssert.assertTrue(passwordPage.checkPasswordPageIsDisplayed(), "Verify page has changed");

        softAssert.assertAll();
    }


    @Test(enabled = true, dataProvider = "Negative_Password", groups={"regression"}, priority = 16)
    public void passwordNegativeTest(Map<String, String> data) {
        passwordPage.fillData(data);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssertion = new SoftAssert();
        if (data.get("error_messages") != null) {
            softAssertion.assertEquals(passwordPage.fieldValidateErrorMessage(data), data.get("error_messages"));
        }
        softAssertion.assertTrue(passwordPage.checkPasswordPageIsDisplayed(), "Verify page hasn't changed");
        softAssertion.assertAll();
    }


    @Test(enabled = true, dataProvider = "Positive_Password", groups={"regression", "smoke"}, priority = 17)
    public void passwordPositiveTest(Map<String, String> data) {
        passwordPage.fillData(data);
        passwordPage.checkAgreeCheckBox();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        passwordPage.confirmButton();
        passwordPage.closeSurvey();
        acknowledgementPage = new Acknowledgement(driver);
    }

    @Test(enabled = true, groups={"regression", "smoke"}, priority = 18)
    public void acknowledgement_storeData() {
        acknowledgementPage.storeApplicationNumber();
        tpApplicationNumber = acknowledgementPage.getApplicationNumber();
        acknowledgementPage.getSystemDateAndCompare();
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(acknowledgementPage.getTpName(), establishmentRegisterPage.getTpFullName());
        softAssertion.assertAll();
    }

    /*@AfterTest
    public void afterTest(){
        driver.close();
        runClass();
    }

    private void runClass() {
        TestNG testSuite = new TestNG();
        testSuite.setTestClasses(new Class[] { OfficerPortalTest.class });
        //testSuite.setObjectFactory(OfficerPortalTest.class);
        testSuite.run();
    }*/

}
