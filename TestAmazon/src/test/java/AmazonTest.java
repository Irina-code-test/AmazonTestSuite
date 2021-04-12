import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonTest extends BaseTest{

    private static final String SEARCH_TERM = "computers for kids";
    private static final String WRONG_MAIL = "Irenka82@mail.com";

    @Test(priority=1)
    public void amazonComputersForKidsSearch(){
        Assert.assertTrue(homePage.open().searchProductByName(SEARCH_TERM).isNotEmptySearchResult());
    }
    @Test(priority=2)
    public void amazonComputersForKidsAddToCart(){
        Assert.assertEquals(homePage.open().searchProductByName(SEARCH_TERM).addFirstElementToCart().getNumberOfElementsInTheCart(), 1);
    }
    @Test(priority=3)
    public void amazonComputersForKidsDeleteFromCart(){
        Assert.assertEquals(cartPage.open().deleteElementFromCart().getNumberOfElementsInTheCart(), 0);
    }

    @Test(priority=4)
    public void amazonInvalidMailInput(){
        Assert.assertFalse(homePage.open().loginByEmail(WRONG_MAIL));
    }

}
