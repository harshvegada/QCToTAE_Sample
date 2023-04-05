package base;

import constants.Filepath;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ExtentManager;
import utility.PropertyUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PredefinedActions {

    protected static WebDriver driver;
    private static WebDriverWait wait;

    protected void startBrowser(String browserName, String env) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser case not matched..." + browserName);
        }

        driver.manage().window().maximize();
        driver.get(getApplicationURL(env));
        wait = new WebDriverWait(driver, 20);
        ExtentManager.log("STEP: Browser launched");
    }

    private String getApplicationURL(String env) {
        return PropertyUtils.getValue(Filepath.CONFIGPATH, env.toLowerCase());
    }

    protected void closeBrower() {
        driver.quit();
    }

    protected void setText(WebElement element, String textToSet) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(textToSet);
        ExtentManager.log("STEP: value " + textToSet + " set on locator " + element);
    }

    protected void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element)).click();
        ExtentManager.log("STEP: clicked on locator " + element);
    }

    protected void scrollTillElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        ExtentManager.log("STEP: scroll to element " + element);
    }

    protected List<String> getTextOfWebElement(List<WebElement> listOfWebElement) {
        List<String> list = new ArrayList<>();
        for (WebElement ele : listOfWebElement) {
            list.add(ele.getText());
        }
        ExtentManager.log("STEP: getting list of element : " + list);
        return list;
    }

    protected void mouseHoveOnElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        ExtentManager.log("STEP: mouse hover to element " + element);
    }

    protected void waitForElementToVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected String getTextOfElement(WebElement element) {
        ExtentManager.log("STEP: getting text of element : " + wait.until(ExpectedConditions.visibilityOf(element)).getText());
        return element.getText();
    }

    protected boolean isElementIsDisplayed(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    protected boolean isElementIsDisplayed(String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    protected String getAttributeValue(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute("value");
    }

    protected void numberOfWindows() {
        Set<String> multi = driver.getWindowHandles();
        wait.until(ExpectedConditions.numberOfWindowsToBe(18));
    }

    protected void refreshPage() {
        ExtentManager.log("STEP: page re-freshed");
        driver.navigate().refresh();
    }

    protected String getTitleOfPage() {
        ExtentManager.log("STEP: title of page : " + driver.getTitle());
        return driver.getTitle();
    }

    protected void switchToWindow() {
        Set<String> multipleWindows = driver.getWindowHandles();
        String mainWindow = driver.getWindowHandle();
        Iterator<String> itr = multipleWindows.iterator();

        while (itr.hasNext()) {
            String currectWindow = itr.next();
            if (!mainWindow.equalsIgnoreCase(currectWindow)) {
                driver.switchTo().window(currectWindow);
                ExtentManager.log("STEP: switched to target window");
            }
        }
    }

    public static String capatureScreenShot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        ExtentManager.log("STEP: screenshot captured");
        return ts.getScreenshotAs(OutputType.BASE64);
    }
}
