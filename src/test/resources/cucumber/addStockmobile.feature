Feature: User add stocks
  Scenario: Add stock button
    Given I am on index page a adm
	When I login and am on the Hompage a adm
	Then I should see the add stock button adm
	
  Scenario: add stock botton, check for logged in
    Given in mainpage and logged in a adm
	When I click add stock botton adm
	Then I get the pop up window to add stock adm
	
  Scenario: Ticker block
    Given in mainpage and logged in a adm
	When I click add stock botton adm
	Then I should see the ticker block adm
	
  Scenario: Quantity block
    Given in mainpage and logged in a adm
	When I click add stock botton adm
	Then I should see the quantity block adm
	
  Scenario: Start date block
    Given in mainpage and logged in a adm
	When I click add stock botton adm
	Then I should see the start date block adm
	
  Scenario: End date block
    Given in mainpage and logged in a adm
	When I click add stock botton adm
	Then I should see the end date block adm
	
  Scenario: Add stock button
  	Given in mainpage and logged in a adm
	When I click add stock botton adm
  	Then I should see the add stock button on the bottom adm

  Scenario: I put the wrong ticker
    Given in mainpage and in add stock pop up window adm
	When I fill in a wrong ticker adm
	Then I see the error message for wrong ticker adm

  Scenario: I put the invalid quantity
    Given in mainpage and in add stock pop up window adm
    When I fill in a wrong quantity adm
    Then I see the error message for invalid quantity adm
 
  Scenario: I do not put the start date
    Given in mainpage and in add stock pop up window adm
    When I doesn't fill the start date adm
    Then I see the error message for start date adm

  Scenario: I put the invalid start date
    Given in mainpage and in add stock pop up window adm
    When I fill the invalid start date adm
    Then I see the error message for invalid start date adm
    
  Scenario: I put the invalid ticker
    Given in mainpage and in add stock pop up window adm
    When I fill the invalid ticker adm
    Then I see the error message for invalid ticker adm
        
  Scenario: I put everything correctly
	Given in mainpage and in add pop up window adm
	When I fill in a correct ticker, purchase date, end date, and quantity adm
	Then I successfully add stock, the stock ticker and its price shown at the portfolio list to the right adm
