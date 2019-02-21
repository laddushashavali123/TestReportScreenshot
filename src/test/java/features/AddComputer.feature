 @Testing
Feature: Add a computer feature functionality

  Scenario: Validate Add a computer page contents
    Given I open web page
    And I click on 'Add a new computer'
    Then I should see 'Add a new computer' page contents

  Scenario Outline: Validate Add a Computer functionality
    Given I open web page
    And I click on 'Add a new computer'
    When I enter the computer name as "<computerName>"
    When I enter the Introduced date as "<introducedDate>"
    When I enter the Discontinued date as "<discontinuedDate>"
    When I select the company as "<companyName>"
    And I click on 'Create this computer'
    Then I should see the computer added successfully as "<computerName>"
    Examples:

      | computerName | introducedDate | discontinuedDate | companyName |
      | Test1        | 2019-02-14     | 2019-05-14       | Netronics   |
      | Test2        | 2019-02-11     | 2019-05-15       | RCA         |

  Scenario Outline: Validate cancel functionality in Add a Computer page
    Given I open web page
    And I click on 'Add a new computer'
    When I enter the computer name as "<computerName>"
    When I enter the Introduced date as "<introducedDate>"
    When I enter the Discontinued date as "<discontinuedDate>"
    And I click on cancel button
    Then I should see homepage
    Examples:

      | computerName | introducedDate | discontinuedDate |
      | Test1        | 2019-02-14     | 2019-05-14       |


  Scenario Outline: Validate error message is displayed when wrong details are entered in Add a Computer page
    Given I open web page
    And I click on 'Add a new computer'
    When I enter the computer name as "<computerName>"
    When I enter the Introduced date as "<introducedDate>"
    And I click on 'Create this computer'
    Then I should be in Add a computer page
    Examples:

      | computerName | introducedDate |
      | Test1        | invalidate     |


  Scenario Outline: Validate computer gets added only with Computer Name
    Given I open web page
    And I click on 'Add a new computer'
    When I enter the computer name as "<computerName>"
    And I click on 'Create this computer'
    Then I should see the computer added successfully as "<computerName>"
    Examples:

      | computerName        |
      | TestingOnlyWithName |