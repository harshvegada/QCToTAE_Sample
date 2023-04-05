package scripts;

import entity.ProductDetails;
import listeners.TestCaseListeners;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultPage;
import pages.SearchedItemDetailsPage;

@Listeners(TestCaseListeners.class)
public class AutomationTestCases {

    @Test
    public void validateProductTitleAndPrice() {

        HomePage homePage = new HomePage();
        ResultPage resultPage = new ResultPage();
        SearchedItemDetailsPage searchedItemDetailsPage = new SearchedItemDetailsPage();

        homePage.searchForItem("Mobile");
        ProductDetails productDetails = resultPage.capatureFirstItemDetails();
        resultPage.clickOnFirstResultItem();
        ProductDetails searchedItemDetails = searchedItemDetailsPage.capatureItemDetails();

        Assert.assertEquals(productDetails.getProductName(), searchedItemDetails.getProductName(), "Product name mis-match on search page & product details page");
        Assert.assertEquals(productDetails.getProductPrice(), searchedItemDetails.getProductPrice(), "Product price mis-match on search page & product details page");
    }

    @Test(dataProvider = "amazonesearchdata")
    public void validateSearchProductDisplayInTabTitle(String productName) {
        HomePage homePage = new HomePage();
        ResultPage resultPage = new ResultPage();

        homePage.searchForItem(productName);
        Assert.assertTrue(resultPage.validateSearchItemIsInTitle().contains("L"+productName), "Product Title not coming in Tab title");
    }

    @DataProvider(name = "amazonesearchdata")
    public Object[][] getData(){
        Object[][] data = new Object[3][1];
        data[0][0] = "Mobile";
        data[1][0] = "Washingmachine";
        data[2][0] = "Earphone";
        return data;
    }
}
