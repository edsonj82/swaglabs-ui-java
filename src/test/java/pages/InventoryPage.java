package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {

    private final WebDriver driver;

    private static final By PAGE_TITLE        = By.cssSelector(".title");
    private static final By INVENTORY_ITEMS   = By.cssSelector(".inventory_item");
    private static final By ITEM_NAMES        = By.cssSelector(".inventory_item_name");
    private static final By ITEM_PRICES       = By.cssSelector(".inventory_item_price");
    private static final By SORT_DROPDOWN     = By.cssSelector("[data-test='product_sort_container']");
    private static final By CART_ICON         = By.cssSelector(".shopping_cart_link");
    private static final By CART_BADGE        = By.cssSelector(".shopping_cart_badge");
    private static final By BURGER_MENU       = By.id("react-burger-menu-btn");
    private static final By LOGOUT_LINK       = By.id("logout_sidebar_link");
    private static final By ALL_ITEMS_LINK    = By.id("inventory_sidebar_link");
    private static final By ABOUT_LINK        = By.id("about_sidebar_link");
    private static final By RESET_LINK        = By.id("reset_sidebar_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    public int getInventoryItemCount() {
        return driver.findElements(INVENTORY_ITEMS).size();
    }

    public List<WebElement> getItemNames() {
        return driver.findElements(ITEM_NAMES);
    }

    public List<WebElement> getItemPrices() {
        return driver.findElements(ITEM_PRICES);
    }

    public void addFirstItemToCart() {
        List<WebElement> addButtons = driver.findElements(By.cssSelector(".btn_inventory"));
        if (!addButtons.isEmpty()) {
            addButtons.get(0).click();
        }
    }

    public void addItemToCartByName(String itemName) {
        List<WebElement> items = driver.findElements(INVENTORY_ITEMS);
        for (WebElement item : items) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            if (name.equalsIgnoreCase(itemName)) {
                item.findElement(By.cssSelector(".btn_inventory")).click();
                return;
            }
        }
    }

    public void removeItemFromCartByName(String itemName) {
        List<WebElement> items = driver.findElements(INVENTORY_ITEMS);
        for (WebElement item : items) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            if (name.equalsIgnoreCase(itemName)) {
                item.findElement(By.cssSelector(".btn_secondary")).click();
                return;
            }
        }
    }

    public void selectSort(String option) {
        org.openqa.selenium.support.ui.Select select =
            new org.openqa.selenium.support.ui.Select(driver.findElement(SORT_DROPDOWN));
        select.selectByVisibleText(option);
    }

    public void goToCart() {
        driver.findElement(CART_ICON).click();
    }

    public int getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(CART_BADGE);
        if (badges.isEmpty()) return 0;
        return Integer.parseInt(badges.get(0).getText());
    }

    public void openBurgerMenu() {
        driver.findElement(BURGER_MENU).click();
    }

    public void clickLogout() {
        driver.findElement(LOGOUT_LINK).click();
    }

    public void clickAllItems() {
        driver.findElement(ALL_ITEMS_LINK).click();
    }

    public void clickAbout() {
        driver.findElement(ABOUT_LINK).click();
    }

    public void clickResetAppState() {
        driver.findElement(RESET_LINK).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void openItemDetail(String itemName) {
        List<WebElement> items = driver.findElements(ITEM_NAMES);
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(itemName)) {
                item.click();
                return;
            }
        }
    }
}
