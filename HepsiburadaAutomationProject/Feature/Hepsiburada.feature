Feature: Hepsiburada Case

  Scenario: Scenario1: Login
    When the user opened "https://www.hepsiburada.com/" with "chrome" browser
    Then sign in as "test@gmail.com" and "test123456"

  Scenario: Scenario2: Login with wrong e-mail or phone number 
    When the user enter a wrong e-mail or phone number
    Then warning message is displayed to the user
    
   Scenario: Scenario3: Login with empty e-mail or phone number
    When the user sign in with keeping e-mail or phone number empty
    Then mandatory field warning for e-mail or phone number is shown to the user
    
   Scenario: Scenario4: Login with unformatted e-mail 
    When the user sign in with unformatted e-mail 
    Then "you must enter valid e-mail address" warning is displayed