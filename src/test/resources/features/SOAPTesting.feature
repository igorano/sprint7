Feature: API testing

  Background:
    Given go to url

  Scenario: Check SOAP request
    When create request
    Then check response

  Scenario: Retrieve information from the DB
    When create get request
    Then retrieve information from the DB

  Scenario: Edit an entry in the DB
    When create post request
    Then edit an entry in the DB

  Scenario: Delete an entry
    When create delete request
    Then delete an entry

  Scenario: Get a particular employee
    When Get a particular employee
    Then retrieve information from the DB

  Scenario: Get all employees
    When create get request
    Then retrieve information from the DB

  Scenario: Error message when getting non existing employee
    When get non existing employee
    Then retrieve information from the DB

  Scenario: Error message when creating already existing employee
    When creating already existing employee
    Then retrieve information from the DB

