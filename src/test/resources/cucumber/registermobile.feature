Feature: New User register
  Scenario: Login letter
  	Given I am on the index page of signup sm
  	When I am on the signup page1 sm
  	Then I should see the login letter to go login page r sm
  	
  Scenario: Signup letter
  	Given I am on the index page of signup sm
  	When I am on the signup page2 sm
  	Then I should see the signup letter to go signup page r sm
  	
   Scenario: Username block
  	Given I am on the index page of signup sm
  	When I am on the signup page3 sm
  	Then I should see the username block r sm
  	
  Scenario: Password block
  	Given I am on the index page of signup sm
  	When I am on the signup page4 sm
  	Then I should see the password block r sm
  	
  Scenario: Retype Password block
  	Given I am on the index page of signup sm
  	When I am on the signup page5 sm 
  	Then I should see the retype password block r sm
  	
  Scenario: Create user button
  	Given I am on the index page of signup sm
  	When I am on the signup page6 sm
  	Then I should see the create user button sm
  	
  Scenario: User types in invalid username in registration
    Given I am on the index page of signup sm
    When I typed in 'he' in the Username field in registration form sm
    And I click the signup button sm
    Then I should see the border color of Username is red in registration form sm
    And I should see error message 'Username can only contain alphanumeric characters and longer than 5 characters.' in registration form sm
    
  Scenario: User types in valid username but no password in registration
    Given I am on the index page of signup sm
    When I typed in 'hello' in the Username field in registration form sm
    And I click the signup button sm
    Then I should see the border color of Username is not red in registration form sm
    And I should see error message 'Password should contain uppercase, lowercase and numeric character.' in registration form sm

  Scenario: User types in different passwords
    Given I am on the index page of signup sm
    When I typed in 'hello' in the Username field in registration form sm
    And I typed in '12345Qa' in the Password field in registration form sm
    And I typed in '12345Q' in the Retype Password field in registration form sm
    And I click the signup button sm
    Then I should see error message 'Passwords did not match.' in registration form sm
      
  Scenario: User types in all valid inputs and direct to homepage
    Given I am on the index page of signup sm
    When I typed in 'ttrojan' in the Username field in registration form sm
    And I typed in '12345Qa' in the Password field in registration form sm
    And I typed in '12345Qa' in the Retype Password field in registration form sm
    And I click the signup button sm
    Then I should see the homepage differnt from signup page sm
    
  Scenario: Register existing user would fail
    Given I am on the index page of signup with existing user account sm
    When I typed in 'trojan' in the Username field in registration form sm
    And I typed in '12345Qa' in the Password field in registration form sm
    And I typed in '12345Qa' in the Retype Password field in registration form sm
    And I click the signup button sm
    Then I should see error message 'User already exists.' in registration form sm
 
