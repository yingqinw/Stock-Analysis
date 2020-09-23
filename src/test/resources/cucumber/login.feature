Feature: User login
  Scenario: User types in invalid username
    Given I am on the index page
    When I typed in 'he' in the Username field
    And I click the login button
    Then I should see the border color of Username is red
    And I should see error message 'Username can only contain alphanumeric characters and longer than 5 characters.'
    
  Scenario: User types in valid username but no password
    Given I am on the index page
    When I typed in 'hello' in the Username field
    And I click the login button
    Then I should see error message 'Password should contain uppercase, lowercase and numeric character.'
    
   Scenario: User types in valid username and password without register
    Given I am on the index page
    When I typed in 'hellothere' in the Username field
    And I typed in '12345Qa' in the Password field
    And I click the login button
    Then I should see error message 'Incorrect username or password. Please try again! :)'