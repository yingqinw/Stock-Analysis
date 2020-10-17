Feature: User add stocks
  Scenario: Add stock button
    Given I am on index page a
	When I login and am on the Hompage a
	Then I should see the add stock button
	
  Scenario: add stock botton, check for logged in
    Given in mainpage and logged in a
	When I click add stock botton
	Then I get the pop up window to add stock
	
  Scenario: Ticker block
    Given in mainpage and logged in a
	When I click add stock botton
	Then I should see the ticker block
	
  Scenario: Quantity block
    Given in mainpage and logged in a
	When I click add stock botton
	Then I should see the quantity block
	
  Scenario: Start date block
    Given in mainpage and logged in a
	When I click add stock botton
	Then I should see the start date block
	
  Scenario: End date block
    Given in mainpage and logged in a
	When I click add stock botton
	Then I should see the end date block
	
  Scenario: Add stock button
  	Given in mainpage and logged in a
	When I click add stock botton
  	Then I should see the add stock button on the bottom

  Scenario: I put the wrong ticker
    Given in mainpage and in add stock pop up window
	When I fill in a wrong ticker
	Then I see the error message for wrong ticker 

  Scenario: I put the invalid quantity
    Given in mainpage and in add stock pop up window
    When I fill in a wrong quantity
    Then I see the error message for invalid quantity
 
  Scenario: I do not put the start date
    Given in mainpage and in add stock pop up window
    When I doesn't fill the start date
    Then I see the error message for start date

  Scenario: I put the invalid start date
    Given in mainpage and in add stock pop up window
    When I fill the invalid start date
    Then I see the error message for invalid start date
    
  Scenario: I put the invalid ticker
    Given in mainpage and in add stock pop up window
    When I fill the invalid ticker
    Then I see the error message for invalid ticker
        
  Scenario: I put everything correctly
	Given in mainpage and in add pop up window
	When I fill in a correct ticker, purchase date, end date, and quantity (an integer and > 0) 
	Then I successfully add stock, the stock ticker and its price shown at the portfolio list to the right
