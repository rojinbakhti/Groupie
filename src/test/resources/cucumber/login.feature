Feature: Login
  Scenario: Login Page shows up
    Given I am on the 'Login' page
    Then I should see Login Header
  Scenario: Login with correct username and password
    Given I am on the 'Login' page
    And I type in username textbox 'testUsername'
    And I type in password textbox 'testPassword'
    And I press the button Login
    Then I should be directed to Home page
  Scenario: Login with incorrect username and password
    Given I am on the 'Login' page
    And I type in username textbox 'wrongUsername'
    And I type in password textbox 'wrongPassword'
    And I press the button Login
    Then I should see error notification that says 'Invalid Username/Password'
  Scenario: Click Sign Up link
    Given I am on the 'Login' page
    And I click the link 'Sign Up'
    Then I should see Sign Up Header