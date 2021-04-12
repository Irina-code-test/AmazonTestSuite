package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{
    private static final String AMAZON_CART_PAGE_URL = "https://www.amazon.com/gp/cart/view.html?ref_=nav_cart";
    private static final String AMAZON_CART_PAGE_TITLE = "Amazon.com Shopping Cart";
    private static final By CART_XPATH = By.xpath("//span[@id=\"nav-cart-count\"]");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public CartPage open(){
        openWebPage(AMAZON_CART_PAGE_URL);
        checkPageTitle(AMAZON_CART_PAGE_TITLE);
        return this;
    }

    public CartPage deleteElementFromCart(){
        final By DELETE_BUTTON_XPATH = By.xpath("//input[@value=\"Delete\"]");
        clickOnElement(DELETE_BUTTON_XPATH);
        return this;
    }
    public int getNumberOfElementsInTheCart(){
        String cartCount = getTextFromField(CART_XPATH);
        return Integer.parseInt(cartCount);
    }
}
