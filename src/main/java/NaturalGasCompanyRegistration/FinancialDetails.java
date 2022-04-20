package NaturalGasCompanyRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class FinancialDetails {
    WebDriver driver;

    public FinancialDetails(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private final By accountMethod = By.xpath("//mat-select[@formcontrolname='accounting']");
    private final By capitalAmount = By.xpath("//input[@formcontrolname='capitalAmount']");
    private final By checkCalenderTypeHijri = By.xpath("//input[@formcontrolname='hijriSelected']/parent::label");
    //By calenderTypeGregorian=By.xpath("//div//label//input[@formcontrolname='gregorianSelected']");
    private final By checkCalenderTypeGregorian = By.xpath("//input[@formcontrolname='gregorianSelected']/parent::label");
    private final By calenderTypeGregorian = By.xpath("//input[@formcontrolname=\"gregorianSelected\"]/ancestor::*[@data-toggle='buttons']");
    private final By calenderTypeHijri = By.xpath("//input[@formcontrolname=\"hijriSelected\"]/ancestor::*[@data-toggle='buttons']");
    //By calenderTypeGregorian=By.id("gregc");
    private final By endOfFinancialMonth = By.xpath("//mat-select[@formcontrolname='endOfFinancialMonth']");
    private final By endOfFinancialDay = By.xpath("//mat-select[@formcontrolname='endOfFinancialDay']");
    private final By financialPeriodOptions = By.xpath("//mat-radio-group[@formcontrolname='financialPeriod']//mat-radio-button[@class]");
    private final By taxableDate = By.xpath("//input[@formcontrolname='taxableDate']");
    private final By continueButton = By.xpath("//button[@class='rounded btnCss save-btn mat-focus-indicator mat-button mat-button-base ng-star-inserted']");
    private final By businessCommencementDate = By.xpath("//input[@formcontrolname='commencementDate']");
    private final By errorMessage=By.tagName("mat-error");


    //Dropdown Option Locator
    private final By option = By.tagName("mat-option");

    public void fillFinancialData(Map<String, String> data) {
        //Select Account Type
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        if (data.get("account_method") != null) {
            selectAccount(data.get("account_method"));
        }
        //Enter Capital Amount
        if (data.get("capital_amount") != null) {
            driver.findElement(capitalAmount).sendKeys(selectAll + Keys.BACK_SPACE);
            driver.findElement(capitalAmount).sendKeys(data.get("capital_amount"));
        }
        //Financial year
        if (data.get("financial_year") != null) {
            if (data.get("financial_year").equals("Gregorian")) {
                if(checkGregorian()){
                    driver.findElement(calenderTypeGregorian).click();
                }
            }
        }
        if (data.get("financial_year") != null) {
            if (data.get("financial_year").equals("Hijri")) {
                if (checkHijri()) {
                    driver.findElement(calenderTypeHijri).click();
                }
            }
        }
        Date date = getCommencementDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int commencementMonth = calendar.get(Calendar.MONTH);
        int commencementDay = calendar.get(Calendar.DAY_OF_MONTH);
        //Financial Month
        if (data.get("financial_month") != null) {
            if (financialMonthEnabled() == true) {
                calendar.set(Calendar.MONTH, Integer.parseInt((data.get("financial_month"))) - 1);
                selectFromList(endOfFinancialMonth, (calendar.get(Calendar.MONTH) + 1) + "");
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Financial Day
        if (data.get("financial_day") != null) {
            if (financialDayEnabled() == true) {
                int dayOfMonthInExcel = Integer.parseInt((data.get("financial_day")));
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonthInExcel);
                if (calendar.getMaximum(Calendar.DAY_OF_MONTH) == dayOfMonthInExcel) {
                    selectFromList(endOfFinancialDay, "Last Day");
                } else if (dayOfMonthInExcel < 10) {
                    selectFromList(endOfFinancialDay, "0" + dayOfMonthInExcel);
                } else {
                    selectFromList(endOfFinancialDay, "" + dayOfMonthInExcel);
                }
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//      Selecting Financial Optional
        int totalOptions = 0;
        ArrayList<Date> periodEnds = new ArrayList<>(3);
        periodEnds.add(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        periodEnds.add(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        periodEnds.add(calendar.getTime());
        for (int i = 0; i < periodEnds.size(); i++) {
            long mMonth = ChronoUnit.MONTHS.between(
                    date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    periodEnds.get(i).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            long mDay = ChronoUnit.DAYS.between(
                    date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    periodEnds.get(i).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            if (mMonth <= 0) {
                if (mDay <= 0) {
                    periodEnds.set(i, null);
                } else {
                    totalOptions++;
                }
            } else if (mMonth > 18) {
                periodEnds.set(i, null);
            } else {
                totalOptions++;
            }
        }
        if (totalOptions >= 2) {
            if (data.get("financial_period_options") != null) {
                if (data.get("financial_period_options").equals("L")) {
                    String IdLong = driver.findElements(financialPeriodOptions).get(1).getAttribute("id");
                    driver.findElement(By.xpath("//mat-radio-button[@id='" + IdLong + "']")).click();
                } else {
                    String Id = driver.findElements(financialPeriodOptions).get(0).getAttribute("id");
                    driver.findElement(By.xpath("//mat-radio-button[@id='" + Id + "']")).click();
                }
            }
        } else {
            String Id = driver.findElements(financialPeriodOptions).get(0).getAttribute("id");
            driver.findElement(By.xpath("//mat-radio-button[@id='" + Id + "']")).click();
        }
    }
    // Verifying Error messages.
    public String fieldValidateErrorMessage(Map<String, String> errorData) {
        List<WebElement> error = switch (errorData.get("error_element")) {
            case "financial_month" -> driver.findElements(with(errorMessage).near(endOfFinancialMonth,100));
            case "financial_day" -> driver.findElements(with(errorMessage).near(endOfFinancialDay,100));
            case "financial_period_options" -> driver.findElements(with(errorMessage).near(financialPeriodOptions,120));
            default -> new ArrayList<>();
        };
        if (error.size() > 0) {
            return error.get(0).getText();
        }
        return null;
    }
    // Method select Account Method
    public boolean selectAccount(String value) {
        return selectFromList(accountMethod, value);
    }

    //Method to select Value fromDropdown
    public boolean selectFromList(By element, String value) {
        driver.findElement(element).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> alldropdownoption = driver.findElements(option);
        for (int i = 0; i < alldropdownoption.size(); i++) {
            if (alldropdownoption.get(i).getText().equals(value)) {
                alldropdownoption.get(i).click();
                return true;
            }
        }
        return false;
    }

    //Method to read commencement Date and convert in date
    public Date getCommencementDate() {
        Date date = null;
        String commencementDate = driver.findElement(businessCommencementDate).getAttribute("value");
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(commencementDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //Method to verify Financial Month is enabled
    public boolean financialMonthEnabled() {
        boolean value = driver.findElement(endOfFinancialMonth).isEnabled();
        if (value == true) {
            return true;
        }
        return false;
    }

    //Method to verify Financial Month is enabled
    public boolean financialDayEnabled() {
        boolean value = driver.findElement(endOfFinancialDay).isEnabled();
        if (value == true) {
            return true;
        }
        return false;
    }
    //Method Click on continue Button.
    public void clickOnContinueButton(){
        driver.findElement(continueButton).click();
    }
    //Method check Gregorian Button is checked or not.
    public boolean checkGregorian(){
        String classAttribute= driver.findElement(checkCalenderTypeGregorian).getAttribute("class");
        if(classAttribute.contains("outline")){
            return true;
        }
      return false;
    }
    public boolean checkHijri(){
        String classAttribute= driver.findElement(checkCalenderTypeHijri).getAttribute("class");
        if(classAttribute.contains("outline")){
            return true;
        }
        return false;
    }

}


