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
public class RegisterStepDefinitions {
	private static final String ROOT_URL = "http://localhost:3000/";
	
	private final WebDriver driver = new ChromeDriver();
	private final WebDriverWait wait = new WebDriverWait(driver, 10);
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	@Given("I am on the index page of signup")
	public void i_am_on_the_index_page_of_signup() {
		driver.get(ROOT_URL);
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[2]")).click();
		By form = By.xpath("//*[@id=\"signup-form\"]/div[2]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(form));
	}
	
	@Given("I am on the index page of signup with existing user account")
	public void i_am_on_the_index_page_of_signup_with_existing_user_account() {
		new CreateUserTable();
		new InitializeUserTable();
		driver.get(ROOT_URL);
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[2]")).click();
		By form = By.xpath("//*[@id=\"signup-form\"]/div[2]/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(form));
	}

	@When("I typed in {string} in the Username field in registration form")
	public void i_typed_in_in_the_Username_field_in_registration_form(String string) {
		driver.findElement(By.xpath("//*[@id=\"signup-form\"]/div[2]/div/input[1]")).sendKeys(string);
	}

	@When("I click the signup button")
	public void i_click_the_signup_button() {
		driver.findElement(By.xpath("//*[@id=\"signup-form\"]/div[2]/button")).click();
	}

	@Then("I should see the border color of Username is red in registration form")
	public void i_should_see_the_border_color_of_Username_is_red_in_registration_form() {
		WebElement username = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/div[2]/div/input[1]"));
	    assertEquals("rgb(255, 0, 0)",username.getCssValue("border-color"));
	}

   @Then("I should see the border color of Username is not red in registration form")
	public void i_should_see_the_border_color_of_Username_is_not_red_in_registration_form() {
	   WebElement username = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/div[2]/div/input[1]"));
	   assertTrue(username.getCssValue("border-color").toString() != "rgb(255, 0, 0)");
	}
   
	@When("I typed in {string} in the Password field in registration form")
	public void i_typed_in_in_the_Password_field_in_registration_form(String string) {
		driver.findElement(By.xpath("//*[@id=\"signup-form\"]/div[2]/div/input[2]")).sendKeys(string);
	}
	
	@When("I typed in {string} in the Retype Password field in registration form")
	public void i_typed_in_in_the_Retype_Password_field_in_registration_form(String string) {
		driver.findElement(By.xpath("//*[@id=\"signup-form\"]/div[2]/div/input[3]")).sendKeys(string);
	}
	
	@Then("I should see error message {string} in registration form")
	public void i_should_see_error_message_in_registration_form(String string) {
	    WebElement alert = driver.findElement(By.cssSelector("#signup-form > div.alertWrapper"));
	    assertTrue(alert.getText().contains(string));
	}
	
	@Then("I should see the homepage differnt from signup page")
	public void i_should_see_the_homepage_differnt_from_signup_page() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/nav/a");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "STOCKANALYSIS");
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
