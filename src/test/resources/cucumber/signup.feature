Feature: Sign Up
  Scenario: Sign Up Page shows up
    Given I am on the 'Sign Up' page
    Then I should see Sign Up Header
  Scenario: Sign Up with new username and matching password
    Given I am on the 'Sign Up' page
    And I type in username textbox 'testUsernameSignup'
    And I type in password textbox 'testPasswordSignup'
    And I type in Confirm password textbox 'testPasswordSignup'
    And I press the button Create Account
    Then I should be directed to Home page
  Scenario: Sign Up with non matching password
    Given I am on the 'Sign Up' page
    And I type in username textbox 'testUsernameSignup'
    And I type in password textbox 'testPasswordSignup'
    And I type in Confirm password textbox 'wrongPassword'
    And I press the button Create Account
    Then I should see error notification that says 'Passwords do not match'
  Scenario: Sign Up with already existing username
    Given I am on the 'Sign Up' page
    And I type in username textbox 'testUsernameSignup'
    And I type in password textbox 'testPasswordSignup'
    And I type in Confirm password textbox 'testPasswordSignup'
    And I press the button Create Account
    Then I should see error notification that says 'Username already exists'
  Scenario: Click Cancel button
    Given I am on the 'Sign Up' page
    And I click the link 'Cancel'
    Then I should see Login Header
  
