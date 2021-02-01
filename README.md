# SKM Simulator

## Description  

This simulates basic subway behaviour such as:
- Moving the trains
- Adding trains / adding compartments / adding passengers
- Removing passengers upon reaching destination
- Passengers are generated and removed automatically, there is no CRUD for them
- All data is stored in a mysql database using liquibase
- Authentication using JWT

## Extended description
There are 2 apps within the project.
1. Simulator
2. Client  

All endpoints and simulation logic are contained withing Simulator.

### Simulator
Responsible for handling all CRUD operations and moving the simulation.
CRUD operations are only available for compartments, trains and users.
There are no CRUD endpoints for stations, as they are added in the initial data to the database.
All passengers are generated randomly on each station (2-8 passengers on each), if there are more passengers
than seats in train, we are only letting them in until we run out of space, the rest is ignored.
On the end of each line the train stops for 2 turns and changes direction. There is an unlimited number of trains
possible as there is no checking for collisions.

### Client
Is allowed only to GET from train or compartment endpoints, can't change anything.

### Security
Users are authenticated using JWT, credentials are stored in database
There are some restrictions for certain endpoints based on the user role, that can be changed in WebSecurity class

## Technologies used
- Gradle
- Docker
- Git
- Spring
- JPA
- Liquibase
- Spring security
- JWT