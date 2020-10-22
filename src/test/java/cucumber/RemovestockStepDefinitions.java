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
public class RemovestockStepDefinitions {
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
	
	@When("I add a stock")
	public void i_add_a_stock() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AMZN");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("12102019");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[4]")).sendKeys("05102020");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I should see the delete button")
	public void i_should_see_the_delete_button() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[3]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "Delete");
		driver.findElement(title).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
	}
	
	@Given("in mainpage, logged in, and add a stock")
	public void in_mainpage_logged_in_and_add_a_stock() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AMZN");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("12102019");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[4]")).sendKeys("05102020");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@When("I click the delete button")
	public void i_click_the_delete_button() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[3]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
	}

	@Then("I should see the text Do you want to delete ticker AMZN ?")
	public void i_should_see_the_text_Do_you_want_to_delete_ticker_AMZN() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/div/p")).getText(), "Do you want to delete ticker AMZN ?");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
	}
	
	@Then("I should the yes button")
	public void i_should_the_yes_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).getText(), "YES");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
	}

	@Then("I should the no button")
	public void i_should_the_no_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[2]")).getText(), "NO");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
	}
	
	@When("I click the no button")
	public void i_click_the_no_button() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[2]")).click();
	}

	@Then("there is still the stock that I added")
	public void there_is_still_the_stock_that_I_added() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[1]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "AMZN");
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		By title2 = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[3]/div");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		driver.findElement(title2).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
		
		
	}
	
	@When("I click the delete button for the stock and click the yes button")
	public void i_click_the_delete_button_for_the_stock_and_click_the_yes_button() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[3]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
	}

	@Then("there should be gone that I intend to delete")
	public void there_should_be_gone_that_I_intend_to_delete() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		By title2 = By.xpath("//*[@id=\"BTC\"]/table/thead/tr/th[1]");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		assertEquals(driver.findElement(title2).getText(), "Tickers");
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
