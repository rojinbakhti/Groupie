Feature: Propose Group Date
  Scenario: Valid Group date Proposal
    Given I am logged in
    And I am on the 'Propose' page
    And I type in a title
    And I type in a keyword
    And I enter a time
    And I add some events
    And I click next
    And I invite a valid user
    And I click send
    Then I should be directed to Home page

  Scenario: Blocked User Group date Proposal
    Given I am logged in as alt user
    And I am on the 'Propose' page
    And I type in a title
    And I type in a keyword
    And I enter a time
    And I add some events
    And I click next
    And I invite a blocked user
    Then I should see a notification saying the user is unavailable

  Scenario: Unavailable User Group date Proposal
    Given I am logged in as alt user
    And I am on the 'Propose' page
    And I type in a title
    And I type in a keyword
    And I enter a time
    And I add some events
    And I click next
    And I invite an unavailable user
    Then I should see a notification saying the user is unavailable