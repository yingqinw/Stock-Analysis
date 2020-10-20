Feature: User logout	
  Scenario: Add stock to graph button 
    Given I am on index page ga
	When I login and am on the Hompage ga
	Then I should see the add stock to graph button 
		
  Scenario: Add stock to graph
    Given I login and am on the Hompage ga
	When I click add stock to graph button
	Then I get the pop up window to add stock ga
	
  Scenario: Ticker block
    Given I login and am on the Hompage ga
	When I click add stock to graph button
	Then I should see the ticker block ga
	
  Scenario: Add stock to graph button
  	Given I login and am on the Hompage ga
	When I click add stock to graph button
  	Then I should see the add stock button to graph button
 
  Scenario: I put the wrong ticker
    Given in Homepage and in add stock to graph pop up window
	When I write the wrong ticker 
	Then I should see the error message for wrong ticker 
	
  Scenario: I put the invalid ticker
    Given in Homepage and in add stock to graph pop up window
    When I write the invalid ticker
    Then I should see the error message for invalid ticker
		
	