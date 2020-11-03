Feature: Graph add stock mobile	
  Scenario: View stock button 
    Given I am on index page ga gam
	When I login and am on the Hompage ga gam
	Then I should see the view stock button ga gam
		 
  Scenario: Add stock to graph
    Given I login and am on the Hompage ga gam
	When I click the view stock button ga gam
	Then I get the pop up window to add stock ga gam
	
  Scenario: Ticker block
    Given I login and am on the Hompage ga gam
	When I click the view stock button ga gam
	Then I should see the ticker block ga gam
	
  Scenario: View stock button in view stock 
  	Given I login and am on the Hompage ga gam
	When I click the view stock button ga gam
  	Then I should see the view stock button in view stock ga gam
  	
  Scenario: Cancel button 
  	Given I login and am on the Hompage ga gam
	When I click the view stock button ga gam
  	Then I should see the cancel button in view stock ga gam
 
  Scenario: I put the wrong ticker
    Given in Homepage and in add stock to graph pop up window gam
	When I write the wrong ticker gam
	Then I should see the error message for wrong ticker gam
	
  Scenario: I put the invalid ticker
    Given in Homepage and in add stock to graph pop up window gam
    When I write the invalid ticker gam
    Then I should see the error message for invalid ticker gam
		
	
