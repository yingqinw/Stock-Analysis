Feature: New User register
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
    
  Scenario: User types in invalid email in registration
    Given I am on the index page of signup
    When I typed in 'trojan' in the Email field in registration form
    And I click the signup button
    Then I should see error message 'Email address is not valid.' in registration form

  Scenario: User types in different passwords
    Given I am on the index page of signup
    When I typed in 'hello' in the Username field in registration form
    And I typed in '12345Qa' in the Password field in registration form
    And I typed in '12345Q' in the Retype Password field in registration form
    And I typed in 'trojan@usc.edu' in the Email field in registration form
    And I click the signup button
    Then I should see error message 'Passwords did not match.' in registration form
      
  Scenario: User types in all valid inputs and direct to homepage
    Given I am on the index page of signup
    When I typed in 'ttrojan' in the Username field in registration form
    And I typed in '12345Qa' in the Password field in registration form
    And I typed in '12345Qa' in the Retype Password field in registration form
    And I typed in 'trojan@usc.edu' in the Email field in registration form
    And I click the signup button
    Then I should see the homepage differnt from signup page
    
  Scenario: Register existing user would fail
    Given I am on the index page of signup with existing user account
    When I typed in 'trojan' in the Username field in registration form
    And I typed in '12345Qa' in the Password field in registration form
    And I typed in '12345Qa' in the Retype Password field in registration form
    And I typed in 'trojan@usc.edu' in the Email field in registration form
    And I click the signup button
    Then I should see error message 'User already exists.' in registration form
 