Feature: User logout	
  Scenario: View stock button 
    Given I am on index page ga
	When I login and am on the Hompage ga
	Then I should see the view stock button ga
		
  Scenario: Add stock to graph
    Given I login and am on the Hompage ga
	When I click the view stock button ga
	Then I get the pop up window to add stock ga
	
  Scenario: Ticker block
    Given I login and am on the Hompage ga
	When I click the view stock button ga
	Then I should see the ticker block ga
	
  Scenario: View stock button in view stock 
  	Given I login and am on the Hompage ga
	When I click the view stock button ga
  	Then I should see the view stock button in view stock ga
  	
  Scenario: Cancel button 
  	Given I login and am on the Hompage ga
	When I click the view stock button ga
  	Then I should see the cancel button in view stock ga
 
  Scenario: I put the wrong ticker
    Given in Homepage and in add stock to graph pop up window
	When I write the wrong ticker 
	Then I should see the error message for wrong ticker 
	
  Scenario: I put the invalid ticker
    Given in Homepage and in add stock to graph pop up window
    When I write the invalid ticker
    Then I should see the error message for invalid ticker
		
	
