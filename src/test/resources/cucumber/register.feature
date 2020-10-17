Feature: New User register
  Scenario: Login letter
  	Given I am on the index page of signup
  	When I am on the signup page1
  	Then I should see the login letter to go login page r
  	
  Scenario: Signup letter
  	Given I am on the index page of signup
  	When I am on the signup page2
  	Then I should see the signup letter to go signup page r
  	
   Scenario: Username block
  	Given I am on the index page of signup
  	When I am on the signup page3
  	Then I should see the username block r
  	
  Scenario: Password block
  	Given I am on the index page of signup
  	When I am on the signup page4
  	Then I should see the password block r
  	
  Scenario: Retype Password block
  	Given I am on the index page of signup
  	When I am on the signup page5
  	Then I should see the retype password block r
  	
  Scenario: Create user button
  	Given I am on the index page of signup
  	When I am on the signup page6
  	Then I should see the create user button 
  	
  Scenario: User types in invalid username in registration
    Given I am on the index page of signup
    When I typed in 'he' in the Username field in registration form
    And I click the signup button
    Then I should see the border color of Username is red in registration form
    And I should see error message 'Username can only contain alphanumeric characters and longer than 5 characters.' in registration form
    
  Scenario: User types in valid username but no password in registration
    Given I am on the index page of signup
    When I typed in 'hello' in the Username field in registration form
    And I click the signup button
    Then I should see the border color of Username is not red in registration form
    And I should see error message 'Password should contain uppercase, lowercase and numeric character.' in registration form

  Scenario: User types in different passwords
    Given I am on the index page of signup
    When I typed in 'hello' in the Username field in registration form
    And I typed in '12345Qa' in the Password field in registration form
    And I typed in '12345Q' in the Retype Password field in registration form
    And I click the signup button
    Then I should see error message 'Passwords did not match.' in registration form
      
  Scenario: User types in all valid inputs and direct to homepage
    Given I am on the index page of signup
    When I typed in 'ttrojan' in the Username field in registration form
    And I typed in '12345Qa' in the Password field in registration form
    And I typed in '12345Qa' in the Retype Password field in registration form
    And I click the signup button
    Then I should see the homepage differnt from signup page
    
  Scenario: Register existing user would fail
    Given I am on the index page of signup with existing user account
    When I typed in 'trojan' in the Username field in registration form
    And I typed in '12345Qa' in the Password field in registration form
    And I typed in '12345Qa' in the Retype Password field in registration form
    And I click the signup button
    Then I should see error message 'User already exists.' in registration form
 
