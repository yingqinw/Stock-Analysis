Feature: User remove stocks
 
  Scenario: remove stock
    Given in mainpage, logged in and added a stock
    When click Delete Bottom
    Then the stock remove from the list