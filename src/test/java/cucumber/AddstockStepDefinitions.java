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
public class AddstockStepDefinitions {
	private static final String ROOT_URL = "http://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on index page a")
	public void i_am_on_index_page_a() {
		driver.get(ROOT_URL);
	}
	
	@When("I login and am on the Hompage a")
	public void i_login_and_am_on_the_Hompage_a() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}
	
	@Then("I should see the add stock button")
	public void i_should_see_the_add_stock_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).getText(), "ADD STOCK");
	}
	
	@Given("in mainpage and logged in a")
	public void in_mainpage_and_logged_in_a() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}
	
	@When("I click add stock botton")
	public void i_click_add_stock_botton() {
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).click();
	}

	@Then("I get the pop up window to add stock")
	public void i_get_the_pop_up_window_to_add_stock() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"addStock-form\"]/div[1]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "ADD STOCK");
	}
	
	@Then("I should see the ticker block")
	public void i_should_see_the_ticker_block() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).getAttribute("placeholder"), "Ticker");
	}
	
	@Then("I should see the quantity block")
	public void i_should_see_the_quantity_block() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).getAttribute("placeholder"), "Quantity");
	}
	
	@Then("I should see the start date block")
	public void i_should_see_the_start_date_block() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).getAttribute("placeholder"), "start date");
	}
	
	@Then("I should see the end date block")
	public void i_should_see_the_end_date_block() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[4]")).getAttribute("placeholder"), "end date");
	}
	
	@Then("I should see the add stock button on the bottom")
	public void i_should_see_the_add_stock_button_on_the_bottom() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).getText(), "ADD STOCK");
	}
	
	@Given("in mainpage and in add stock pop up window")
	public void in_mainpage_and_in_add_stock_pop_up_window() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).click();
	}

	@When("I fill in a wrong ticker")
	public void i_fill_in_a_wrong_ticker() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("wrong");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}
	
	@Then("I see the error message for wrong ticker")
	public void i_see_the_error_message_for_wrong_ticker() {
		WebElement username = driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]"));
	    assertEquals("rgb(255, 0, 0)",username.getCssValue("border-color"));
	}

	@When("I fill in a wrong quantity")
	public void i_fill_in_a_wrong_quantity() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPL");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("dd");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}
	
	@Then("I see the error message for invalid quantity")
	public void i_see_the_error_message_for_invalid_quantity() {
		WebElement username = driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]"));
	    assertEquals("rgb(255, 0, 0)",username.getCssValue("border-color"));
	}

	@When("I doesn't fill the start date")
	public void i_doesn_t_fill_the_start_date() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPL");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}
	
	@Then("I see the error message for start date")
	public void i_see_the_error_message_for_start_date() {
		WebElement username = driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]"));
	    assertEquals("rgb(255, 0, 0)",username.getCssValue("border-color"));
	}

	@When("I fill the invalid start date")
	public void i_fill_the_invalid_start_date() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPL");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("5");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I see the error message for invalid start date")
	public void i_see_the_error_message_for_invalid_start_date() {
		WebElement username = driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]"));
	    assertEquals("rgb(255, 0, 0)",username.getCssValue("border-color"));
	}
	
	@When("I fill the invalid ticker")
	public void i_fill_the_invalid_ticker() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPA");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("12102019");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[4]")).sendKeys("05102020");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I see the error message for invalid ticker")
	public void i_see_the_error_message_for_invalid_ticker() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"addStock-form\"]/div[3]/p");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "Invalid ticker!");
	}
	
	@Given("in mainpage and in add pop up window")
	public void in_mainpage_and_in_add_pop_up_window() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).click();
	}
	
	@When("I fill in a correct ticker, purchase date, end date, and quantity \\(an integer and > {int})")
	public void i_fill_in_a_correct_ticker_purchase_date_end_date_and_quantity_an_integer_and(Integer int1) {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AMZN");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("12102019");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[4]")).sendKeys("05102020");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
		
	}

	@Then("I successfully add stock, the stock ticker and its price shown at the portfolio list to the right")
	public void i_successfully_add_stock_the_stock_ticker_and_its_price_shown_at_the_portfolio_list_to_the_right2() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[1]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "AMZN");
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		By title2 = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[3]");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title2).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
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
