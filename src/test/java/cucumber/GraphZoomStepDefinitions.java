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
public class GraphZoomStepDefinitions {
	private static final String ROOT_URL = "https://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	
	int m1;
	int m2;
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on index page a gz")
	public void i_am_on_index_page_a_gz() {
		driver.get(ROOT_URL);
	}
	
	@When("I login and am on the Hompage a gz")
	public void i_login_and_am_on_the_Hompage_a_gz() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.get(ROOT_URL);
		driver.navigate().refresh();
	}
	
	@When("I click the Zoom in button")
	public void i_click_the_Zoom_in_button() {
		try {
			Thread.sleep(2*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get(ROOT_URL);
		driver.navigate().refresh();
		try {
			Thread.sleep(7*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get(ROOT_URL);
		driver.navigate().refresh();
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.cssSelector("[id^='highcharts-'] > div:nth-child(2) > span > i");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
	}

	@Then("I should see the {int} days range")
	public void i_should_see_the_days_range(Integer int1) {
		try {
			Thread.sleep(4*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.cssSelector("[id^='highcharts-'] > svg > g.highcharts-range-selector-group > g.highcharts-input-group > g:nth-child(2) > text > tspan");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		String a = driver.findElement(title).getText();
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		By title2 = By.cssSelector("[id^='highcharts-'] > svg > g.highcharts-range-selector-group > g.highcharts-input-group > g:nth-child(4) > text > tspan");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		String a2 = driver.findElement(title2).getText();
		
		String d1 = a.substring(4,5);
		String d2 = a2.substring(4,6);
		int u = Integer.parseInt(d1);
		int k = Integer.parseInt(d2);
		assertEquals(k-u, 5);
	}

	@When("I click the Zoom out button")
	public void i_click_the_Zoom_out_button() {
		try {
			Thread.sleep(7*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.cssSelector("[id^='highcharts-'] > div:nth-child(3) > span > i");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
	}

	@Then("I should see the {int} months range again")
	public void i_should_see_the_months_range_again(Integer int1) {
		try {
			Thread.sleep(4*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.cssSelector("[id^='highcharts-'] > svg > g.highcharts-range-selector-group > g.highcharts-input-group > g:nth-child(2) > text > tspan");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		String a = driver.findElement(title).getText();
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		By title2 = By.cssSelector("[id^='highcharts-'] > svg > g.highcharts-range-selector-group > g.highcharts-input-group > g:nth-child(4) > text > tspan");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		String a2 = driver.findElement(title2).getText();
		
		String d1 = a.substring(0,2);
		String d2 = a2.substring(0,2);
		
		if(d1 == "Jan") { m1 =1;}
		else if(d1 == "Feb") {m1 =2;}
		else if(d1 == "Mar") {m1 =3;}
		else if(d1 == "Apr") {m1 =4;}
		else if(d1 == "May") {m1 =5;}
		else if(d1 == "Jun") {m1 =6;}
		else if(d1 == "Jul") {m1 =7;}
		else if(d1 == "Aug") {m1 =8;}
		else if(d1 == "Sep") {m1 =9;}
		else if(d1 == "Oct") {m1 =10;}
		else if(d1 == "Nov") {m1 =11;}
		else if(d1 == "Dec") {m1 =12;}	
		if(d2 == "Jan") { m2 =1;}
		else if(d2 == "Feb") {m2 =2;}
		else if(d2 == "Mar") {m2 =3;}
		else if(d2 == "Apr") {m2 =4;}
		else if(d2 == "May") {m2 =5;}
		else if(d2 == "Jun") {m2 =6;}
		else if(d2 == "Jul") {m2 =7;}
		else if(d2 == "Aug") {m2 =8;}
		else if(d2 == "Sep") {m2 =9;}
		else if(d2 == "Oct") {m2 =10;}
		else if(d2 == "Nov") {m2 =11;}
		else if(d2 == "Dec") {m2 =12;}
		assertEquals(m1-m2, 0);
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
