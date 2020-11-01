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
	@Given("in Homepage and in view stock to graph pop up window and add a stock on the graph to view")
	public void in_Homepage_and_in_view_stock_to_graph_pop_up_window_and_add_a_stock_on_the_graph_to_view() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).click();
	    driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).sendKeys("AAPL");
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button[1]")).click();	
		
	}
	
	@When("I click the remove stock from graph button")
	public void i_click_the_remove_stock_from_graph_button() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		By title2 = By.cssSelector("[id^='highcharts-'] > svg > g.highcharts-legend > g");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		assertEquals(driver.findElement(title2).getText(), "AAPL");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
	}

	@When("I write the wrong ticker gr")
	public void i_write_the_wrong_ticker_gr() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input")).sendKeys("wrong");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
	}

	@Then("I should see the error message for wrong ticker gr")
	public void i_should_see_the_error_message_for_wrong_ticker_gr() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"addStock-form\"]/div[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "Ticker does not exist in the graph");
		
	}
	
	@When("I write the valid ticker and click remove stock button2")
	public void i_write_the_valid_ticker_and_click_remove_stock_button2() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input")).sendKeys("AAPL");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}
	
	@When("I write the valid ticker and click remove stock button")
	public void i_write_the_valid_ticker_and_click_remove_stock_button() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input")).sendKeys("AAPL");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I should see the confirmation window")
	public void i_should_see_the_confirmation_window() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/div/p")).getText(), "Do you want to remove ticker AAPL?");
	}

	@Then("I should see the remove stock button on confirmation page")
	public void i_should_see_the_remove_stock_button_on_confirmation_page() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).getText(), "REMOVE STOCK");

	}
	
	@Then("I should see the cancel button on confirmation page")
	public void i_should_see_the_cancel_button_on_confirmation_page() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[2]")).getText(), "CANCEL");

	}
	
	@Then("I should be able to click the clickable stock button on confirmation page")
	public void i_should_be_able_to_click_the_clickable_stock_button_on_confirmation_page() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")));
		element.click();
	}

	@Then("I should be able to click the clickable cancel button on confirmation page")
	public void i_should_be_able_to_click_the_clickable_cancel_button_on_confirmation_page() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[2]")));
		element.click();
	}
	

	@When("I click the cancel button gms")
	public void i_click_the_cancel_button_gms() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[2]")).click();
	}

	@Then("I should see the stock on the graph gms")
	public void i_should_see_the_stock_on_the_graph_gms() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.cssSelector("[id^='highcharts-'] > svg > g.highcharts-legend > g");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "AAPL");
	}
	
	@When("I click the remove stock button gms")
	public void i_click_the_remove_stock_button() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
	}

	@Then("I should not see the stock on the graph gms")
	public void i_should_not_see_the_stock_on_the_graph_gms() {
		driver.get(ROOT_URL);
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.cssSelector("[id^='highcharts-'] > svg > text.highcharts-title > tspan");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "USC CS310 Stock Management Chart");
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
