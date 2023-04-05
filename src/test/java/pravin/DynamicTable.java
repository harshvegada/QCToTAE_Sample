package pravin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class DynamicTable {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://datatables.net/");
        driver.manage().window().maximize();

        List<WebElement> totalColums = driver.findElements(By.xpath("//table[@id='example']/thead/tr/th[@class!='dt-body-right sorting dtr-hidden']"));
        List<WebElement> totalRows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));

        List<String> tableHeaderCell = new ArrayList<>();
        totalColums.forEach(i -> tableHeaderCell.add(i.getText()));


        int ageIndex = tableHeaderCell.indexOf("Age");

        for (int i = 1; i <= totalRows.size(); i++) {
            for (int j = 1; j <= totalColums.size(); j++) {
                if (driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[" + j + "]")).getText().equals("Software Engineer")) {
                    System.out.println(driver.findElement(By.xpath("//table[@id='example']/tbody/tr/td[" + ageIndex + "]")).getText());
                    Select select = new Select(driver.findElement(By.xpath("//table[@id='example']/tbody/tr/td[" + ageIndex + "]")));
                    select.selectByVisibleText("65");
                }
            }
        }
    }

    @Test
    public void m1(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(4,2);

        softAssert.assertEquals(4,5);

        System.out.println("Hi...");
        softAssert.assertAll();
    }

}
