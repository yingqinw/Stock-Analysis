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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import csci310.CreateUserTable;
import csci310.DropUserTable;
import csci310.InitializeUserTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

/**
 * Step definitions for Cucumber tests.
*/
public class GraphAddStockMobileStepDefinitions {
	private static final String ROOT_URL = "https://localhost:3000/";

	public WebDriver driver;
	
	@Before()
	public void before() {
		Map<String, Object> deviceMetrics = new HashMap<>();
		deviceMetrics.put("width", 414);
		deviceMetrics.put("height", 896);
		deviceMetrics.put("pixelRatio", 3.0);
		Map<String, Object> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceMetrics", deviceMetrics);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		driver = new ChromeDriver(chromeOptions);
	
		new DropUserTable();
	}
	
	@Given("I am on index page ga gam")
	public void i_am_on_index_page_ga_gam() {
		driver.get(ROOT_URL);
	}

	@When("I login and am on the Hompage ga gam")
	public void i_login_and_am_on_the_Hompage_ga_gam() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}
	
	@When("I click the view stock button ga gam")
	public void i_click_the_view_stock_button_ga_gam() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).click();
	}

	@Then("I get the pop up window to add stock ga gam")
	public void i_get_the_pop_up_window_to_add_stock_ga_gam() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[1]/div")).getText(), "VIEW STOCK");
	}

	@Then("I should see the ticker block ga gam")
	public void i_should_see_the_ticker_block_ga_gam() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).getAttribute("placeholder"), "Ticker");
	}

	@Then("I should see the view stock button ga gam")
	public void i_should_see_the_view_stock_button_ga_gam() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).getText(), "VIEW STOCK");
	}

	@Then("I should see the view stock button in view stock ga gam")
	public void i_should_see_the_view_stock_button_in_view_stock_ga_gam() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button[1]")).getText(), "VIEW STOCK");
	}
	
	@Then("I should see the cancel button in view stock ga gam")
	public void i_should_see_the_cancel_button_in_view_stock_ga_gam() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button[2]")).getText(), "CANCEL");
	}
	
	@Given("in Homepage and in add stock to graph pop up window gam")
	public void in_Homepage_and_in_add_stock_to_graph_pop_up_window_gam() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).click();
	}

	@When("I write the wrong ticker gam")
	public void i_write_the_wrong_ticker_gam() {
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).sendKeys("wrong");	
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button")).click();
	}

	@Then("I should see the error message for wrong ticker gam")
	public void i_should_see_the_error_message_for_wrong_ticker_gam() {
		WebElement ticker = driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input"));
	    assertEquals("rgb(255, 0, 0)",ticker.getCssValue("border-color"));
	}

	@When("I write the invalid ticker gam")
	public void i_write_the_invalid_ticker_gam() {
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).sendKeys("AKSK");	
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button")).click();
	}

	@Then("I should see the error message for invalid ticker gam")
	public void i_should_see_the_error_message_for_invalid_ticker_gam() {
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
