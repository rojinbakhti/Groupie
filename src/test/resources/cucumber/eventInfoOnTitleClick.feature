Feature: Clicking event title gives more info
  Scenario: Click on event title for more info
    Given I am logged in
    And I am on the 'Propose' page
    And I type in a title
    And I type in a keyword
    And I enter a time
    And I click the event name for the first event
    Then I should see a popup with more info