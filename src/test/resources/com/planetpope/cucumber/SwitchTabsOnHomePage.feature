Feature: Switch Tabs after Login

  Scenario: Switching between tabs after login
    Given I open the Application_URL in Chrome  
    And I click on login_tab
    And I enter a valid email_address "crypto88@gmail.com"
    And I enter a valid_password "crypto88"
    And I click on Login_button
    And I wait 5_seconds
  
    And User switches between_different_tabs
      | Tab Name   | Expected URL                                                    |
      | Wallets    | http://iqhqsolutionsdev.ddns.net:8082/#/wallet                  |
      | Insight    | http://iqhqsolutionsdev.ddns.net:8082/#/insights                |
      | Strategies | http://iqhqsolutionsdev.ddns.net:8082/#/strategy-recommendations|
    And User closes the application
