Feature: Homepage mobile
  Scenario: Homepage Title
  	Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should see the title called USC CS310 Stock Portfolio Management hpm
	
  Scenario: Click Homepage Title
    Given I am on the Hompage h hpm
	When I click the title hpm
	Then I should be on the hompage again hpm
	
  Scenario: Banner color
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should see the banner color as grey hpm
	
  Scenario: Tickers text
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should see the Tickers text hpm
	
  Scenario: Last Price text
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should see the Last Price text hpm
	
  Scenario: Action text
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should see the Action text hpm
	
  Scenario: View stock button 
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should see the view stock button hpm 
	
  Scenario: Remove stock from graph button
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should see the remove stock from graph button hpm
	
  Scenario: Select dates button
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should see the select dates button hpm
	
  Scenario: View stock button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should be able to click the clickable view stock button hpm
	
  Scenario: Remove stock from graph button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should be able to click the clickable remove stock from graph button hpm
	
  Scenario: Select dates button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should be able to click the clickable select dates button hpm
	
  Scenario: View stock arrow button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	And I click the view stock button hpm
	Then I should be able to click the clickable view stock button2 hpm
  
  Scenario: Cancel stock button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	And I click the view stock button hpm
	Then I should be able to click the clickable cancel button in view stock hpm
	
  Scenario: View stock cross button
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	And I click the view stock button hpm
	Then I should be able to click the clickable cross button in view stock hpm
	
  Scenario: Remove stock from graph button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should be able to click the clickable remove stock from graph button hpm
  
  Scenario: Cancel stock button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	And I click the remove stock from graph button hpm
	Then I should be able to click the clickable remove stock button in remove stock hpm
	
  Scenario: View stock cross button
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	And I click the remove stock from graph button hpm
	Then I should be able to click the clickable cross button in remove stock hpm
	
  Scenario: Select dates button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should be able to click the clickable select dates button hpm
  
  Scenario: Confirm dates button clickable in select dates
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	And I click the select dates button hpm
	Then I should be able to click the clickable Confirm dates button hpm
	
  Scenario: Select dates cross button
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	And I click the select dates button hpm
	Then I should be able to click the clickable cross button in select dates hpm
	
  Scenario: Add stock button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should be able to click the clickable add stock button hpm
	
  Scenario: Add stock button in add stock clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	And I click the add stock button hpm
	Then I should be able to click the clickable add stock button in add stock hpm

  Scenario: Add stock cross button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	And I click the add stock button hpm
	Then I should be able to click the clickable cross button in add stock hpm
	
  Scenario: Logout button clickable
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should be able to click the clickable logout button hpm

  Scenario: Default S&P graph 
    Given I am on index page h hpm
	When I login and am on the Hompage h hpm
	Then I should be able to see the S&P 500 on the grap hpm	
	
