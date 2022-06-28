Feature: SOAP testing

  Background:
    Given go to url

  Scenario: Check SOAP add request
    When create add request
    Then assert response content

  Scenario: Check SOAP divide request
    When create divide request
    Then assert response content

  Scenario: Check SOAP multiply request
    When  create multiply request
    Then assert response content

  Scenario: Check SOAP subtract request
    When create subtract request
    Then assert response content
