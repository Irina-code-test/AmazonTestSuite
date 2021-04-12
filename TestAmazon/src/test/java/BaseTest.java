import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import pages.CartPage;

public abstract class BaseTest {

    protected WebDriver driver   = null;
    protected HomePage  homePage = null;
    protected CartPage  cartPage = null;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "../../WebDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }
}
