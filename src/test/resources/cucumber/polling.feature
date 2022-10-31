Feature: Polling
  Scenario: Save Draft
    Given I am logged in as choco
    And I click on the activity feed
    And I click on the first event
    And I reload the page
    And I click Save Draft button
    And I reload the page
    And I click on the activity feed
    And I click on the first event
    And I reload the page
    Then the excitement should be 3
  Scenario: Click Submit
    Given I am logged in as choco
    And I click on the activity feed
    And I click on the first event
    And I reload the page
    And I click Submit button
    Then I should see notification that says 'Your response has been recorded!'
  