Feature: 3 Failed logins blocks user for 60 seconds
  Scenario: User fails login 3 times
    Given I am on the 'Login' page
    And I type in username textbox 'testUsername'
    And I type in password textbox 'wrongPassword'
    And I press the button Login
    And I press the button Login
    And I press the button Login
    Then I should be locked out for 60 seconds
  