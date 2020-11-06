Feature: User add stocks
  Scenario: Portfolio Number
    Given I am on index page a pn
	When I login and am on the Hompage a pn
	Then I should see the portflio number 0.00
	
  Scenario: Increase letter 
    Given I am on index page a pn
	When I login and am on the Hompage a pn
	Then I should see the Increase letter 
	
  Scenario: Percentage number
    Given I am on index page a pn
	When I login and am on the Hompage a pn
	Then I should see the 0.0% 
	
  Scenario: Add a stock that update the portfolio number
    Given in mainpage and logged in a pn
	When I click add stock botton pn
	Then I should see the portflio value number changed for the stock 
	
  Scenario: Add the second stock that update the portfolio number
    Given in mainpage and logged in a pn
	When I click add another stock pn
	Then I should see the new portflio value number changed for the stocks
	
  Scenario: Click the toggle for the first stock
     Given in mainpage and logged in a pn
	When I click the toggle for the first stock pn
	Then I should see the updated porfolio value for the second stock
	

 