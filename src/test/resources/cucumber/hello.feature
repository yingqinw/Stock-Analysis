Feature: Hello
  Scenario: Open hello page from index page
    Given I am on the index page
    When I click the link 'Click me'
    Then I should see header 'Hello world!'
    
  Scenario: Open other page from index page
    Given I am on the index page
    When I click the link 'Or click me'
    Then I should see text 'Hello servlet'