Feature: Settings
  Scenario: Settings Page shows up
    Given I am logged in
    And I am on the 'Settings' page
    Then I should see Settings Header
  Scenario: Block user
    Given I am logged in
    And I am on the 'Settings' page
    And I type in 'aravadani'
    And I click Block
    Then I should see blocked user '@aravadani'
  Scenario: Unblock user
    Given I am logged in
    And I am on the 'Settings' page
    And next to '@aravadani', I click Unblock
    Then I should see no users are blocked