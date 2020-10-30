package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import csci310.CreateUserTable;
import csci310.DropUserTable;
import csci310.InitializeUserTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Step definitions for Cucumber tests.
*/
public class GraphRemoveStockStepDefinitions {
	private static final String ROOT_URL = "https://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on index page gr")
	public void i_am_on_index_page_gr() {
		driver.get(ROOT_URL);
	}

	@When("I login and am on the Hompage gr")
	public void i_login_and_am_on_the_Hompage_gr() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}

	@When("I click remove stock to graph button")
	public void i_click_remove_stock_to_graph_button() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[2]")).click();
	}
	
	@Then("I should see the remove stock from graph button gr")
	public void i_should_see_the_remove_stock_from_graph_button_gr() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[2]")).getText(), "REMOVE STOCK FROM GRAPH");
	}

	@Then("I get the pop up window for remove stock gr")
	public void i_get_the_pop_up_window_for_remove_stock_gr() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/div")).getText(), "REMOVE STOCK");
	}

	@Then("I should see the ticker block gr")
	public void i_should_see_the_ticker_block_gr() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input")).getAttribute("placeholder"), "Ticker");
	}

	@Then("I should see the remove stock button to graph button")
	public void i_should_see_the_remove_stock_button_to_graph_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).getText(), "REMOVE STOCK");
	}

	@Given("in Homepage and in remove stock to graph pop up window")
	public void in_Homepage_and_in_remove_stock_to_graph_pop_up_window() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[2]")).click();
	}

	@When("I write the wrong ticker gr")
	public void i_write_the_wrong_ticker_gr() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input")).sendKeys("wrong");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I should see the error message for wrong ticker gr")
	public void i_should_see_the_error_message_for_wrong_ticker_gr() {
		WebElement ticker = driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input"));
	    assertEquals("rgb(255, 0, 0)",ticker.getCssValue("border-color"));
	}

	@After()
	public void after() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
