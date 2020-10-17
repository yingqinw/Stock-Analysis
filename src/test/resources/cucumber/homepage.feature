Feature: User logout
  Scenario: Homepage Title
  	Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the title called USC CS310 Stock Portfolio Management
	
  Scenario: Click Homepage Title
    Given I am on the Hompage h
	When I click the title 
	Then I should be on the hompage again
	
  Scenario: Banner color
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the banner color as grey
	
  Scenario: Tickers text
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the Tickers text
	
  Scenario: Last Price text
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the Last Price text
	
  Scenario: Action text
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the Action text
	
  Scenario: Add stock to graph button 
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the add stock to graph button 
	
  Scenario: Remove stock from graph button
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the remove stock from graph button
	
  Scenario: Select dates button
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the select dates button
		
	