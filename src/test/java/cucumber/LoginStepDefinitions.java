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
public class LoginStepDefinitions {
	private static final String ROOT_URL = "http://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on the index page i")
	public void i_am_on_the_index_page_i() {
	    driver.get(ROOT_URL);
	}
	
	@Given("I am on the index page with existing account")
	public void i_am_on_the_index_page_with_existing_account() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	}
	
	@When("I am on the login page1")
	public void i_am_on_the_login_page1() {
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]")).click();
	}

	@Then("I should see the login letter to go login page")
	public void i_should_see_the_login_letter_to_go_login_page() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]")).getText(), "LOGIN");
	}

	@When("I am on the login page2")
	public void i_am_on_the_login_page2() {
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]")).click();
	}

	@Then("I should see the signup letter to go signup page")
	public void i_should_see_the_signup_letter_to_go_signup_page() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[2]")).getText(), "SIGNUP");
	}
	
	@When("I am on the login page3")
	public void i_am_on_the_login_page3() {
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]")).click();
	}

	@Then("I should see the username block")
	public void i_should_see_the_username_block() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).getAttribute("placeholder"), "Username");
	}

	@When("I am on the login page4")
	public void i_am_on_the_login_page4() {
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]")).click();
	}

	@Then("I should see the password block")
	public void i_should_see_the_password_block() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).getAttribute("placeholder"), "Password");
	}
	
	@When("I am on the login page5")
	public void i_am_on_the_login_page5() {
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]")).click();
	}

	@Then("I should see the login button")
	public void i_should_see_the_login_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).getText(), "LOGIN");
	}

	@When("I typed in {string} in the Username field")
	public void i_typed_in_in_the_Username_field(String string) {
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys(string);
	}

	@When("I click the login button")
	public void i_click_the_login_button() {
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}

	@Then("I should see the border color of Username is red")
	public void i_should_see_the_border_color_of_Username_is_red() {
	    WebElement username = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]"));
	    assertEquals("rgb(255, 0, 0)",username.getCssValue("border-color"));
	}

	@Then("I should see error message {string}")
	public void i_should_see_error_message(String string) {
	    WebElement alert = driver.findElement(By.cssSelector("#login-form > div.alertWrapper"));
	    assertTrue(alert.getText().contains(string));
	}
	
	@Then("I should see the border color of Username is not red")
	public void i_should_see_the_border_color_of_Username_is_not_red() {
		WebElement username = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]"));
		assertTrue(username.getCssValue("border-color").toString() != "rgb(255, 0, 0)");
	}
	
	@When("I typed in {string} in the Password field")
	public void i_typed_in_in_the_Password_field(String string) {
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys(string);
	}
	
	@Then("I should see the homepage")
	public void i_should_see_the_homepage() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/nav/a");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "USC CS310 STOCK PORTFOLIO MANAGEMENT");
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
