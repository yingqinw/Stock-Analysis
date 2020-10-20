Feature: User logout	
  Scenario: Remove stock from graph button
    Given I am on index page gr
	When I login and am on the Hompage gr
	Then I should see the remove stock from graph button gr

  Scenario: Remove stock to graph
    Given I login and am on the Hompage gr
	When I click remove stock to graph button
	Then I get the pop up window for remove stock gr
	
  Scenario: Ticker block
    Given I login and am on the Hompage gr
	When I click remove stock to graph button
	Then I should see the ticker block gr
	
  Scenario: Remove stock to graph button
  	Given I login and am on the Hompage gr
	When I click remove stock to graph button
  	Then I should see the remove stock button to graph button
 
  Scenario: I put the invalid ticker
    Given in Homepage and in remove stock to graph pop up window
	When I write the wrong ticker gr
	Then I should see the error message for wrong ticker gr
	
		
	
