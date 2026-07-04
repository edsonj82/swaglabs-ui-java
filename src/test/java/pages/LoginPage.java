package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    private static final By USERNAME_FIELD = By.id("user-name");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON   = By.id("login-button");
    private static final By ERROR_MESSAGE  = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void enterUsername(String username) {
        driver.findElement(USERNAME_FIELD).clear();
        driver.findElement(USERNAME_FIELD).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(PASSWORD_FIELD).clear();
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
