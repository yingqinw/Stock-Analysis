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

import java.text.NumberFormat;

/**
 * Step definitions for Cucumber tests.
*/
public class PortfolioNumberStepDefinitions {
	private static final String ROOT_URL = "https://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on index page a pn")
	public void i_am_on_index_page_a_pn() {
		driver.get(ROOT_URL);
	}
	
	@When("I login and am on the Hompage a pn")
	public void i_login_and_am_on_the_Hompage_a_pn() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}
	
	@Given("in mainpage and logged in a pn")
	public void in_mainpage_and_logged_in_a_pn() {
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
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[2]/button")).click();
	}
	
	@Then("I should see the portflio number {double}")
	public void i_should_see_the_portflio_number(Double double1) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[1]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertTrue(driver.findElement(title).getText().contains("$0.00"));
	}

	@Then("I should see the Increase letter")
	public void i_should_see_the_Increase_letter() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[1]/div/div/div[1]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "Increase");
	}

	@When("I click add stock botton pn")
	public void i_click_add_stock_botton_pn() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[2]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AMZN");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("12102019");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[4]")).sendKeys("05102020");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I should see the portflio value number changed for the stock")
	public void i_should_see_the_portflio_value_number_changed_for_the_stock() {
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		By title2 = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[1]/div");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		double value = Double.parseDouble(driver.findElement(title).getText());
		round(value, 2);
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		assertTrue(driver.findElement(title2).getText().contains(defaultFormat.format(value)));
		
	}

	@When("I click add another stock pn")
	public void i_click_add_another_stock_pn() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[2]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPL");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("12102019");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[4]")).sendKeys("05102020");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I should see the new portflio value number changed for the stocks")
	public void i_should_see_the_new_portflio_value_number_changed_for_the_stocks() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr[1]/td[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		By title2 = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr[2]/td[2]");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		WebDriverWait wait3 = new WebDriverWait(driver, 10);
		By title3 = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[1]/div");
		wait3.until(ExpectedConditions.visibilityOfElementLocated(title3));
		
		double value = Double.parseDouble(driver.findElement(title).getText());
		round(value, 2);
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		double value2 = Double.parseDouble(driver.findElement(title2).getText());
		round(value2, 2);
		double value3 = value + value2;
		assertTrue(driver.findElement(title3).getText().contains(defaultFormat.format(value3)));
		
	}

	@When("I click the toggle for the first stock pn")
	public void i_click_the_toggle_for_the_first_stock_pn() {
		try {
			Thread.sleep(7*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr[1]/td[4]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
	}

	@Then("I should see the updated porfolio value for the second stock")
	public void i_should_see_the_updated_porfolio_value_for_the_second_stock() {
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr[2]/td[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		By title2 = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[1]/div");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		double value = Double.parseDouble(driver.findElement(title).getText());
		round(value, 2);
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		assertTrue(driver.findElement(title2).getText().contains(defaultFormat.format(value)));
	}
	
	@Then("I should see either red or green color depending on the portfolio value")
	public void i_should_see_either_red_or_green_color_depending_on_the_portfolio_value() {
		try {
			Thread.sleep(7*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[1]/div/div/div[1]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		if(driver.findElement(title).getText() == "Decrease") {
			WebDriverWait wait2 = new WebDriverWait(driver, 20);
			By title2 = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[1]/div/text()");
			wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
			WebElement username = driver.findElement(title2);
			assertTrue(username.getCssValue("border-color").toString() != "rgb(255, 0, 0)");
		}
		else if(driver.findElement(title).getText() == "Increase") {
			WebDriverWait wait2 = new WebDriverWait(driver, 20);
			By title2 = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[1]/div/text()");
			wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
			WebElement username = driver.findElement(title2);
			assertTrue(username.getCssValue("border-color").toString() != "rgb(0, 128, 0)");
		}
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
