Feature: LoginFeature
  This feature deals with login functionality of the app

  Scenario: Login with correct credentials
    Given I navigate to login page
    And I enter the following details
      | email                | password  |
      | baltachtan@yahoo.com | zefir1990 |
    And I click login button
    Then I should see the userform page
