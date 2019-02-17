Feature: Search feature functionality

  Scenario: Validate Search page contents
    Given I open web page
    Then I should see 'Search' page
    And I should validate the 'Search' Page contents


  Scenario Outline: Perform search for a computer name and validate the search content
    Given I open web page
    And I search for a "<computerName>"
    Then I should see the search results with "<computerName>" and "<introducedDate>" and "<discontinuedDate>" and "<companyName>"
    Examples:

      | computerName     | introducedDate | discontinuedDate | companyName |
      | WonderlaEfteling | -              | -                | -           |

  Scenario Outline: Perform search for a computer name and open the search content
    Given I open web page
    And I search for a "<computerName>"
    Then I should see the search results as "<computerName>"
    Then I click on search result as "<computerName>"
    Then I should validate the computer contents as "<computerName>" and "<introducedDate>" and "<discontinuedDate>" and "<companyName>"
    Examples:

      | computerName      | introducedDate | discontinuedDate | companyName |
      | cucumberreporting | 2019-02-14     | 2019-05-14       | Netronics   |