Feature: User add stocks
  Scenario: add/delete bottom, check for logged in
   Given in mainpage and logged in
	When I click add/delete bottom
	Then I get the pop up window to add/delete stock

  Scenario: add/delete pop up window
    Given in mainpage and in add/delete pop up window
	When I fill in a wrong ticker
	Then I can’t successfully add/delete stock

  Scenario: add/delete pop up window
    Given in mainpage and in add/delete pop up window
    When I doesn't fill the start date
    Then I can’t successfully add/delete stock
    
  Scenario: add/delete pop up window
    Given in mainpage and in add/delete pop up window
    When I fill the invalid start date
    Then I can’t successfully add/delete stock
    
  Scenario: add pop up window
	Given in mainpage and in add pop up window
	When I fill in a correct ticker, purchase date, and quantity (an integer and > 0) 
	Then I successfully add stock, the stock ticker and its price shown at the portfolio list to the right
	
   Scenario: add pop up window
	Given in mainpage and in add pop up window
	When I fill in a correct ticker, purchase date, end date, and quantity (an integer and > 0) 
	Then I successfully add stock, the stock ticker and its price shown at the portfolio list to the right


