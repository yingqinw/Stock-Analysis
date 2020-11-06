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
	private static final String ROOT_URL = "https://localhost:3000/";

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
	
	@When("I click the view stock button ga")
	public void i_click_the_view_stock_button_ga() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).click();
	}

	@Then("I get the pop up window to add stock ga")
	public void i_get_the_pop_up_window_to_add_stock_ga() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[1]/div")).getText(), "VIEW STOCK");
	}

	@Then("I should see the ticker block ga")
	public void i_should_see_the_ticker_block_ga() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).getAttribute("placeholder"), "Ticker");
	}

	@Then("I should see the view stock button ga")
	public void i_should_see_the_view_stock_button_ga() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).getText(), "VIEW STOCK");
	}

	@Then("I should see the view stock button in view stock ga")
	public void i_should_see_the_view_stock_button_in_view_stock_ga() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button[1]")).getText(), "VIEW STOCK");
	}
	
	@Then("I should see the cancel button in view stock ga")
	public void i_should_see_the_cancel_button_in_view_stock_ga() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button[2]")).getText(), "CANCEL");
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
	
	@When("I write the valid stock and click the cancel button")
	public void i_write_the_valid_stock_and_click_the_cancel_button() {
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).sendKeys("AAPL");
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button[2]")).click();
	}

	@Then("I should not see the stock on the graph")
	public void i_should_not_see_the_stock_on_the_graph() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.cssSelector("[id^='highcharts-'] > svg > text.highcharts-title > tspan");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "USC CS310 Stock Management Chart");
	}
	
	@When("I write the valid stock and click view stock button")
	public void i_write_the_valid_stock_and_click_view_stock_button() {
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).sendKeys("AAPL");
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button[1]")).click();
	}
	
	@Then("I should see the stock on the graph")
	public void i_should_see_the_stock_on_the_graph() {
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.cssSelector("[id^='highcharts-'] > svg > g.highcharts-legend > g > g > g.highcharts-legend-item.highcharts-line-series.highcharts-color-2.highcharts-series-2 > text > tspan");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "AAPL");
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
