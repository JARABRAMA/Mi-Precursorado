# Mi Precursorado App - Requirements

This app helps pioneers (precursors) manage their preaching hours.

## Feature: List preaching days

### Scenario: User lists preaching days of the current month
- Given: the user enters the app
- When: the current screen is the list of preaching days
- Then: the app lists the preaching days of the current month

### Scenario: The month has no registered preaching days
- Given: the user enters the app
- When: the current screen is the list of preaching days
- And: the selected month has no registered preaching days
- Then: the app shows the message 'Sin días de predicación registrados'

### Scenario: User selects a month
- Given: the user enters the app
- When: the current screen is the list of preaching days
- And: the user selects a year and a month
- Then: the app lists the preaching days of the selected month

## Feature: Register preaching time (UPSERT preaching day)

### Scenario: User saves a preaching day that does not exist
- Given: the user registers a new time for a day
- When: the day is not in the database
- Then: the app creates a new preaching day
- And: the app assigns the registered duration to that day

### Scenario: User saves a preaching day that already exists
- Given: the user registers a new time for a day
- When: the day is already in the database
- Then: the app shows an alert with the message 'Este día ya tiene un tiempo registrado. Si continúas, el nuevo tiempo se sumará al tiempo registrado anteriormente. ¿Deseas continuar?'

### Scenario: User accepts adding time to an existing day
- Given: the user registers a new time for a day
- When: the day is already in the database
- And: the user clicks 'Aceptar' when the alert appears
- Then: the new time is added to the previously registered time

### Scenario: User cancels adding time to an existing day
- Given: the user registers a new time for a day
- When: the day is already in the database
- And: the user clicks 'Cancelar' when the alert appears
- Then: no change is made

## Notes

- Each calendar date can hold a single preaching day; additional time on the same date is accumulated.
- Durations are stored per day using `java.time.Duration`.
