package IndividualRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class IndividualRegisterPage {

    WebDriver driver;

    //Individuals button
    private By individualsButton = By.xpath("(//mat-card[@class = 'cardHover mat-card mat-focus-indicator'])[3]");

    //ID Type dropdown options
    private By dropDownOptions = By.tagName("mat-option");

    //Labels
    private By label = By.className("registerLabelCss");

    //Field Error element
    private By error = By.tagName("p");

    //Page Error element
    private By pageError = By.xpath("//p[contains(@class, 'notifier__notification-message')]");

    //ID Type selection
    private By idType = By.name("type1");
    private By idTypeOption = By.tagName("mat-option");

    //ID Number
    private By idNumberField = By.xpath("//input[@name='num1'] | //input[@name='IdNumber']");

    //Date of birth
    private By dateOfBirthField = By.name("sd");

    //Continue
    private By continueButton = By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base btnCss']");

    //Close button for page notification messages
    private By pageErrorCloseButton = By.xpath("//button[contains(@class,'notifier__notification-button')]");
    private final By backButton = By.xpath("//i[contains(@class, 'arrow-left')]");


    public IndividualRegisterPage(WebDriver driver) {
        this.driver = driver;

    }

    public void clickIndividualsButton() {

        driver.findElement(individualsButton).click();

    }


    public boolean selectFromList(By e, String value) {
        driver.findElement(e).click();
        List<WebElement> allOptions = driver.findElements(dropDownOptions);
        for (int i = 0; i <= allOptions.size() - 1; i++) {
            if (allOptions.get(i).getText().equals(value)) {
                allOptions.get(i).click();
                return true;
            }
        }
        return false;
    }

    public void fillData(Map<String, String> data) {

        if (data.get("id_type") != null) {
            selectFromList(idType, data.get("id_type"));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String selectAll = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(idNumberField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("id_number") != null) {
            driver.findElement(idNumberField).sendKeys(data.get("id_number"));
        }

        driver.findElement(dateOfBirthField).sendKeys(selectAll + Keys.BACK_SPACE);
        if (data.get("date_of_birth") != null) {
            driver.findElement(dateOfBirthField).sendKeys(data.get("date_of_birth"));
        }


    }

    public String getFieldLevelError(Map<String, String> errorData) {
        List<WebElement> fieldErrors = switch (errorData.get("error_element")) {
            case "id_type" -> driver.findElements(with(error).below(idType));
            case "id_number" -> driver.findElements(with(error).below(idNumberField));
            case "date_of_birth" -> driver.findElements(with(error).below(dateOfBirthField));
            default -> new ArrayList<>();
        };

        if (fieldErrors.size() > 0) {
            return fieldErrors.get(0).getText();
        }
        return null;
    }

    public String getPageLevelError() {
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

    public String getFieldsLabels(String key){
        WebElement element_label = switch(key){
            case "ID_type" -> driver.findElement(with(label).above(idType));
            case "ID_number" -> driver.findElement(with(label).above(idNumberField));
            case "DOB" -> driver.findElement(with(label).above(dateOfBirthField));
            default -> null;
        };
        // return label of the first element if any
        if(!element_label.getText().isEmpty()) {
            return element_label.getText();
        }
        return null;
    }



    public void clickContinueButton() {
        driver.findElement(continueButton).click();

    }
    public void clickBackButton(){
        driver.findElement(backButton).click();
    }

    public boolean checkPage(){
        return driver.findElement(idNumberField).isDisplayed();
    }

}


