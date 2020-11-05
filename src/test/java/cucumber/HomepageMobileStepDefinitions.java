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
public class HomepageStepDefinitions {
	private static final String ROOT_URL = "https://localhost:3000/";

	private final WebDriver driver = new ChromeDriver();
	
	@Before()
	public void before() {
		new DropUserTable();
	}
	
	@Given("I am on index page h hpm")
	public void i_am_on_index_page_h_hpm() {
		driver.get(ROOT_URL);
	}

	@When("I login and am on the Hompage h hpm")
	public void i_login_and_am_on_the_Hompage_h_hpm() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}
	
	@When("I refresh the site hpm")
	public void i_refresh_the_site_hpm() {
		driver.get(ROOT_URL);
		driver.navigate().refresh();
	}

	@Then("I should see the title called USC CS310 Stock Portfolio Management hpm")
	public void i_should_see_the_title_called_USC_CS310_Stock_Portfolio_Management_hpm() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/a")).getText(), "USC CS310 STOCK PORTFOLIO MANAGEMENT");
	}

	@Given("I am on the Hompage h hpm")
	public void i_am_on_the_Hompage_h_hpm() {
		new DropUserTable();
		new CreateUserTable();
		new InitializeUserTable();
	    driver.get(ROOT_URL);
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[1]")).sendKeys("trojan");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div/input[2]")).sendKeys("12345Qa");
	    driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/button")).click();
	}

	@When("I click the title hpm")
	public void i_click_the_title_hpm() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/a")).click();
	}

	@Then("I should be on the hompage again hpm")
	public void i_should_be_on_the_hompage_again_hpm() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/a")).getText(), "USC CS310 STOCK PORTFOLIO MANAGEMENT");
	}

	@Then("I should see the banner color as grey hpm")
	public void i_should_see_the_banner_color_as_grey_hpm() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav")).getCssValue("background-color"), "rgba(120, 120, 120, 1)");
	}
	
	@Then("I should see the Tickers text hpm")
	public void i_should_see_the_Tickers_text_hpm() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"BTC\"]/table/thead/tr/th[1]")).getText(), "Tickers");
	}

	@Then("I should see the Last Price text hpm")
	public void i_should_see_the_Last_Price_text_hpm() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"BTC\"]/table/thead/tr/th[2]")).getText(), "Last Price");
	}

	@Then("I should see the Action text hpm")
	public void i_should_see_the_Action_text_hpm() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"BTC\"]/table/thead/tr/th[3]")).getText(), "Action");
	}

	@Then("I should see the view stock button hpm")
	public void i_should_see_the_view_stock_button_hpm() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).getText(), "VIEW STOCK");
	}

	@Then("I should see the remove stock from graph button hpm")
	public void i_should_see_the_remove_stock_from_graph_button_hpm() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[2]")).getText(), "REMOVE STOCK FROM GRAPH");
	}

	@Then("I should see the select dates button hpm")
	public void i_should_see_the_select_dates_button_hpm() {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[3]")).getText(), "SELECT DATES");
	}
	
	@Then("I should be able to click the clickable view stock button hpm")
	public void i_should_be_able_to_click_the_clickable_view_stock_button_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")));
		element.click();
	}

	@Then("I should be able to click the clickable remove stock from graph button hpm")
	public void i_should_be_able_to_click_the_clickable_remove_stock_from_graph_button_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[2]")));
		element.click();
	}

	@Then("I should be able to click the clickable select dates button hpm")
	public void i_should_be_able_to_click_the_clickable_select_dates_button_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[3]")));
		element.click();
	}
	
	@When("I click the view stock button hpm")
	public void i_click_the_view_stock_button_hpm() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[1]")).click();
	}

	@Then("I should be able to click the clickable view stock button2 hpm")
	public void i_should_be_able_to_click_the_clickable_view_stock_button2_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button[1]")));
		element.click();
	}

	@Then("I should be able to click the clickable cancel button in view stock hpm")
	public void i_should_be_able_to_click_the_clickable_cancel_button_in_view_stock_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[2]/button[2]")));
		element.click();
	}

	@Then("I should be able to click the clickable cross button in view stock hpm")
	public void i_should_be_able_to_click_the_clickable_cross_button_in_view_stock_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStockToGraph-form\"]/div[1]/i")));
		element.click();
	}
	
	@When("I click the remove stock from graph button hpm")
	public void i_click_the_remove_stock_from_graph_button_hpm() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[2]")).click();
	}

	@Then("I should be able to click the clickable remove stock button in remove stock hpm")
	public void i_should_be_able_to_click_the_clickable_remove_stock_button_in_remove_stock_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")));
		element.click();
	}

	@Then("I should be able to click the clickable cross button in remove stock hpm")
	public void i_should_be_able_to_click_the_clickable_cross_button_in_remove_stock_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[1]/i")));
		element.click();
	}
	
	@When("I click the select dates button hpm")
	public void i_click_the_select_dates_button_hpm() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/button[3]")).click();
	}

	@Then("I should be able to click the clickable Confirm dates button hpm")
	public void i_should_be_able_to_click_the_clickable_Confirm_dates_button_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")));
		element.click();
	}

	@Then("I should be able to click the clickable cross button in select dates hpm")
	public void i_should_be_able_to_click_the_clickable_cross_button_in_select_dates_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[1]/i")));
		element.click();
	}
	
	@Then("I should be able to click the clickable add stock button hpm")
	public void i_should_be_able_to_click_the_clickable_add_stock_button_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[2]/button")));
		element.click();
	}

	@When("I click the add stock button hpm")
	public void i_click_the_add_stock_button_hpm() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[1]/div[2]/button")).click();
	}

	@Then("I should be able to click the clickable add stock button in add stock hpm")
	public void i_should_be_able_to_click_the_clickable_add_stock_button_in_add_stock_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[2]/button")));
		element.click();
	}

	@Then("I should be able to click the clickable cross button in add stock hpm")
	public void i_should_be_able_to_click_the_clickable_cross_button_in_add_stock_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addStock-form\"]/div[1]/i")));
		element.click();
	}

	@Then("I should be able to click the clickable logout button hpm")
	public void i_should_be_able_to_click_the_clickable_logout_button_hpm() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10); 
		WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"responsive-navbar-nav\"]/span/button")));
		element.click();
	}
	
	@Then("I should be able to see the S&P {int} on the graph hpm")
	public void i_should_be_able_to_see_the_S_P_on_the_graph_hpm(Integer int1) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By title = By.cssSelector("[id^='highcharts-'] > svg > g.highcharts-legend > g > g > g.highcharts-legend-item.highcharts-line-series.highcharts-color-1.highcharts-series-1 > text > tspan");
		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		assertEquals(driver.findElement(title).getText(), "S&P 500");
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
