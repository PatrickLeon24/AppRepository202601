
Feature: Category Service Integration Tests

  Scenario: Save a new category
    Given a new category with name "Deportes"
    Then the category should be saved with name "Deportes"

  Scenario: Update an existing category
    Given a new category with name "Deportes"
    When I save the category
    Then the category should be saved with name "Deportes"
    When I update the category name to "Deportes Actualizado"
    Then the category should be updated with the name "Deportes Actualizado"

  Scenario: Read all categories
    Given there are categories in the system
    Then I should see a non-empty list of categories

  Scenario: Delete an existing category
    Given a new category with name "Deportes Actualizado"
    When I save the category
    Then the category should be saved with name "Deportes Actualizado"
    When I delete the category
    Then the category should no longer exist in the system