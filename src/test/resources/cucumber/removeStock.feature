Feature: User delete stocks
  Scenario: delete pop up window
	Given in mainpage and add a stock
	When I click the delete button
	Then the stock will be delete from the list
