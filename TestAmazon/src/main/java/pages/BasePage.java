package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.openqa.selenium.devtools.v89.page.Page;

public abstract class BasePage extends Page{

    protected WebDriver driver;

    protected void checkPageTitle(String title){
        Assert.assertEquals(driver.getTitle(), title);
    }

    protected void pressKey(Keys key){
        Actions actions = new Actions(driver);
        actions.sendKeys(key).perform();
    }

    protected void enterTextIntoField(By xpath, String text){
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(xpath),text).perform();
    }

    protected void openWebPage(String url){
        driver.navigate().to(url);
    }

    protected void clickOnElement(By xpath){
        clickOnElement(xpath, 0);
    }
    protected void clickOnElement(By xpath, int position){
        Actions actions = new Actions(driver);
        actions.click(driver.findElements(xpath).get(position)).perform();
    }
    protected String getTextFromField(By xpath){
        return driver.findElement(xpath).getText();
    }
}
