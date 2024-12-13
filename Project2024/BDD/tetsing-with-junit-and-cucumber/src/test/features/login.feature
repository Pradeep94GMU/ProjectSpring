Feature: User Login

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When they enter valid credentials
    Then they are redirected to their dashboard

  Scenario: Failed login with invalid credentials
    Given the user is on the login page
    When they enter invalid credentials
    Then an error message is displayed
