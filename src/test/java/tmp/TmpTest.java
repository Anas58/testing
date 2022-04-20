package tmp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v95.log.Log;
import org.openqa.selenium.devtools.v95.network.Network;
import org.openqa.selenium.devtools.v95.network.model.Cookie;
import org.openqa.selenium.devtools.v95.network.model.Headers;
import org.openqa.selenium.devtools.v95.network.model.Request;
import org.openqa.selenium.devtools.v95.network.model.RequestId;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class TmpTest {

    ChromeDriver driver;
    Tmp tmpp;

    @BeforeTest(groups={"regression", "smoke"})
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.get("https://toportalqa.gazt.gov.sa/irj/portal?ume.logon.locale=en");
//        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        tmpp = new Tmp(driver);

    }

    @DataProvider(name = "search_data")
    public Object[][] loginData(){
        Object[][] data = utilities.ExcelParser.passToDataProvider("C://Users//abdulkarim-c//Desktop//Book1.xlsx", "search", "english", null);
        return data;
    }


    @Test(enabled = false, dataProvider = "search_data")
    public void test(Map <String, String> data, ITestContext context){
        String testParam = context.getCurrentXmlTest().getParameter("field");
        System.out.println("this is test param before update: " + testParam);

        data.put("search", testParam);
        tmpp.fillSearch(data);

    }


    @Test(enabled = true)
    public void tmp(){

        tmpp.login("nrole_off", "Test@123");

//        try {
//            Thread.sleep(16000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));



        devTools.addListener(Network.requestWillBeSent(), requestSent -> {

            System.out.println("Request URL => " + requestSent.getRequest().getUrl());

            System.out.println("Request Method => " + requestSent.getRequest().getMethod());

            System.out.println("Request Headers => " + requestSent.getRequest().getHeaders());

            System.out.println("-------------------------------------------------");

        });

        tmpp.clickLogin();
        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuilder myString = new StringBuilder();
        List<Cookie> cookies = devTools.send(Network.getAllCookies());
        for(Cookie cookie : cookies){
            //System.out.println("Cookie: " + cookie.getName() + " " + cookie.getValue());
            myString.append(cookie.getName()).append("=").append(cookie.getValue()).append(";").append(" ");


        }
        System.out.println(myString);
//        RequestId[] requestIds = new RequestId[100];
//        devTools.addListener(Network.requestWillBeSent(), request -> {
//            Request req = request.getRequest();
//            //System.out.println("Request URL " + req.getUrl());
//            requestIds[0] = request.getRequestId();
//            System.out.println("Request ID: " + request.getRequestId().toString());
//        });
//
//        devTools.addListener(Network.responseReceived(), response -> {
////          Response res = response.getResponse();
//
//            System.out.println("Response URL " + response.getResponse().getUrl());
//            int status = response.getResponse().getStatus();
//            System.out.println("Status code is " + status);
//            String headers = response.getResponse().getHeaders().toString();
//            System.out.println("Response Headers " + headers);
//            System.out.println("Response Time is " + response.getResponse().getResponseTime());
//            String type = response.getType().toJson();
//            String responseBody = devTools.send(Network.getResponseBody(requestIds[0])).getBody();
//            System.out.println("Response Body: " + responseBody);
//            if (response.getResponse().getStatus().toString().startsWith("4")) {
//                System.out.println(response.getResponse().getUrl() + " is failing with status code "
//                        + response.getResponse().getStatus());
//            }
//        });


//        devTools.addListener(Network.responseReceived(), responseReceieved -> {
//
//            System.out.println("Response Url => " + responseReceieved.getResponse().getUrl());
//
//            System.out.println("Response Status => " + responseReceieved.getResponse().getStatus());
//
//            System.out.println("Response Headers => " + responseReceieved.getResponse().getHeaders().toString());
//
//            System.out.println("------------------------------------------------------");
//
//        });

//        tmpp.clickInbox();


//        devTools.addListener(Network.requestWillBeSent(), request -> {
//            Headers header = request.getRequest().getHeaders();
//            if (!header.isEmpty()) {
//                System.out.println("Request Headers: ");
//                header.forEach((key, value) -> {
//                    System.out.println(" " + key + " = " + value);
//                });
//            }
//        });


        System.out.println("\n\nCookies: \n");

        System.out.println(driver.manage().getCookies().toString());


        devTools.send(Network.disable());


    }
}