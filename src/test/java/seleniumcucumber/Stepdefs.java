package seleniumcucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.is;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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

    static WebDriver driver;


    @Given("^The city is Anderson$")
    public void the_city_is_Anderson() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "src/test/java/seleniumcucumber/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/dbtest/#!/view1");
        
    }
//
//    @When("^Enter the city Anderson and stub$")
//    public void enter_the_city_Anderson_and_stub(String city, String database) throws Throwable {
//        
//        WebElement select_field = driver.findElement(By.id("select_db_dropdown"));
//        WebElement cityfield = driver.findElement(By.id("city"));
//        WebElement submit = driver.findElement(By.id("submit"));
//        Select dropdown = new Select(select_field);
//        dropdown.selectByValue(database);
//        String input = city;
//        cityfield.sendKeys(input);
//        submit.click();
//        TimeUnit.SECONDS.sleep(2);
//    }
//
//    @Then("^I should get success$")
//    public void i_should_get_success() throws Throwable {
//        int expectedSize = 7;
//        
//        WebElement bookstable = driver.findElement(By.id("books"));
//        WebElement body = bookstable.findElement(By.tagName("tbody"));
//        List<WebElement> rowList = body.findElements(By.tagName("tr"));
//        Assert.assertThat(7, is(rowList.size()));
//    }
//
//    @Given("^The city is $")
//    public void the_city_is() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^Enter the city  and stub$")
//    public void enter_the_city_and_stub() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^I should get Invalid Input$")
//    public void i_should_get_Invalid_Input() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Given("^The city is Lyngby$")
//    public void the_city_is_Lyngby() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^Enter the city Lyngby and stub$")
//    public void enter_the_city_Lyngby_and_stub() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^I should get No Book Found$")
//    public void i_should_get_No_Book_Found() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^Enter the city Anderson and mongodb$")
//    public void enter_the_city_Anderson_and_mongodb() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^Enter the city  and mongodb$")
//    public void enter_the_city_and_mongodb() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^Enter the city Lyngby and mongodb$")
//    public void enter_the_city_Lyngby_and_mongodb() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^Enter the city Anderson and neo(\\d+)j$")
//    public void enter_the_city_Anderson_and_neo_j(int arg1) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^Enter the city  and neo(\\d+)j$")
//    public void enter_the_city_and_neo_j(int arg1) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^Enter the city Lyngby and neo(\\d+)j$")
//    public void enter_the_city_Lyngby_and_neo_j(int arg1) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
}
