package pages;

import base.PredefinedActions;
import entity.ProductDetails;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reports.ExtentManager;

public class SearchedItemDetailsPage extends PredefinedActions {

    @FindBy(css = "span#productTitle")
    private WebElement itemTitle;

    @FindBy(css = "span[class*='priceToPay'] span.a-price-whole")
    private WebElement itemPrice;

    @FindBy(css = "div#ppd div#averageCustomerReviews span#acrPopover i span")
    private WebElement itemRating;

    public SearchedItemDetailsPage(){
        PageFactory.initElements(driver, this);
    }


    public ProductDetails capatureItemDetails(){
        ProductDetails itemDetails = new ProductDetails();
        itemDetails.setProductPrice(getTextOfElement(itemPrice));
        itemDetails.setProductName(getTextOfElement(itemTitle));
        ExtentManager.log("STEP: captured first item details");
        return itemDetails;
    }

}
