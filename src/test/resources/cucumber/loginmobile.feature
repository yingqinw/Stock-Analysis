Feature: User login
  Scenario: Login letter
  	Given I am on the index page i lm
  	When I am on the login page1 lm
  	Then I should see the login letter to go login page lm
  	
  Scenario: Signup letter
  	Given I am on the index page i lm
  	When I am on the login page2 lm
  	Then I should see the signup letter to go signup page lm
  	
  Scenario: Username block
  	Given I am on the index page i lm
  	When I am on the login page3 lm
  	Then I should see the username block lm
  	
  Scenario: Password block
  	Given I am on the index page i lm
  	When I am on the login page4 lm
  	Then I should see the password block lm
  	
  Scenario: Login button
  	Given I am on the index page i lm
  	When I am on the login page5 lm
  	Then I should see the login button lm
  
  Scenario: User types in invalid username
    Given I am on the index page i lm
    When I typed in 'he' in the Username field lm
    And I click the login button lm
    Then I should see the border color of Username is red lm
    And I should see error message 'Username can only contain alphanumeric characters and longer than 5 characters.' lm
    
  Scenario: User types in valid username but no password
    Given I am on the index page i lm
    When I typed in 'hello' in the Username field lm
    And I click the login button lm
    Then I should see error message 'Password should contain uppercase, lowercase and numeric character.' lm
    
  Scenario: Existing user logs in and redirect to homepage
    Given I am on the index page with existing account lm
    When I typed in 'trojan' in the Username field lm
    And I typed in '12345Qa' in the Password field lm
    And I click the login button lm
    Then I should see the homepage lm
