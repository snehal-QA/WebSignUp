@E2ETest @InvalidDetails
Feature: Negative E2E tests for Signup Page

  Background: Miro SignUp page is open
    Given user navigates to miro sign up page
    And miro sign up page is loaded in browser

  @MissingMandotoryDetails
  Scenario Outline: User is not registered on missing mandatory sign up detail/(s): <Missingdetail/(s)>
    When user enters "<Name>" in name textbox
    And user enters "<WorkEmail>" in workemail textbox
    And user enters "<Password>" in password textbox
    And user selects "<PolicyAgreement>" checkbox
    And user clicks "GetStartedNow" button
    Then user gets error message for missing details "<Name>","<WorkEmail>","<Password>","<PolicyAgreement>"
    And user is not navigated to confirmation page
    And user is still on signup page

    Examples: 
      | Name | WorkEmail | Password | PolicyAgreement | Missingdetail/(s)                       |
      |      | WorkEmail | Password | PolicyAgreement | Name                                    |
      | Name |           | Password | PolicyAgreement | WorkEmail                               |
      | Name | WorkEmail |          | PolicyAgreement | Password                                |
      | Name | WorkEmail | Password |                 | PolicyAgreement                         |
      |      |           |          |                 | Name,WorkEmail,Password,PolicyAgreement |
      | Name | WorkEmail |          |                 | Password,PolicyAgreement                |
      |      |           |          | PolicyAgreement | Name,WorkEmail,Password                 |

  @IncorrectWorkEmail
  Scenario Outline: User is not registered with incorrect WorkEmail:<WorkEmail>.WorkEmail is <EmailType>
    When user enters "Name" in name textbox
    And user enters "<WorkEmail>" in workemail textbox
    And user enters "<Password>" in password textbox
    And user selects "PolicyAgreement" checkbox
    And user clicks "GetStartedNow" button
    Then user gets error message for incorrect details "WorkEmail" "<WorkEmail>"
    And user is not navigated to confirmation page
    And user is still on signup page

    Examples: 
      | WorkEmail | EmailType          |
      | test      | missing @ & Domain |
      | test@     | missing Domain     |
      | test@miro | missing .com       |
      | test.com  | missing @          |

  @InvalidPassword
  Scenario Outline: User is not registered with Password:<Password> <PasswordType> 
    When user enters "Name" in name textbox
    And user enters "WorkEmail" in workemail textbox
    And user enters "<Password>" in password textbox
    And user selects "PolicyAgreement" checkbox
    And user clicks "GetStartedNow" button
    Then user gets error message for incorrect details "password" "<Password>"
    And user is not navigated to confirmation page
    And user is still on signup page

    Examples: 
      | Password | PasswordType           |
      | test     | less than 8 characters |
      | test@128 | equal to 8 characters  |

  @RegisterWithExistingWorkEmail
  Scenario: User is not registered with already registered email address
    Given user already sign up successfully with workemail "WorkEmail"
    And user navigates to miro sign up page
    When user enters "Name" in name textbox
    And user enters existing "WorkEmail" in workemail textbox
    And user enters "Password" in password textbox
    And user selects "PolicyAgreement" checkbox
    And user clicks "GetStartedNow" button
    Then user gets error message for existing email
