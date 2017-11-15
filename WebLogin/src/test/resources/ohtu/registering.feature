Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation is successful with valid username and password
    Given command new user is selected
    When a valid username "kliisa" and password "salainen1" and matching password confirmation are entered
    Then a new user is created

  Scenario: user can register with valid username and logout
    Given command new user is selected
    When registering with username "jukkkka" and password "1234567a"
    And clicked to home page
    And clicked logout
    Then user is logged out

  Scenario: creation fails with too short username and valid password
    Given command new user is selected
    When registering with username "kk" and password "akkuj"
    Then user is not registered and error message is given

  Scenario: creation fails with correct username and too short password
    Given command new user is selected
    When registering with username "kkk" and password "akkuj"
    Then user is not created and error "password should have at least 8 characters" is reported

  Scenario: creation fails with already taken username and valid password
    Given command new user is selected
    When registering with username "jukka" and password "ak7yikuj"
    Then user is not created and error "username is already taken" is reported

  Scenario: user can login with successfully generated account
    Given user with username "lea" with password "salainen1" is successfully created
    And login is selected
    When correct username "lea" and password "salainen1" are given
    Then user is logged in

  Scenario: user can not login with account that is not successfully created
    Given user with username "aa" and password "bad" is tried to be created
    And login is selected
    When correct username "aa" and password "bad" are given
    Then user is not logged in and error message is given
