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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Step definitions for Cucumber tests.
*/
public class ToggleStepDefinitions {
	private static final String ROOT_URL = "https://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on the index page ts")
	public void i_am_on_the_index_page_ts() {
		driver.get(ROOT_URL);
	}

	@When("I login and am on the hompage ts")
	public void i_login_and_am_on_the_hompage_ts() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}
	@When("I add a stock for portfolio")
	public void i_add_a_stock_for_portfolio() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[1]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPL");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("1");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("09202020");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[4]")).sendKeys("10202020");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I should see the option tab")
	public void i_should_see_the_option_tab() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"BTC\"]/table/thead/tr/th[4]")).getText(), "Option");
	}
	
	@Then("I should be able to click the clickable toggle button")
	public void i_should_be_able_to_click_the_clickable_toggle_button() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[4]/div")));
		element.click();
	}

	@Then("I should see the select all button")
	public void i_should_see_the_select_all_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/button[1]")).getText(), "SELECT ALL");
	}

	@Then("I should be able to click the clickable select all button")
	public void i_should_be_able_to_click_the_clickable_select_all_button() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/button[1]")));
		element.click();
	}

	@Then("I should see the unselect all button")
	public void i_should_see_the_unselect_all_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/button[2]")).getText(), "UNSELECT ALL");

	}

	@Then("I should be able to click the clickable unselect all button")
	public void i_should_be_able_to_click_the_clickable_unselect_all_button() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/button[2]")));
		element.click();
	}
	
	@Given("I am on the hompage")
	public void i_am_on_the_hompage() {
		driver.get(ROOT_URL);
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}

	@When("I click the toggle")
	public void i_click_the_toggle() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[4]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
	}

	@Then("there should be no graph for the stock")
	public void there_should_be_no_graph_for_the_stock() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		By title = By.cssSelector("[id^='highcharts-'] > svg > g:nth-child(24) > text");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "0");
	}
	
	@When("I add a stock for portfolio2")
	public void i_add_a_stock_for_portfolio2() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[1]/button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("IBM");	
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("1");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("09202020");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[4]")).sendKeys("10202020");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@When("I click the toggle for the first stock")
	public void i_click_the_toggle_for_the_first_stock() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr[1]/td[4]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
	}

	@Then("there should be the graph for only one stock")
	public void there_should_be_the_graph_for_only_one_stock() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By title = By.cssSelector("[id^='highcharts-'] > svg > g:nth-child(24) > text:nth-child(3)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "200");
	}

	@When("I click the unselect all button")
	public void i_click_the_unselect_all_button() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/button[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
		try {
			Thread.sleep(8*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(title).click();
	}

	@When("I click the select all button")
	public void i_click_the_select_all_button() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/button[1]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
		try {
			Thread.sleep(8*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(title).click();
	}

	@Then("there should the graph for portfolio")
	public void there_should_the_graph_for_portfolio() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By title = By.cssSelector("[id^='highcharts-'] > svg > g:nth-child(24) > text:nth-child(3)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "100");
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
