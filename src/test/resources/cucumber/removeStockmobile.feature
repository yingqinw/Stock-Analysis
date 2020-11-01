Feature: User delete stocks
  Scenario: Delete letter
	Given in mainpage and logged in rmsm
	When I add a stock rmsm
	Then I should see the delete button rmsm
	
  Scenario: Delete letter button clickable
  	Given in mainpage and logged in rmsm
  	When I add a stock rmsm
  	Then I should be able to click the clickable delete button rmsm
  	
  Scenario: Confirm pop up window for delete 
	Given in mainpage, logged in, and add a stock rmsm
	When I click the delete button rmsm
	Then I should see the text Do you want to delete ticker AMZN ? rmsm
	
  Scenario: Delete stock button 
	Given in mainpage, logged in, and add a stock rmsm
	When I click the delete button rmsm
	Then I should the delete stock button rmsm

  Scenario: Cancel button 
	Given in mainpage, logged in, and add a stock rmsm
	When I click the delete button rmsm
	Then I should the cancel button rmsm

  Scenario: Delete stock button clickable
  	Given in mainpage, logged in, and add a stock rmsm
	When I click the delete button rmsm
  	Then I should be able to click the clickable delete stock button rmsm
  	
  Scenario: Cancel button clickable
  	Given in mainpage, logged in, and add a stock rmsm
	When I click the delete button rmsm
  	Then I should be able to click the clickable cancel button rmsm	 	
	
  Scenario: Do not delete the stock
	Given in mainpage, logged in, and add a stock rmsm
	When I click the delete button rmsm
	And I click the no button rmsm
	Then there is still the stock that I added rmsm
	
  Scenario: Delete the stock
	Given in mainpage, logged in, and add a stock rmsm
	And I click the delete button for the stock and click the yes button rmsm
	Then there should be gone that I intend to delete rmsm 
