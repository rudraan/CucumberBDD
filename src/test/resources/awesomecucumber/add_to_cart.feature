Feature: Add to cart

#  Scenario: Add one quantity from Store
#    Given I'm on the Store Page
#    When I add a "Blue Shoes" to the cart
#    Then I should see 1 "Blue Shoes" in the cart

#  for parallel execution we are using this
  Scenario Outline: Add one quantity from Store - examples
    Given I'm on the Store Page
    When I add a "<product_name>" to the cart
    Then I should see 1 "<product_name>" in the cart
    Examples:
      | product_name    |
      | Anchor Bracelet |
      | Black Over-the-shoulder Handbag |