Feature: User delete stocks
  Scenario: Delete letter
	Given in mainpage and logged in
	When I add a stock
	Then I should see the delete button
	
  Scenario: Delete letter button clickable
  	Given in mainpage and logged in
  	When I add a stock
  	Then I should be able to click the clickable delete button
  	
  Scenario: Confirm pop up window for delete 
	Given in mainpage, logged in, and add a stock
	When I click the delete button
	Then I should see the text Do you want to delete ticker AMZN ?
	
  Scenario: Delete stock button 
	Given in mainpage, logged in, and add a stock
	When I click the delete button
	Then I should the delete stock button

  Scenario: Cancel button 
	Given in mainpage, logged in, and add a stock
	When I click the delete button
	Then I should the cancel button

  Scenario: Delete stock button clickable
  	Given in mainpage, logged in, and add a stock
	When I click the delete button
  	Then I should be able to click the clickable delete stock button
  	
  Scenario: Cancel button clickable
  	Given in mainpage, logged in, and add a stock
	When I click the delete button
  	Then I should be able to click the clickable cancel button		
	
  Scenario: Do not delete the stock
	Given in mainpage, logged in, and add a stock
	When I click the delete button
	And I click the no button
	Then there is still the stock that I added
	
  Scenario: Delete the stock
	Given in mainpage, logged in, and add a stock
	And I click the delete button for the stock and click the yes button
	Then there should be gone that I intend to delete
