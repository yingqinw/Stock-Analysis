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
public class GraphAddStockStepDefinitions {
	private static final String ROOT_URL = "http://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on index page ga")
	public void i_am_on_index_page_ga() {
		driver.get(ROOT_URL);
	}

	@When("I login and am on the Hompage ga")
	public void i_login_and_am_on_the_Hompage_ga() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}
	
	@When("I click add stock to graph button")
	public void i_click_add_stock_to_graph_button() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).click();
	}

	@Then("I get the pop up window to add stock ga")
	public void i_get_the_pop_up_window_to_add_stock_ga() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[1]/div")).getText(), "ADD STOCK");
	}

	@Then("I should see the ticker block ga")
	public void i_should_see_the_ticker_block_ga() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).getAttribute("placeholder"), "Ticker");
	}

	@Then("I should see the add stock button to graph button")
	public void i_should_see_the_add_stock_button_to_graph_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button")).getText(), "ADD STOCK TO GRAPH");
	}

	@Then("I should see the title called USC CS310 Stock Portfolio Management")
	public void i_should_see_the_title_called_USC_CS310_Stock_Portfolio_Management() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/a")).getText(), "USC CS310 STOCK PORTFOLIO MANAGEMENT");
	}

	@Then("I should see the add stock to graph button")
	public void i_should_see_the_add_stock_to_graph_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).getText(), "ADD STOCK TO GRAPH");
	}
	
	@Given("in Homepage and in add stock to graph pop up window")
	public void in_Homepage_and_in_add_stock_to_graph_pop_up_window() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).click();
	}

	@When("I write the wrong ticker")
	public void i_write_the_wrong_ticker() {
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).sendKeys("wrong");	
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button")).click();
	}

	@Then("I should see the error message for wrong ticker")
	public void i_should_see_the_error_message_for_wrong_ticker() {
		WebElement ticker = driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input"));
	    assertEquals("rgb(255, 0, 0)",ticker.getCssValue("border-color"));
	}

	@When("I write the invalid ticker")
	public void i_write_the_invalid_ticker() {
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).sendKeys("AKSK");	
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button")).click();
	}

	@Then("I should see the error message for invalid ticker")
	public void i_should_see_the_error_message_for_invalid_ticker() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"addStockToGraph-form\"]/div[3]/p");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "Invalid ticker!");
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
