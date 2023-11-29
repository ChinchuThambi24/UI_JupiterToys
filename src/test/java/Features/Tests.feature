Feature: Jupiter Toys> Contacts- Add Contact feedback / Validate Errors
#Jupiter Toys Contacts functionality testing: Error validations, Add contact feedback

  #Contact page error validations
  @TEST_JT1 @Contacts
  Scenario Outline: Contacts - Error validation
    Given User navigates to the 'contact' of Jupiter Toys site
    When User clicks Submit button
    Then Alert message is displayed for mandatory fields
    When User fills the mandatory fields '<Forename>', '<Email>', '<Message>'
    Then Verify no alert message is displayed

    Examples:
      | Forename        | Email                   | Message       |
      | TestUser        | testuser@test.com       | Test Message  |

  #Submit feedback
  @TEST_JT2 @Contacts
  Scenario Outline: Contacts - Submit feedback
    Given User navigates to the 'contact' of Jupiter Toys site
    When User fills the mandatory fields '<Forename>', '<Email>', '<Message>'
    And User clicks Submit button
    Then Successful submission message is displayed

    Examples:
      | Forename        | Email                   | Message       |
      | TestUser        | testuser@test.com       | Test Message  |

    #Shop and verify the amount
  @TEST_JT3 @Shop
  Scenario Outline: Shop - Shop the items and verify the details in cart
    Given User navigates to the 'shop' of Jupiter Toys site
    And User selects 2 of Stuffed Frogs - '<Item1>'
    And User selects 5 of Fluffy Bunny - '<Item2>'
    And User selects 3 of Valentine Bear - '<Item3>'
    When User navigates to the 'cart' of Jupiter Toys site
    Then Verify subtotal of '<Item1>', '<Item2>' and '<Item3>' is displayed correctly
    And Verify price of '<Item1>', '<Item2>' and '<Item3>' is displayed correctly
    And Verify total is the sum of all subtotals

    Examples:
      | Item1         | Item2         | Item3           |
      | Stuffed Frog  | Fluffy Bunny  | Valentine Bear  |


