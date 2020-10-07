Feature: User logout
  Scenario: logout after login
    Given I login and am on the Hompage
	  When I click the logout button
	  Then I should see the login page