Feature: Add and Remove Unavailability
  Scenario: Add Unavailability
    Given I am logged in
    And I am on the 'Home' page
    And I add unavailability
    Then I can see the unavailability event on the calendar
  Scenario: Remove unavailability
    Given I am logged in
    And I am on the 'Home' page
    And I add unavailability
    And I click the created unavailability event on the calendar
    Then I can no longer see the unavailability event on the calendar