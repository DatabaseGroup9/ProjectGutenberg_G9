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
public class Home {

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

    

}
