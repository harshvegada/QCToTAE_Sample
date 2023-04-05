package pages;

import base.PredefinedActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pagelocators.HomePageLocators;
import reports.ExtentManager;

import java.util.List;

public class HomePage extends PredefinedActions {

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement amazonSearchBar;

    @FindBy(xpath = "//input[contains(@id,'search-submit-button')]")
    private WebElement searchBarIcon;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void searchForItem(String itemtoBeSearch) {
        setText(amazonSearchBar, itemtoBeSearch);
        clickOnElement(searchBarIcon);
        ExtentManager.log("STEP: User search for " + itemtoBeSearch + " in search bar");
    }

}
