/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Andreas
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Selenium {

    private static final int WAIT_MAX = 4;
    static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/selenium/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Before
    public void waitLoad() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    //Verify that page is loaded and all expected data are visible
    // 1. Verify that data is loaded, and the DOM is constructed in homepage

    public void test1() throws Exception {
        driver.get("http://localhost:8080/dbtest/#!/home");
        WebElement e = driver.findElement(By.id("headline"));
        String expectedResult = "Here we write a short brief about the project";
        String actualOutout = e.getText();
        Assert.assertThat(expectedResult, is(actualOutout));
    }

    @Test
    //Verify that page is loaded and all expected data are visible
    //2. Verify that data is loaded, and the DOM is constructed in userstory 1
    // Check if the dropdown menu contains correct things
    
    public void test2() throws Exception {
        driver.get("http://localhost:8080/dbtest/#!/view1");
        WebElement e = driver.findElement(By.id("select_db_dropdown"));
        Select dropdown = new Select(e);
        List<WebElement> dropdownOptions = dropdown.getOptions();
        String expectedResult_null = "";
        String expectedResult_stub = "stub";
        String expectedResult_mongodb = "mongodb";
        String expectedResult_neo4j = "neo4j";

        Assert.assertThat(4, is(dropdownOptions.size()));
        Assert.assertThat(expectedResult_null, is(dropdownOptions.get(0).getText()));
        Assert.assertThat(expectedResult_stub, is(dropdownOptions.get(1).getText()));
        Assert.assertThat(expectedResult_mongodb, is(dropdownOptions.get(2).getText()));
        Assert.assertThat(expectedResult_neo4j, is(dropdownOptions.get(3).getText()));
    }

    @Test
    //Verify that page is loaded and all expected data are visible
    //3. Verify that data is loaded, and the DOM is constructed in userstory 1
    // Checks if data is loaded correct with stub data
    public void test3() throws Exception {
        driver.get("http://localhost:8080/dbtest/#!/view1");
        WebElement select_field = driver.findElement(By.id("select_db_dropdown"));
        WebElement cityfield = driver.findElement(By.id("city"));
        WebElement submit = driver.findElement(By.id("submit"));

        Select dropdown = new Select(select_field);
        dropdown.selectByValue("stub");
        String input = "asd";
        cityfield.sendKeys(input);
        submit.click();
        
        TimeUnit.SECONDS.sleep(2);
        WebElement bookstable = driver.findElement(By.id("books"));
        WebElement body = bookstable.findElement(By.tagName("tbody"));
        List<WebElement> rowList = body.findElements(By.tagName("tr"));
        for(WebElement e : rowList){
            List<WebElement> td = e.findElements(By.tagName("td"));
            System.out.println(td.get(0).getText());
            System.out.println(td.get(1).getText());
        }
    }

}
