Feature: Testing URL Navigation

  Scenario: Verify URL Navigation
    Given I open Chrome browser
    When I navigate to "http://iqhqsolutionsdev.ddns.net:8082/"
    Then the current URL should be "http://iqhqsolutionsdev.ddns.net:8082/#/index"
