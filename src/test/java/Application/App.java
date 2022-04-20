package Application;

import AuditorRegistration.AuditorRegistration;
import CharitiesandNonProfitOrganizationSignup.CharitiesAndNonProfitOrganizationRegistrationTest;
import CompanyRegistration.CompanyRegistrationTest;
import EstablishmentRegistration.EstablishmentRegisterTest;
import GovernmentalAndSubGovermentalRegistration.GovernmentalAndSubGovernmentalSignUpTest;
import IndividualRegistration.IndividualsTest;
import NaturalGasCompanyRegistration.NaturalGasCompanyRegistration;
import NonRegularTaxpayerTestNG.NonRegularTaxpayerTest;
import NonResidentCompanyRegistration.NonResidentCompanyRegistrationTest;
import OilHydrocarbonCompanyRegistration.OilHydrocarbonCompanyRegistration;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import tmp.TmpTest;

import java.util.*;

public class App {

    public void runTest(String module){

        TestNG testSuite = new TestNG();
        XmlSuite mySuite = new XmlSuite();
        XmlTest myTest = new XmlTest(mySuite);
        HashMap<String,String> testngParams = new HashMap<String,String>();
        List<String> groups = Arrays.asList("regression", "smoke");
        XmlClass myClass = new XmlClass();

        switch (module) { //switch (String.valueOf(data.get("module")))
            case "Establishment Registration":
                mySuite.setName("Establishment Registration");
                myTest.setName("Establishment Registration");
                testngParams.put("id", "NewData"); //read this from document backend
                testngParams.put("dob", "NewData");
                myClass.setClass(EstablishmentRegisterTest.class);
                //testSuite.setTestClasses(new Class[]{EstablishmentRegisterTest.class});
                testSuite.setDefaultSuiteName("Establishment Registration");
                testSuite.setDefaultTestName("Establishment Registration");
                break;
            case "Company Registration":
                mySuite.setName("Company Registration");
                myTest.setName("Company Registration");
                testngParams.put("id", "NewData"); //update this
                myClass.setClass(CompanyRegistrationTest.class);
                //testSuite.setTestClasses(new Class[]{CompanyRegistrationTest.class});
                testSuite.setDefaultSuiteName("Company Registration");
                testSuite.setDefaultTestName("Company Registration");
                break;
            case "Auditor Registration":
                mySuite.setName("Auditor Registration");
                myTest.setName("Auditor Registration");
                testngParams.put("tin", null); //update this
                myClass.setClass(AuditorRegistration.class);
                //testSuite.setTestClasses(new Class[]{AuditorRegistration.class});
                testSuite.setDefaultSuiteName("Auditor Registration");
                testSuite.setDefaultTestName("Auditor Registration");
                break;
            case "Individual Registration":
                mySuite.setName("Individual Registration");
                myTest.setName("Individual Registration");
                testngParams.put("id", "NewData"); //update this
                testngParams.put("dob", "NewData");
                myClass.setClass(IndividualsTest.class);
                //testSuite.setTestClasses(new Class[]{IndividualsTest.class});
                testSuite.setDefaultSuiteName("Individual Registration");
                testSuite.setDefaultTestName("Individual Registration");
                break;
            case "Non Regular Taxpayer Registration":
                mySuite.setName("Non Regular Taxpayer Registration");
                myTest.setName("Non Regular Taxpayer Registration");
                myClass.setClass(NonRegularTaxpayerTest.class);
                //testSuite.setTestClasses(new Class[]{NonRegularTaxpayerTest.class});
                testSuite.setDefaultSuiteName("Non Regular Taxpayer Registration");
                testSuite.setDefaultTestName("Non Regular Taxpayer Registration");
                break;
            case "Non Resident Company Registration":
                mySuite.setName("Non Resident Company Registration");
                myTest.setName("Non Resident Company Registration");
                myClass.setClass(NonResidentCompanyRegistrationTest.class);
                //testSuite.setTestClasses(new Class[]{NonResidentCompanyRegistrationTest.class});
                testSuite.setDefaultSuiteName("Non Resident Company Registration");
                testSuite.setDefaultTestName("Non Resident Company Registration");
                break;
            case "Oil & Hydrocarbon Registration":
                mySuite.setName("Oil & Hydrocarbon Registration");
                myTest.setName("Oil & Hydrocarbon Registration");
                testngParams.put("id", "NewData"); //update this
                myClass.setClass(OilHydrocarbonCompanyRegistration.class);
                //testSuite.setTestClasses(new Class[]{OilHydrocarbonCompanyRegistration.class});
                testSuite.setDefaultSuiteName("Oil & Hydrocarbon Registration");
                testSuite.setDefaultTestName("Oil & Hydrocarbon Registration");
                break;
            case "Governmental & Sub Governmental Registration":
                mySuite.setName("Governmental & Sub Governmental Registration");
                myTest.setName("Governmental & Sub Governmental Registration");
                testngParams.put("id", "NewData"); //update this
                myClass.setClass(GovernmentalAndSubGovernmentalSignUpTest.class);
                //testSuite.setTestClasses(new Class[]{GovernmentalAndSubGovernmentalSignUpTest.class});
                testSuite.setDefaultSuiteName("Governmental & Sub Governmental Registration");
                testSuite.setDefaultTestName("Governmental & Sub Governmental Registration");
                break;
            case "Natural Gas Registration":
                mySuite.setName("Natural Gas Registration");
                myTest.setName("Natural Gas Registration");
                testngParams.put("id", "NewData"); //update this
                myClass.setClass(NaturalGasCompanyRegistration.class);
                //testSuite.setTestClasses(new Class[]{NaturalGasCompanyRegistration.class});
                testSuite.setDefaultSuiteName("Natural Gas Registration");
                testSuite.setDefaultTestName("Natural Gas Registration");
                break;
            case "Charities & Non Profit Registration":
                mySuite.setName("Charities & Non Profit Registration");
                myTest.setName("Charities & Non Profit Registration");
                testngParams.put("id", "NewData"); //update this
                myClass.setClass(CharitiesAndNonProfitOrganizationRegistrationTest.class);
                //testSuite.setTestClasses(new Class[]{CharitiesAndNonProfitOrganizationRegistrationTest.class});
                testSuite.setDefaultSuiteName("Charities & Non Profit Registration");
                testSuite.setDefaultTestName("Charities & Non Profit Registration");
                break;

            case "TmpTest":
                mySuite.setName("TmpTest");
                myTest.setName("TmpTest");
                myTest.setIncludedGroups(groups);
                //testngParams.put("id", "NewData"); //update this
                myClass.setClass(TmpTest.class);
                testSuite.setDefaultSuiteName("TmpTest");
                testSuite.setDefaultTestName("TmpTest");
                break;
        }


        myTest.setParameters(testngParams);
        myTest.getClasses().add(myClass);
        List<XmlSuite> mySuitesList = new ArrayList<XmlSuite>();
        mySuitesList.add(mySuite);

        testSuite.setXmlSuites(mySuitesList);
        testSuite.run();


    }


    public static void main(String[] args){

        App app = new App();
        Scanner in = new Scanner(System.in);

        System.out.println("Please Enter a Module:");

        app.runTest(in.nextLine());


    }


}

