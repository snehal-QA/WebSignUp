@E2ETest @validDetails
Feature: E2E tests for Signup Page

  Background: Miro SignUp page is open
    Given user navigates to miro sign up page
    Then miro sign up page is loaded in browser

  
  Scenario: User successfully navigates to the EmailConfirmation Page on entering all valid Sign-up details
    When user enters "Name" in name textbox
    And user enters "WorkEmail" in workemail textbox
    And user enters "Password" in password textbox
    And user selects "PolicyAgreement" checkbox
    And user selects "News&UpdateAgreement" checkbox
    And user clicks "GetStartedNow" button
    Then user is navigated to Email Confirmation page
    And Email confirmation message contains "Check your email"
    And Email confirmation message contains my "WorkEmail"
    And Email confirmation message contains textbox to enter 6 digit code

  Scenario: User successfully navigates to the EmailConfirmation Page on entering all mandatory Sign-up details (News&UpdateAgreement-not selected)
    When user enters "Name" in name textbox
    And user enters "WorkEmail" in workemail textbox
    And user enters "Password" in password textbox
    And user selects "PolicyAgreement" checkbox
    And user clicks "GetStartedNow" button
    Then user is navigated to Email Confirmation page
    And Email confirmation message contains "Check your email"
    And Email confirmation message contains my "WorkEmail"
    And Email confirmation message contains textbox to enter 6 digit code