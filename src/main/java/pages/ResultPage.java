package pages;

import base.PredefinedActions;
import entity.ProductDetails;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reports.ExtentManager;

public class ResultPage extends PredefinedActions {


    @FindBy(xpath = "//span[text()='Results']//ancestor::div[contains(@class,'result-item')]/following-sibling::div//img")
    private WebElement firstResultItemImage;

    @FindBy(xpath = "(//span[text()='Results']//ancestor::div[contains(@class,'result-item')]/following-sibling::div//h2//span)[1]")
    private WebElement firstItemTitle;

    @FindBy(xpath = "(//span[text()='Results']//ancestor::div[contains(@class,'result-item')]/following-sibling::div//span[@class='a-price'])[1]")
    private WebElement firstItemPrice;

    public ResultPage(){
        PageFactory.initElements(driver, this);
    }

    public String validateSearchItemIsInTitle() {
        ExtentManager.log("STEP: Validating search item title");
        return getTitleOfPage();
    }

    public void clickOnFirstResultItem(){
        clickOnElement(firstResultItemImage);
        switchToWindow();
        ExtentManager.log("STEP: clicked on first item searched");
    }

    public ProductDetails capatureFirstItemDetails(){
        ProductDetails itemDetails = new ProductDetails();
        itemDetails.setProductName(getTextOfElement(firstItemTitle));
        itemDetails.setProductPrice(getTextOfElement(firstItemPrice).replace("₹",""));
        ExtentManager.log("STEP: captured first item details");
        return itemDetails;
    }


}
