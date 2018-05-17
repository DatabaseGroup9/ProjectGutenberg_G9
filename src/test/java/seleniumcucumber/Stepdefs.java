package seleniumcucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.SystemUtils;
import static org.hamcrest.CoreMatchers.is;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import static seleniumcucumber.View1.driver;

/**
 *
 * @author Cherry Rose Semeña
 */
public class Stepdefs {

    private static final int WAIT_MAX = 4;
    static WebDriver driver;

    public Stepdefs() {
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/seleniumcucumber/chromedriver.exe");
        } else if (SystemUtils.IS_OS_UNIX) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/seleniumcucumber/chromedriver_unic");
        }
        driver = new ChromeDriver();
    }

    @Given("^The city is '(.*)'$")
    public void the_city(String city) throws Throwable {
        driver.get("http://localhost:8080/dbtest/#!/view1");

    }

    @When("^Enter '(.*)' and choose '(.*)'$")
    public void enter_city_and_choose_db(String city, String database) throws Throwable {
        
    }


    @Then("^I should get '(.*)'$")
    public void i_should_get_success(String page) throws Throwable {
       
    }

}
