Feature: Toggle button for stock portfolio 
  Scenario: Option letter  
	Given I am on the index page ts
	When I login and am on the hompage ts
	Then I should see the option tab 
	
  Scenario: Toggle clickable
  	Given I am on the index page ts
	When I login and am on the hompage ts
	And I add a stock for portfolio 
	Then I should be able to click the clickable toggle button
  	
  Scenario: Select all button
	Given I am on the index page ts
	When I login and am on the hompage ts
	Then I should see the select all button 
	
  Scenario: Select all button clickable
	Given I am on the index page ts
	When I login and am on the hompage ts
	Then I should be able to click the clickable select all button

  Scenario: Unselect all button
	Given I am on the index page ts
	When I login and am on the hompage ts
	Then I should see the unselect all button

  Scenario: Unselect all button clickable
	Given I am on the index page ts
	When I login and am on the hompage ts
	Then I should be able to click the clickable unselect all button
  	
  Scenario: Add one stock and click the toggle
	Given I am on the hompage 
	When I click the toggle 
	Then there should be no graph for the stock		

  Scenario: Unselect all for stocks
	Given I am on the hompage  
	When I click the unselect all button 
	Then there should be no graph for the stock	
	
  Scenario: Select all for stocks
	Given I am on the hompage 
	When I click the select all button 
	Then there should the graph for portfolio
	
  Scenario: Add two stock and click the one toggle
	Given I am on the hompage 
	When I add a stock for portfolio2
	And I click the toggle for the first stock
	Then there should be the graph for only one stock
	
  
 
