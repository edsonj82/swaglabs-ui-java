package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private final WebDriver driver;

    // Step One
    private static final By PAGE_TITLE      = By.cssSelector(".title");
    private static final By FIRST_NAME      = By.id("first-name");
    private static final By LAST_NAME       = By.id("last-name");
    private static final By POSTAL_CODE     = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By CANCEL_BUTTON   = By.id("cancel");
    private static final By ERROR_MESSAGE   = By.cssSelector("[data-test='error']");

    // Step Two (overview)
    private static final By FINISH_BUTTON   = By.id("finish");
    private static final By ITEM_TOTAL      = By.cssSelector(".summary_subtotal_label");
    private static final By TAX_LABEL       = By.cssSelector(".summary_tax_label");
    private static final By TOTAL_LABEL     = By.cssSelector(".summary_total_label");

    // Complete
    private static final By COMPLETE_HEADER = By.cssSelector(".complete-header");
    private static final By COMPLETE_TEXT   = By.cssSelector(".complete-text");
    private static final By BACK_HOME_BUTTON = By.id("back-to-products");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(FIRST_NAME).clear();
        driver.findElement(FIRST_NAME).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(LAST_NAME).clear();
        driver.findElement(LAST_NAME).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(POSTAL_CODE).clear();
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void clickCancel() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void clickFinish() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public String getItemTotal() {
        return driver.findElement(ITEM_TOTAL).getText();
    }

    public String getTaxLabel() {
        return driver.findElement(TAX_LABEL).getText();
    }

    public String getTotalLabel() {
        return driver.findElement(TOTAL_LABEL).getText();
    }

    public String getCompleteHeader() {
        return driver.findElement(COMPLETE_HEADER).getText();
    }

    public String getCompleteText() {
        return driver.findElement(COMPLETE_TEXT).getText();
    }

    public void clickBackHome() {
        driver.findElement(BACK_HOME_BUTTON).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
