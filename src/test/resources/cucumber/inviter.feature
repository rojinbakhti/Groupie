Feature: Inviters are able to see PGD details
  Scenario: Inviter sees PGD responses
    Given I am logged in 
    And I click on the activity feed
    And I click on an unfinalized event
    Then I should see responses to the PGD
  Scenario: Inviter sees system picked event
    Given I am logged in 
    And I click on the activity feed
    And I click on an unfinalized event
    Then I should see system picked event
  Scenario: Inviter picks an event from the drop down
    Given I am logged in 
    And I click on the activity feed
    And I click on an unfinalized event
    Then I should see events in the drop down
  Scenario: Inviter closes and submits an event
    Given I am logged in 
    And I click on the activity feed
    And I click on an unfinalized event
    And I click on page
    And I click on submit
    Then I should be directed to Home page  