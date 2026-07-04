package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private final WebDriver driver;

    private static final By PAGE_TITLE       = By.cssSelector(".title");
    private static final By CART_ITEMS       = By.cssSelector(".cart_item");
    private static final By ITEM_NAMES       = By.cssSelector(".inventory_item_name");
    private static final By ITEM_QUANTITIES  = By.cssSelector(".cart_quantity");
    private static final By CONTINUE_SHOPPING = By.id("continue-shopping");
    private static final By CHECKOUT_BUTTON  = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    public int getCartItemCount() {
        return driver.findElements(CART_ITEMS).size();
    }

    public List<WebElement> getItemNames() {
        return driver.findElements(ITEM_NAMES);
    }

    public boolean isItemInCart(String itemName) {
        return driver.findElements(ITEM_NAMES).stream()
            .anyMatch(el -> el.getText().equalsIgnoreCase(itemName));
    }

    public void removeItemFromCart(String itemName) {
        List<WebElement> items = driver.findElements(CART_ITEMS);
        for (WebElement item : items) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            if (name.equalsIgnoreCase(itemName)) {
                item.findElement(By.cssSelector(".cart_button")).click();
                return;
            }
        }
    }

    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING).click();
    }

    public void clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
