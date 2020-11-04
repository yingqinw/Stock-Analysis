Feature: Upload csv file	
  Scenario: Upload file button
    Given I am on index page ucf
	When I login and am on the Hompage ucf
	Then I should see the upload file button
		
  Scenario: Upload file clickable 
    Given I am on index page ucf
	When I login and am on the Hompage ucf
	Then I should be able to click the clickable upload file button
	
  Scenario: Upload file pop up window
    Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
	Then I should be on the upload file pop up window
	
  Scenario: Upload file here part
  	Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
  	Then I should see the upload file here part
  	
  Scenario: Upload file here part clickable 
  	Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
  	Then I should be able to click the clickable choose file 
 
  Scenario: Upload file button in upload file
    Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button 
	Then I should see the upload file button in upload file
	
  Scenario: Upload file button clickable in upload file
    Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
    Then I should be able to click the clickable upload file button in upload file
    
  Scenario: Cancel button in upload file in upload file
  	Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
    Then I should see the cancel button in upload file 
    
  Scenario: Cancel button clickable in upload file 
  	Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
    Then I should be able to click the clickable cancel button in upload file 
   
  Scenario: Cross button in upload file
  	Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
    Then I should be able to click the clickable cross button in upload file
    
   Scenario: Upload a file 
  	Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
	And I upload a file 
    Then I should be able to see the file is uploaded
    
  Scenario: Upload a file but cancel the actual upload
  	Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
	And I upload a file 
	And I click the cancel button in upload file 
    Then there should not the list that I add
    
  Scenario: Upload a file and click the upload file button
  	Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
	And I upload a file 
	And I click the upload file button in upload file 
    Then I should see the stocks on the portfolio 
    
  Scenario: Upload a file with unvaild info and click the upload file button
  	Given I am on index page ucf
	When I login and am on the Hompage ucf and click the upload file button
	And I upload a file with unvalid info
	And I click the upload file button in upload file 
    Then I should see the error messages containing the error lines
