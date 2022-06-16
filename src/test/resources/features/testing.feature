Feature: test feature

Scenario: Search in Emag

Given Login into emag
And Login existing account successfully
And Search for a product in the search field
And Search in the category
When Add a random product to the favorites
Then Checkout all Lego Harry Potter