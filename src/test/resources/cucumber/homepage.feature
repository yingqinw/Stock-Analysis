Feature: Homepage
  Scenario: Refresh homepage
  	Given I am on index page h
	When I login and am on the Hompage h 
	And I refresh the site
	Then I should see the title called USC CS310 Stock Portfolio Management
	
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
	
  Scenario: View stock button 
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the view stock button 
	
  Scenario: Remove stock from graph button
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the remove stock from graph button
	
  Scenario: Select dates button
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should see the select dates button
	
  Scenario: View stock button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should be able to click the clickable view stock button 
	
  Scenario: Remove stock from graph button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should be able to click the clickable remove stock from graph button
	
  Scenario: Select dates button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should be able to click the clickable select dates button
	
  Scenario: View stock arrow button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	And I click the view stock button
	Then I should be able to click the clickable view stock button2
  
  Scenario: Cancel stock button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	And I click the view stock button
	Then I should be able to click the clickable cancel button in view stock
	
  Scenario: View stock cross button
    Given I am on index page h
	When I login and am on the Hompage h
	And I click the view stock button
	Then I should be able to click the clickable cross button in view stock
	
  Scenario: Remove stock from graph button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should be able to click the clickable remove stock from graph button
  
  Scenario: Cancel stock button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	And I click the remove stock from graph button
	Then I should be able to click the clickable remove stock button in remove stock
	
  Scenario: View stock cross button
    Given I am on index page h
	When I login and am on the Hompage h
	And I click the remove stock from graph button
	Then I should be able to click the clickable cross button in remove stock
	
  Scenario: Select dates button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should be able to click the clickable select dates button
  
  Scenario: Confirm dates button clickable in select dates
    Given I am on index page h
	When I login and am on the Hompage h
	And I click the select dates button
	Then I should be able to click the clickable Confirm dates button
	
  Scenario: Select dates cross button
    Given I am on index page h
	When I login and am on the Hompage h
	And I click the select dates button
	Then I should be able to click the clickable cross button in select dates
	
  Scenario: Add stock button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should be able to click the clickable add stock button
	
  Scenario: Add stock button in add stock clickable
    Given I am on index page h
	When I login and am on the Hompage h
	And I click the add stock button
	Then I should be able to click the clickable add stock button in add stock 

  Scenario: Add stock cross button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	And I click the add stock button
	Then I should be able to click the clickable cross button in add stock
	
  Scenario: Logout button clickable
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should be able to click the clickable logout button

  Scenario: Default S&P graph 
    Given I am on index page h
	When I login and am on the Hompage h
	Then I should be able to see the S&P 500 on the graph
