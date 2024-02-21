Feature: SwitchTabs Before Login Steps

Scenario: Tabs should redirect to login page
    Given User is on the application's main page
    When User switches between different tabs
      | Dashboard    |
      | Wallets    |
      | Insight    |
      | Strategies |
     
