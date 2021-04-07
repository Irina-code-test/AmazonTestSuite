import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class AmazonTest {
    static final String AMAZON_HOME_PAGE_URL = "https://www.amazon.com/";
    static final String AMAZON_HOME_PAGE_TITLE = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
    static final String SEARCH_TERM = "computers for kids";

    static final String SEARCH_FIELD_XPATH = "//input[@id='twotabsearchtextbox']";
    static final String SEARCH_BUTTON_XPATH = "//input[@id='nav-search-submit-button']";
    static final String FIRST_LINK_XPATH = "(//a[contains(@class, 'a-link-normal a-text-normal')])[1]";
    static final String CART_XPATH = "//span[@id=\"nav-cart-count\"]";
    static final String ADD_TO_CART_BUTTON_XPATH = "//input[@id=\"add-to-cart-button\"]";
    static final String DELETE_BUTTON_XPATH = "//input[@value=\"Delete\"]";
    static final String AMAZON_HOME_PAGE_SING_IN_XPATH = "//*[@id=\"nav-link-accountList\"]";
    static final String EMAIL_OR_MOBILE_INPUT_XPATH = "//*[@id=\"ap_email\"]";
    static final String CONTINUE_BUTTON_XPATH = "//input[@id='continue']";
    static final String ERROR_MESSAGE_XPATH = "//*[@id=\"auth-error-message-box\"]";
    static final String WRONG_MAIL = "Irenka82@mail.com";
    static final String NO_RESULTS_XPATH = "//span[text()=\"No results for \"]";

    private WebDriver driver;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "../WebDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }



@Test(expectedExceptions = org.openqa.selenium.NoSuchElementException.class,
        expectedExceptionsMessageRegExp = ".*No results for.*")
public void amazonComputersForKidsSearch(){
    driver.navigate().to(AMAZON_HOME_PAGE_URL);
    String pageTitle = driver.getTitle();
    Assert.assertEquals(pageTitle, AMAZON_HOME_PAGE_TITLE );
    WebElement searchElement = driver.findElement(By.xpath(SEARCH_FIELD_XPATH));
    searchElement.click();
    searchElement.sendKeys(SEARCH_TERM);
    driver.findElement(By.xpath(SEARCH_BUTTON_XPATH)).click();
    driver.findElement(By.xpath(NO_RESULTS_XPATH));
}
@Test
public void amazonComputersForKidsAddToCart(){
        driver.navigate().to(AMAZON_HOME_PAGE_URL);
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, AMAZON_HOME_PAGE_TITLE );
        WebElement searchElement = driver.findElement(By.xpath(SEARCH_FIELD_XPATH));
        searchElement.click();
        searchElement.sendKeys(SEARCH_TERM);
        driver.findElement(By.xpath(SEARCH_BUTTON_XPATH)).click();
        driver.findElement(By.xpath(FIRST_LINK_XPATH)).click();
        driver.findElement(By.xpath(ADD_TO_CART_BUTTON_XPATH)).click();
        WebElement cartElement = driver.findElement(By.xpath(CART_XPATH));
        String cartCount = cartElement.getText();
        Assert.assertEquals(cartCount, "1");
    }
@Test
public void amazonComputersForKidsDeleteFromCart(){
        driver.navigate().to(AMAZON_HOME_PAGE_URL);
        WebElement cartElement = driver.findElement(By.xpath(CART_XPATH));
        cartElement.click();
        driver.findElement(By.xpath(DELETE_BUTTON_XPATH)).click();
        cartElement = driver.findElement(By.xpath(CART_XPATH));
        String cartCount = cartElement.getText();
        Assert.assertEquals(cartCount, "0");
    }
@Test
public void amazonInvalidMailInput(){
        driver.navigate().to(AMAZON_HOME_PAGE_URL);
        driver.findElement(By.xpath(AMAZON_HOME_PAGE_SING_IN_XPATH)).click();
        driver.findElement(By.xpath(EMAIL_OR_MOBILE_INPUT_XPATH)).sendKeys(WRONG_MAIL);
        driver.findElement(By.xpath(CONTINUE_BUTTON_XPATH)).click();
        WebElement errorMessage = null;
        try {
            errorMessage = driver.findElement(By.xpath(ERROR_MESSAGE_XPATH));
        }
        catch(Exception err){

        }
        Assert.assertNotNull(errorMessage);
    }
@AfterTest
public void cleanUp(){
    driver.quit();
}
}
