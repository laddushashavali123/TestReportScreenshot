Feature: Validate update and Delete computer functionality

  Scenario Outline: Perform search for a computer name and open the search content
    Given I open web page
    Then I click on search result
    Then I should see edit computer contents
    When I update the computer details as "<computerName>"
    Then I should see the details getting saved as "<computerName>"
    Examples:

      | computerName        |
      | cucumberreporting11 |

  Scenario Outline: Perform search for a computer name and Delete the computer
    Given I open web page
    Then I click on search result
    Then I should see edit computer contents
    Then I get the computername
    Then I click on Delete the computer button
    Then I should see the details getting deleted as "<computerName>"
    Examples:

      | computerName |
      | Test1        |