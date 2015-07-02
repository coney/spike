Feature: Search
  Search Something on Baidu

  Scenario: Search Something On Baidu
    Given I am on the Baidu search page
    When I search for "Huawei"
    Then I should see "华为"