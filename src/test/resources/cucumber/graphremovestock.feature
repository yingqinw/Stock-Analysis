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
	
  Scenario: I put the valid ticker and see the confirmation window
    Given in Homepage and in remove stock to graph pop up window
	When I write the valid ticker and click remove stock button
	Then I should see the confirmation window
	
  Scenario: Remove Stock button on confirmation page
    Given in Homepage and in remove stock to graph pop up window
	When I write the valid ticker and click remove stock button
	Then I should see the remove stock button on confirmation page
	
  Scenario: Cancel button on confirmation page
    Given in Homepage and in remove stock to graph pop up window
	When I write the valid ticker and click remove stock button
	Then I should see the cancel button on confirmation page
	
  Scenario: Remove Stock button clickable on confirmation page 
    Given in Homepage and in remove stock to graph pop up window
	When I write the valid ticker and click remove stock button
	Then I should be able to click the clickable stock button on confirmation page
	
  Scenario: Cancel button clickable on confirmation page 
    Given in Homepage and in remove stock to graph pop up window
	When I write the valid ticker and click remove stock button
	Then I should be able to click the clickable cancel button on confirmation page
	
  Scenario: Not remove the stock
    Given in Homepage and in view stock to graph pop up window and add a stock on the graph to view
    When I click the remove stock from graph button
	And I write the valid ticker and click remove stock button2
	And I click the cancel button gms
	Then I should see the stock on the graph gms
	
  Scenario: Remove the stock
    Given in Homepage and in view stock to graph pop up window and add a stock on the graph to view
    When I click the remove stock from graph button
	And I write the valid ticker and click remove stock button2
	And I click the remove stock button gms
	Then I should not see the stock on the graph gms
		
	
