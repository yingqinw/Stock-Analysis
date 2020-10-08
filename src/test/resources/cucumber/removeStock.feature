Feature: User remove stocks
  Scenario: delete bottom, check for logged in
    Given in mainpage and logged in
		When I click delete bottom
		Then I get the pop up window to delete stock

  Scenario: delete pop up window
    Given in mainpage and in delete pop up window
		When I fill in a wrong ticker
		Then I can’t successfully delete stock

  Scenario: delete pop up window
    Given in mainpage and in delete pop up window2
    When I fill in a wrong quantity
    Then I can’t successfully delete stock2
 
  Scenario: delete pop up window
    Given in mainpage and in delete pop up window3
    When I doesn't fill the start date
    Then I can’t successfully delete stock3

  Scenario: delete pop up window
    Given in mainpage and in delete pop up window4
    When I fill the invalid start date
    Then I can’t successfully delete stock4