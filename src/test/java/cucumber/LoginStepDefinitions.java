package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Step definitions for Cucumber tests.
*/
public class LoginStepDefinitions {
	private static final String ROOT_URL = "http://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();

	@Given("I am on the index page")
	public void i_am_on_the_index_page() {
	    driver.get(ROOT_URL);
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
