Feature: API testing

  Scenario: Create an entry in the DB

    Given go to base url
    When create post request
    Then view the entry in DB

  Scenario: Retrieve information from the DB

    Given go to base url
    When create get request
    Then retrieve information from the DB

  Scenario: Edit an entry in the DB

    Given go to base url
    When create post request
    Then edit an entry in the DB

  Scenario: Delete an entry

    Given go to base url
    When create delete request
    Then delete an entry

#- Post a new employee
#- Get a particular employee
#- Get all employees
#- Error message when getting non existing employee
#- Error message when creating already existing employee