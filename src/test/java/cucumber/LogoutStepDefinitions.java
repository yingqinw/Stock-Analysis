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
public class LogoutStepDefinitions {
	private static final String ROOT_URL = "http://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on index page o")
	public void i_am_on_index_page_o() {
		driver.get(ROOT_URL);
	}
	
	@Given("I login and am on the Hompage o")
	public void i_login_and_am_on_the_Hompage_o() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}
	
	@Then("I should see the logout button")
	public void i_should_see_the_logout_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"responsive-navbar-nav\"]/span/button")).getText(), "LOG OUT");
	}
	
	@When("I click the logout button")
	public void i_click_the_logout_button() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.findElement(By.xpath("//*[@id=\"responsive-navbar-nav\"]/span/button")).click();
	}
	@Then("I should see the login page")
	public void i_should_see_the_login_page() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "LOGIN");
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
