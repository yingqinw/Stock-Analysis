package cucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import csci310.CreateUserTable;
import csci310.DropUserTable;
import csci310.InitializeUserTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
	
	@When("I click delete bottom")
	public void i_click_delete_bottom() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).click();
		throw new io.cucumber.java.PendingException();
	}

	@Then("I get the pop up window to delete stock")
	public void i_get_the_pop_up_window_to_delete_stock() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("in mainpage and in delete pop up window")
	public void in_mainpage_and_in_delete_pop_up_window() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I can’t successfully delete stock")
	public void i_can_t_successfully_delete_stock() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("in mainpage and in delete pop up window2")
	public void in_mainpage_and_in_delete_pop_up_window2() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I can’t successfully delete stock2")
	public void i_can_t_successfully_delete_stock2() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("in mainpage and in delete pop up window3")
	public void in_mainpage_and_in_delete_pop_up_window3() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I can’t successfully delete stock3")
	public void i_can_t_successfully_delete_stock3() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("in mainpage and in delete pop up window4")
	public void in_mainpage_and_in_delete_pop_up_window4() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I can’t successfully delete stock4")
	public void i_can_t_successfully_delete_stock4() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
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
