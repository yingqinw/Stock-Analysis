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
public class UploadCSVFileStepDefinitions {
	private static final String ROOT_URL = "https://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on index page ucf")
	public void i_am_on_index_page_ucf() {
		driver.get(ROOT_URL);
	}

	@When("I login and am on the Hompage ucf")
	public void i_login_and_am_on_the_Hompage_ucf() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}
	
	@Then("I should see the upload file button")
	public void i_should_see_the_upload_file_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"responsive-navbar-nav\"]/span/button[1]")).getText(), "UPLOAD FILE");

	}

	@Then("I should be able to click the clickable upload file button")
	public void i_should_be_able_to_click_the_clickable_upload_file_button() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"responsive-navbar-nav\"]/span/button[1]")));
		element.click();
	}

	@When("I login and am on the Hompage ucf and click the upload file button")
	public void i_login_and_am_on_the_Hompage_ucf_and_click_the_upload_file_button() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"responsive-navbar-nav\"]/span/button[1]")).click();
	}

	@Then("I should be on the upload file pop up window")
	public void i_should_be_on_the_upload_file_pop_up_window() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/div")).getText(), "UPLOAD FILE");
	}

	@Then("I should see the upload file here part")
	public void i_should_see_the_upload_file_here_part() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/h4")).getText(), "Upload File Here");
	}

	@Then("I should be able to click the clickable choose file")
	public void i_should_be_able_to_click_the_clickable_choose_file() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input")).getAttribute("placeholder"), "upload a csv file");

	}

	@Then("I should see the upload file button in upload file")
	public void i_should_see_the_upload_file_button_in_upload_file() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button[1]")).getText(), "UPLOAD FILE");
	}

	@Then("I should be able to click the clickable upload file button in upload file")
	public void i_should_be_able_to_click_the_clickable_upload_file_button_in_upload_file() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button[1]")));
		element.click();
	}

	@Then("I should see the cancel button in upload file")
	public void i_should_see_the_cancel_button_in_upload_file() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button[2]")).getText(), "CANCEL");
	}

	@Then("I should be able to click the clickable cancel button in upload file")
	public void i_should_be_able_to_click_the_clickable_cancel_button_in_upload_file() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button[2]")));
		element.click();
	}

	@Then("I should be able to click the clickable cross button in upload file")
	public void i_should_be_able_to_click_the_clickable_cross_button_in_upload_file() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[1]/i")));
		element.click();
	}
	
	@When("I upload a file")
	public void i_upload_a_file() {
		WebElement uploadElement = driver.findElement(By.className("highInput"));
		uploadElement.sendKeys("/Users/hyunjaecho/git/project-20203-group33-20203/test.csv");
	}
	

	@Then("I should be able to see the file is uploaded")
	public void i_should_be_able_to_see_the_file_is_uploaded() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		WebElement filename = driver.findElement(title);
	    assertEquals("rgb(255, 0, 0)",filename.getCssValue("border-color"));
	}
	
	@When("I click the cancel button in upload file")
	public void i_click_the_cancel_button_in_upload_file() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button[2]")).click();
	}

	@Then("there should not the list that I add")
	public void there_should_not_the_list_that_I_add() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"BTC\"]/table/thead/tr/th[1]")).getText(), "Tickers");
	}

	@When("I click the upload file button in upload file")
	public void i_click_the_upload_file_button_in_upload_file() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button[1]")).click();
	}

	@Then("I should see the stocks on the portfolio")
	public void i_should_see_the_stocks_on_the_portfolio() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		By title = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr[1]/td[1]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "AAPL");
		driver.findElement(By.xpath("//*[@id=\"BTC\"]/table/tbody/tr[1]/td[3]/div")).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
		driver.get(ROOT_URL);
		driver.navigate().refresh();
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		By title2 = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr[1]/td[1]");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		assertEquals(driver.findElement(title2).getText(), "AMZN");
		WebDriverWait wait3 = new WebDriverWait(driver, 30);
		By title3 = By.xpath("//*[@id=\"BTC\"]/table/tbody/tr/td[3]/div");
		wait3.until(ExpectedConditions.visibilityOfElementLocated(title3));
		driver.findElement(title3).click();
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/button[1]")).click();
	}
	
	@When("I upload a file with unvalid info")
	public void i_upload_a_file_with_unvalid_info() {
		WebElement uploadElement = driver.findElement(By.className("highInput"));
		uploadElement.sendKeys("/Users/hyunjaecho/git/project-20203-group33-20203/testError.csv");
	}

	@Then("I should see the error messages containing the error lines")
	public void i_should_see_the_error_messages_containing_the_error_lines() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		By title = By.xpath("//*[@id=\"addStock-form\"]/div[3]/p");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "There are errors on the following line(s): 3 4 5 6 7");
	}
	
	@When("I do not upload a file")
	public void i_do_not_upload_a_file() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button[1]")).click();
	}

	@Then("I should be able to see the error message")
	public void i_should_be_able_to_see_the_error_message() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		By title = By.xpath("//*[@id=\"addStock-form\"]/div[3]/p");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "Please select a file to upload");
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
