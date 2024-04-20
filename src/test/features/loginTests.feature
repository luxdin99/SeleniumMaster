Feature: As a user
  I should be able to login with only valid username and password

  Background: Login
    Given I am on homepage "https://www.saucedemo.com"
@login
  Scenario: Verify login with valid username and password

    When I do Login with username "standard_user" and password "secret_sauce"
    Then I should land on products page

@login
  Scenario: Verify login with invalid username and password
    When I do Login with invalid username "standard" and password "secret"
    Then I should get error message "Epic sadface: Username and password do not match any user in this service"

@login
  Scenario: Verify login with no data

    When I do Login with no username and password entered
    Then I should get error message "Epic sadface: Username is required"
