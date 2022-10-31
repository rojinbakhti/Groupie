Feature: User is logged out after 60 seconds of inactivity
  Scenario: User is logged out
    Given I am logged in
    And I am on the 'Settings' page
    And I am inactive for 60 seconds
    Then I should be logged out
  Scenario: User is not logged out
    Given I am logged in
    And I am on the 'Home' page
    And I am inactive for 45 seconds
    And I go to the Settings Page
    And I am inactive for 40 seconds
    Then I should still be on 'Settings' Page