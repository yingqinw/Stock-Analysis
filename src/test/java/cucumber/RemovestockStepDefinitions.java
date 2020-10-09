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
import static org.junit.Assert.assertTrue;

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
	
	@Given("in mainpage and add a stock")
	public void in_mainpage_and_add_a_stock() {
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
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("12102019");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@When("I click the delete button")
	public void i_click_the_delete_button() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[3]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
		driver.findElement(title).click();
		
	}
	@Then("the stock will be delete from the list")
	public void the_stock_will_be_delete_from_the_list() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertTrue(driver.findElements(By.linkText("//*[@id=\"BTC\"]/table/tbody")).size() < 1);
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
