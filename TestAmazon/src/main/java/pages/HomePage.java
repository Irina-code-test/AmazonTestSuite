package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private static final String AMAZON_HOME_PAGE_URL = "https://www.amazon.com/";
    private static final String AMAZON_HOME_PAGE_TITLE = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
    private static final By SEARCH_FIELD = By.xpath("//input[@id='twotabsearchtextbox']");
    private static final By NO_RESULTS_XPATH = By.xpath("//span[text()=\"No results for \"]");
    private static final By FIRST_LINK_XPATH = By.xpath("(//a[contains(@class, 'a-link-normal a-text-normal')])[1]");
    private static final By CART_XPATH = By.xpath("//span[@id=\"nav-cart-count\"]");
    private static final By ADD_TO_CART_BUTTON_XPATH = By.xpath("//input[@id=\"add-to-cart-button\"]");
    private static final By AMAZON_HOME_PAGE_SING_IN_XPATH = By.xpath("//*[@id=\"nav-link-accountList\"]");
    private static final By EMAIL_OR_MOBILE_INPUT_XPATH = By.xpath("//*[@id=\"ap_email\"]");
    private static final By CONTINUE_BUTTON_XPATH = By.xpath("//input[@id='continue']");
    private static final By ERROR_MESSAGE_XPATH = By.xpath("//*[@id=\"auth-error-message-box\"]");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public HomePage open(){
        openWebPage(AMAZON_HOME_PAGE_URL);
        checkPageTitle(AMAZON_HOME_PAGE_TITLE);
        return this;
    }

    public HomePage searchProductByName(String productName){
        enterTextIntoField(SEARCH_FIELD, productName);
        pressKey(Keys.ENTER);
        return this;
    }

    public boolean isNotEmptySearchResult(){
        try {
            clickOnElement(NO_RESULTS_XPATH);
            return false;
        }catch (IndexOutOfBoundsException err){
            return true;
        }
    }

    public HomePage addFirstElementToCart(){
        clickOnElement(FIRST_LINK_XPATH);
        clickOnElement(ADD_TO_CART_BUTTON_XPATH);
        return this;
    }

    public boolean loginByEmail(String email){
        clickOnElement(AMAZON_HOME_PAGE_SING_IN_XPATH);
        enterTextIntoField(EMAIL_OR_MOBILE_INPUT_XPATH, email);
        clickOnElement(CONTINUE_BUTTON_XPATH);

        try {
            clickOnElement(ERROR_MESSAGE_XPATH);
            return false;
        }catch (org.openqa.selenium.NoSuchElementException err){

        }

        return true;
    }

    public int getNumberOfElementsInTheCart(){
        String cartCount = getTextFromField(CART_XPATH);
        return Integer.parseInt(cartCount);
    }
}
