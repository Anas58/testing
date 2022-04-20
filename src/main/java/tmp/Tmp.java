package tmp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class Tmp {
    WebDriver driver;

    public Tmp(WebDriver driver) {
        this.driver = driver;
    }


    private final By search = By.xpath("//input[@name='q']");
    By userName = By.xpath("//input[@class='username']");
    By password = By.xpath("//input[@class='password']");
    By loginButton = By.name("uidPasswordLogon");
    By inbox = By.xpath("//div[@id='inbox']");


    public void login(String user, String pass){
        driver.findElement(userName).sendKeys(user);
        driver.findElement(password).sendKeys(pass);

    }

    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

    public void clickInbox(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("contentAreaFrame"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inbox)).click();
    }

    public void fillSearch(Map <String, String> data){
        driver.findElement(search).sendKeys(data.get("search"));
    }

}
