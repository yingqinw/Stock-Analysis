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
public class HomepageStepDefinitions {
	private static final String ROOT_URL = "http://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on index page h")
	public void i_am_on_index_page_h() {
		driver.get(ROOT_URL);
	}

	@When("I login and am on the Hompage h")
	public void i_login_and_am_on_the_Hompage_h() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}

	@Then("I should see the title called USC CS310 Stock Portfolio Management")
	public void i_should_see_the_title_called_USC_CS310_Stock_Portfolio_Management() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/a")).getText(), "USC CS310 STOCK PORTFOLIO MANAGEMENT");
	}

	@Given("I am on the Hompage h")
	public void i_am_on_the_Hompage_h() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}

	@When("I click the title")
	public void i_click_the_title() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/a")).click();
	}

	@Then("I should be on the hompage again")
	public void i_should_be_on_the_hompage_again() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/a")).getText(), "USC CS310 STOCK PORTFOLIO MANAGEMENT");
	}

	@Then("I should see the banner color as grey")
	public void i_should_see_the_banner_color_as_grey() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav")).getCssValue("background-color"), "rgba(120, 120, 120, 1)");
	}
	
	@Then("I should see the Tickers text")
	public void i_should_see_the_Tickers_text() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"BTC\"]/table/thead/tr/th[1]")).getText(), "Tickers");
	}

	@Then("I should see the Last Price text")
	public void i_should_see_the_Last_Price_text() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"BTC\"]/table/thead/tr/th[2]")).getText(), "Last Price");
	}

	@Then("I should see the Action text")
	public void i_should_see_the_Action_text() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"BTC\"]/table/thead/tr/th[3]")).getText(), "Action");
	}

	@Then("I should see the add stock to graph button")
	public void i_should_see_the_add_stock_to_graph_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).getText(), "ADD STOCK TO GRAPH");
	}

	@Then("I should see the remove stock from graph button")
	public void i_should_see_the_remove_stock_from_graph_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[2]")).getText(), "REMOVE STOCK FROM GRAPH");
	}

	@Then("I should see the select dates button")
	public void i_should_see_the_select_dates_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[3]")).getText(), "SELECT DATES");
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
