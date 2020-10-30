Feature: User logout
  Scenario: logout button
  	Given I am on index page o
	When I login and am on the Hompage o
	Then I should see the logout button
	
  Scenario: logout after login  
    Given I login and am on the Hompage o
	When I click the logout button
	Then I should see the login page
	
  Scenario: logout by timeout
    Given I login and am on the Hompage o
	When I am waiting for more than 120 min 
	Then I should see the login page
