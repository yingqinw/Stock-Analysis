Feature: User delete stocks
  Scenario: Delete letter
	Given in mainpage and logged in
	When I add a stock
	Then I should see the delete button
	
  Scenario: Confirm pop up window for delete 
	Given in mainpage, logged in, and add a stock
	When I click the delete button
	Then I should see the text Do you want to delete ticker AMZN ?
	
  Scenario: Yes button 
	Given in mainpage, logged in, and add a stock
	When I click the delete button
	Then I should the yes button

  Scenario: No button 
	Given in mainpage, logged in, and add a stock
	When I click the delete button
	Then I should the no button
	
  Scenario: Do not delete the stock
	Given in mainpage, logged in, and add a stock
	When I click the delete button
	And I click the no button
	Then there is still the stock that I added
	
  Scenario: Delete the stock
	Given in mainpage, logged in, and add a stock2
	And I click the delete button for the first stock
	And I click the yes button
	Then there should be gone that I intend to delete
