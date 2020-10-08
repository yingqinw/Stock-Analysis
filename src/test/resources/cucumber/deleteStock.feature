Feature: User delete stocks
  Scenario: delete bottom, check for is portfolio empty
	Given in mainpage and logged in and have no stock in portfolio
	When I click delete bottom
	Then I don’t get the pop up window to delete stock

  Scenario: delete pop up window
	Given in mainpage and in delete pop up window
	When I fill in a correct ticker which I don’t have in portfolio
	Then pop up window closes, nothing happens

  Scenario: delete pop up window
	Given in mainpage and in delete pop up window
	When I fill in a date that is a trading date but prior to the purchase date
	Then pop up window closes, nothing happens

  Scenario: delete pop up window
	Given in mainpage and in delete pop up window
	When I fill in a date that is a trading date after the purchase date and a ticker that its stock I have in portfolio
	Then pop up window closes, ticker disappear on the portfolio list, portfolio value updated

