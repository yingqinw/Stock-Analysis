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
	
		
	