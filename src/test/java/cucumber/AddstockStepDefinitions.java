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
	@Given("in mainpage and logged in")
	public void in_mainpage_and_logged_in() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}
	
	@When("I click add\\/delete bottom")
	public void i_click_add_delete_bottom() {
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).click();
	}

	@Then("I get the pop up window to add\\/delete stock")
	public void i_get_the_pop_up_window_to_add_delete_stock() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"addStock-form\"]/div[1]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "ADD STOCK");
	}
	
	@Given("in mainpage and in add\\/delete pop up window")
	public void in_mainpage_and_in_add_delete_pop_up_window() {
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

	@Then("I can’t successfully add\\/delete stock")
	public void i_can_t_successfully_add_delete_stock() {
		WebElement username = driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]"));
	    assertEquals("rgb(255, 0, 0)",username.getCssValue("border-color"));
	}

	
	@Given("in mainpage and in add\\/delete pop up window2")
	public void in_mainpage_and_in_add_delete_pop_up_window2() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPL");
	    
	}

	@When("I fill in a wrong quantity")
	public void i_fill_in_a_wrong_quantity() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("dd");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I can’t successfully add\\/delete stock2")
	public void i_can_t_successfully_add_delete_stock2() {
		WebElement username = driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]"));
	    assertEquals("rgb(255, 0, 0)",username.getCssValue("border-color"));
	}
	
	@Given("in mainpage and in add\\/delete pop up window3")
	public void in_mainpage_and_in_add_delete_pop_up_window3() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPL");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
	}

	@When("I doesn't fill the start date")
	public void i_doesn_t_fill_the_start_date() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I can’t successfully add\\/delete stock3")
	public void i_can_t_successfully_add_delete_stock3() {
		WebElement username = driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]"));
	    assertEquals("rgb(255, 0, 0)",username.getCssValue("border-color"));
	}
	
	@Given("in mainpage and in add\\/delete pop up window4")
	public void in_mainpage_and_in_add_delete_pop_up_window4() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPL");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
	}

	@When("I fill the invalid start date")
	public void i_fill_the_invalid_start_date() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("5");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I can’t successfully add\\/delete stock4")
	public void i_can_t_successfully_add_delete_stock4() {
		WebElement username = driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]"));
	    assertEquals("rgb(255, 0, 0)",username.getCssValue("border-color"));
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

	@When("I fill in a correct ticker, purchase date, and quantity \\(an integer and > {int})")
	public void i_fill_in_a_correct_ticker_purchase_date_and_quantity_an_integer_and(Integer int1) {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPL");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("11102019");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I successfully add stock, the stock ticker and its price shown at the portfolio list to the right")
	public void i_successfully_add_stock_the_stock_ticker_and_its_price_shown_at_the_portfolio_list_to_the_right() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[1]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "AAPL");
	}
	
	@Given("in mainpage and in add pop up window2")
	public void in_mainpage_and_in_add_pop_up_window2() {
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

	@Then("I successfully add stock, the stock ticker and its price shown at the portfolio list to the right2")
	public void i_successfully_add_stock_the_stock_ticker_and_its_price_shown_at_the_portfolio_list_to_the_right2() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr[2]/td[1]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "AMZN");
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
