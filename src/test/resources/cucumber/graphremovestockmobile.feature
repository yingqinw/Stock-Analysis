Feature: Graph remove stock mobile
  Scenario: Remove stock from graph button
    Given I am on index page gr grmsm
	When I login and am on the Hompage gr grmsm
	Then I should see the remove stock from graph button gr grmsm

  Scenario: Remove stock to graph
    Given I login and am on the Hompage gr grmsm
	When I click remove stock to graph button grmsm
	Then I get the pop up window for remove stock gr grmsm
	
  Scenario: Ticker block
    Given I login and am on the Hompage gr grmsm
	When I click remove stock to graph button grmsm
	Then I should see the ticker block gr grmsm
	
  Scenario: Remove stock to graph button
  	Given I login and am on the Hompage gr grmsm
	When I click remove stock to graph button grmsm
  	Then I should see the remove stock button to graph button grmsm
 
  Scenario: I put the invalid ticker
    Given in Homepage and in remove stock to graph pop up window grmsm
	When I write the wrong ticker gr grmsm
	Then I should see the error message for wrong ticker gr grmsm
	
  Scenario: I put the valid ticker and see the confirmation window
    Given in Homepage and in remove stock to graph pop up window grmsm
	When I write the valid ticker and click remove stock button grmsm
	Then I should see the confirmation window grmsm
	
  Scenario: Remove Stock button on confirmation page
    Given in Homepage and in remove stock to graph pop up window grmsm
	When I write the valid ticker and click remove stock button grmsm
	Then I should see the remove stock button on confirmation page grmsm
	
  Scenario: Cancel button on confirmation page
    Given in Homepage and in remove stock to graph pop up window grmsm
	When I write the valid ticker and click remove stock button grmsm
	Then I should see the cancel button on confirmation page grmsm
	
  Scenario: Remove Stock button clickable on confirmation page grmsm 
    Given in Homepage and in remove stock to graph pop up window grmsm
	When I write the valid ticker and click remove stock button grmsm
	Then I should be able to click the clickable stock button on confirmation page grmsm
	
  Scenario: Cancel button clickable on confirmation page 
    Given in Homepage and in remove stock to graph pop up window grmsm
	When I write the valid ticker and click remove stock button grmsm
	Then I should be able to click the clickable cancel button on confirmation page grmsm
	
  Scenario: Not remove the stock
    Given in Homepage and in view stock to graph pop up window and add a stock on the graph to view grmsm
    When I click the remove stock from graph button grmsm
	And I write the valid ticker and click remove stock button2 grmsm
	And I click the cancel button gms grmsm
	Then I should see the stock on the graph gms grmsm
	
  Scenario: Remove the stock
    Given in Homepage and in view stock to graph pop up window and add a stock on the graph to view grmsm
    When I click the remove stock from graph button grmsm
	And I write the valid ticker and click remove stock button2 grmsm
	And I click the remove stock button gms grmsm
	Then I should not see the stock on the graph gms grmsm
		
	
