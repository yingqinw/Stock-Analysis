Feature: User login
  Scenario: Login letter
  	Given I am on the index page i
  	When I am on the login page1
  	Then I should see the login letter to go login page
  	
  Scenario: Signup letter
  	Given I am on the index page i
  	When I am on the login page2
  	Then I should see the signup letter to go signup page
  	
  Scenario: Username block
  	Given I am on the index page i
  	When I am on the login page3
  	Then I should see the username block
  	
  Scenario: Password block
  	Given I am on the index page i
  	When I am on the login page4
  	Then I should see the password block
  	
  Scenario: Login button
  	Given I am on the index page i
  	When I am on the login page5
  	Then I should see the login button
  	
  Scenario: Login letter clickable
  	Given I am on the index page i
  	When I am on the login page1
  	Then I should be able to click the clickable login letter button
  	
  Scenario: Login button clickable
  	Given I am on the index page i
  	When I am on the login page1
  	Then I should be able to click the clickable login button
  	
  Scenario: User types in invalid username
    Given I am on the index page i
    When I typed in 'he' in the Username field
    And I click the login button
    Then I should see the border color of Username is red
    And I should see error message 'Username can only contain alphanumeric characters and longer than 5 characters.'
    
  Scenario: User types in valid username but no password
    Given I am on the index page i
    When I typed in 'hello' in the Username field
    And I click the login button
    Then I should see error message 'Password should contain uppercase, lowercase and numeric character.'
    
  Scenario: Existing user logs in and redirect to homepage
    Given I am on the index page with existing account
    When I typed in 'trojan' in the Username field
    And I typed in '12345Qa' in the Password field
    And I click the login button
    Then I should see the homepage
