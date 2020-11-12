Feature: User add stocks
  Scenario: Zoom in functionality
    Given I am on index page a gz
	When I login and am on the Hompage a gz
	And I click the Zoom in button
	Then I should see the 3 days range
	
  Scenario: Zoom in functionality
    Given I am on index page a gz
	When I login and am on the Hompage a gz
	And I click the Zoom in button
	And I click the Zoom out button
	Then I should see the 3 months range again
		
	


	

 
