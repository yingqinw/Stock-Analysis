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
public class GraphSelectDatesStepDefinitions {
	private static final String ROOT_URL = "https://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	int m1;
	int m2;
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on index page gd")
	public void i_am_on_index_page_gd() {
		driver.get(ROOT_URL);
	}

	@When("I login and am on the Hompage gd")
	public void i_login_and_am_on_the_Hompage_gd() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}

	@Then("I should see the select dates from graph button")
	public void i_should_see_the_select_dates_from_graph_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[3]")).getText(), "SELECT DATES");
	}

	@When("I click select stock to graph button")
	public void i_click_select_stock_to_graph_button() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[3]")).click();
	}

	@Then("I get the pop up window for select dates")
	public void i_get_the_pop_up_window_for_select_dates() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[1]/div")).getText(), "SELECT DATES");
	}

	@Then("I should see the start date block gd")
	public void i_should_see_the_start_date_block_gd() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).getAttribute("placeholder"), "start date");
	}

	@Then("I should see the end date block gd")
	public void i_should_see_the_end_date_block_gd() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).getAttribute("placeholder"), "end date");
	}

	@Then("I should see the confirm dates button")
	public void i_should_see_the_confirm_dates_button() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).getText(), "CONFIRM DATES");
	}

	@Given("in Homepage and in select dates pop up window")
	public void in_Homepage_and_in_select_dates_pop_up_window() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[3]")).click();
	}

	@When("I write the wrong end date")
	public void i_write_the_wrong_end_date() {
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("12102019");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("12102018");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I should see the error message for end date gd")
	public void i_should_see_the_error_message_for_end_date_gd() {
		WebElement ticker = driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]"));
	    assertEquals("rgb(255, 0, 0)",ticker.getCssValue("border-color"));
	}
	
	@Given("in Homepage and in view stock to graph pop up window")
	public void in_Homepage_and_in_view_stock_to_graph_pop_up_window() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).click();
	}
	
	@When("I add a stock on the graph to view gsd")
	public void i_add_a_stock_on_the_graph_to_view_gsd() {
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/div/input")).sendKeys("AAPL");
		driver.findElement(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button[1]")).click();	
	}
	@Then("I should see the {int} months dates as default")
	public void i_should_see_the_months_dates_as_default(Integer int1) {
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.cssSelector("[id^='highcharts-'] > svg > g:nth-child(23) > text:nth-child(7) > tspan");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		String a = driver.findElement(title).getText();
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		By title2 = By.cssSelector("[id^='highcharts-'] > svg > g:nth-child(23) > text:nth-child(1) > tspan");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		String b = driver.findElement(title2).getText();
		String str1 = a.substring(3);
		String str2 = b.substring(4);
		System.out.print("HY1"+str1);
		System.out.print("HY2"+str2);
		if(str1 == "Jan") { m1 =1;}
		else if(str1 == "Feb") {m1 =2;}
		else if(str1 == "Mar") {m1 =3;}
		else if(str1 == "Apr") {m1 =4;}
		else if(str1 == "May") {m1 =5;}
		else if(str1 == "Jun") {m1 =6;}
		else if(str1 == "Jul") {m1 =7;}
		else if(str1 == "Aug") {m1 =8;}
		else if(str1 == "Sep") {m1 =9;}
		else if(str1 == "Oct") {m1 =10;}
		else if(str1 == "Nov") {m1 =11;}
		else if(str1 == "Dec") {m1 =12;}
		
		if(str2 == "Jan") { m2 =1;}
		else if(str2 == "Feb") {m2 =2;}
		else if(str2 == "Mar") {m2 =3;}
		else if(str2 == "Apr") {m2 =4;}
		else if(str2 == "May") {m2 =5;}
		else if(str2 == "Jun") {m2 =6;}
		else if(str2 == "Jul") {m2 =7;}
		else if(str2 == "Aug") {m2 =8;}
		else if(str2 == "Sep") {m2 =9;}
		else if(str2 == "Oct") {m2 =10;}
		else if(str2 == "Nov") {m2 =11;}
		else if(str2 == "Dec") {m2 =12;}
		
		System.out.print("HYK1"+m1);
		System.out.print("HYK2"+m2);


		int range = m1-m2;
		assertTrue(range==0);
	}
	@When("I click the select dates button gsd")
	public void i_click_the_select_dates_button_gsd() {
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By title = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[3]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).click();
	}
	
	@When("I specify the dates from {int}\\/{int}\\/{int} to {int}\\/{int}\\/{int}")
	public void i_specify_the_dates_from_to(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5, Integer int6) {
			
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[1]")).sendKeys("09102020");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/div/input[2]")).sendKeys("10102020");
		driver.findElement(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")).click();
	}

	@Then("I should see the graph for {int} month")
	public void i_should_see_the_graph_for_month(Integer int1) {
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By title = By.cssSelector("[id^='highcharts-'] > svg > g:nth-child(23) > text:nth-child(7) > tspan");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		String a = driver.findElement(title).getText();
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		By title2 = By.cssSelector("[id^='highcharts-'] > svg > g:nth-child(23) > text:nth-child(1) > tspan");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(title2));
		String b = driver.findElement(title2).getText();
		String str1 = a.substring(3);
		String str2 = b.substring(4);
		System.out.print("HY3"+str1);
		System.out.print("HY4"+str2);
		
		if(str1 == "Jan") { m1 =1;}
		else if(str1 == "Feb") {m1 =2;}
		else if(str1 == "Mar") {m1 =3;}
		else if(str1 == "Apr") {m1 =4;}
		else if(str1 == "May") {m1 =5;}
		else if(str1 == "Jun") {m1 =6;}
		else if(str1 == "Jul") {m1 =7;}
		else if(str1 == "Aug") {m1 =8;}
		else if(str1 == "Sep") {m1 =9;}
		else if(str1 == "Oct") {m1 =10;}
		else if(str1 == "Nov") {m1 =11;}
		else if(str1 == "Dec") {m1 =12;}
		
		if(str2 == "Jan") { m2 =1;}
		else if(str2 == "Feb") {m2 =2;}
		else if(str2 == "Mar") {m2 =3;}
		else if(str2 == "Apr") {m2 =4;}
		else if(str2 == "May") {m2 =5;}
		else if(str2 == "Jun") {m2 =6;}
		else if(str2 == "Jul") {m2 =7;}
		else if(str2 == "Aug") {m2 =8;}
		else if(str2 == "Sep") {m2 =9;}
		else if(str2 == "Oct") {m2 =10;}
		else if(str2 == "Nov") {m2 =11;}
		else if(str2 == "Dec") {m2 =12;}
		
		System.out.print("HYK3"+m1);
		System.out.print("HYK4"+m2);

		int range = m1-m2;
		assertTrue(range==0);
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
