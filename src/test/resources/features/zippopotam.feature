Feature: Zippopotam menu

    Scenario: Zippopotam countries button
        Given user is on zippopotam homepage
        When user clicks countries button
        Then user is displayed countries page

    Scenario: Zippopotam Turkey json log
        Given Request from turkey page
        When Get turkey page
        Then The country should be turkey
        Then Serinevler mah and Beyceli mah should have correct longitude
        And Log

    Scenario Outline: Zippopotam countries service checks
        Given Http request with parameters "<code>" "<range>"
        When Waiting response for entering zip code and range
        Then The waiting response is actual "<country>"

        Examples: Countries
           | code | range | country       |
           | TR   | 01000 | Turkey        |
           | AD   | AD100 | Andorra       |
           | US   | 00210 | United States |
           | ru   | 101000| Russia        |

