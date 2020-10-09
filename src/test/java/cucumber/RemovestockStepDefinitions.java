package cucumber;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	@Given("in mainpage, logged in and added a stock")
	public void in_mainpage_logged_in_and_added_a_stock() {
		//in_mainpage_and_in_add_pop_up_window
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/button")).click();
	
	    //I fill in a correct ticker, purchase date, and quantity
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("AAPL");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("11");
	    driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[3]")).sendKeys("11102019");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@When("click Delete Bottom")
	public void click_Delete_Bottom() {
		//click delete bottom
		driver.findElement(By.xpath("*[@id=\"BTC\"]/table/tbody/tr/td[3]/div")).click();
	}

	@Then("the stock remove from the list")
	public void the_stock_remove_from_the_list() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("*[@id=\"BTC\"]/table/tbody/tr/td[3]/div");
		try {
			driver.findElement(By.xpath("*[@id=\"BTC\"]/table/tbody/tr/td[3]/div"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		}catch(Exception e) {
			System.out.println("in exception");
			assert(true);
		}
		System.out.println("out of exception");
		assertEquals(driver.findElement(title).getText(), null);
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
