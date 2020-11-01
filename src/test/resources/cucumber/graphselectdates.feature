Feature: User logout	
  Scenario: Select Dates button
    Given I am on index page gd
	When I login and am on the Hompage gd
	Then I should see the select dates from graph button

  Scenario: Select dates to graph
    Given I login and am on the Hompage gd
	When I click select stock to graph button
	Then I get the pop up window for select dates
	
  Scenario: Start Date block
    Given I login and am on the Hompage gd
	When I click select stock to graph button
	Then I should see the start date block gd
	
  Scenario: End Date block
    Given I login and am on the Hompage gd
	When I click select stock to graph button
	Then I should see the end date block gd
	
  Scenario: Confirm dates button
  	Given I login and am on the Hompage gd
	When I click select stock to graph button
  	Then I should see the confirm dates button
 
  Scenario: I put the end date that is before start date ticker
    Given in Homepage and in select dates pop up window
	When I write the wrong end date 
	Then I should see the error message for end date gd
	
  Scenario: I add the stock 
    Given in Homepage and in view stock to graph pop up window 
	When I add a stock on the graph to view gsd
	Then I should see the 3 months dates as default
	
  Scenario: I specify the dates
    Given in Homepage and in view stock to graph pop up window 
	When I add a stock on the graph to view gsd
	And I click the select dates button gsd
	And I specify the dates from 9/10/2020 to 10/10/2020
	Then I should see the graph for 1 month
	
	
	
		
	
