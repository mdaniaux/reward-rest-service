# reward-rest-service

Quick assignment from Michael Daniaux done the 21st of july 2022

My goal was to show that it was easy for me to use :
Spring Boot Starters so that way all our maven dependencies are managed
Spring Boot Application and configuration (application.properties)
Rest-services : Spring Web - Spring MVC
Spring design patterns : Controller - Services - Repository
MockMvc : for the unit tests
Embedded H2 SQL database : schema and data load from data.sql
JdbcTemplate and RowMapper to retrieve the data from the db
Exception management : 400 or 500 are properly returned when exceptions
Logging is also included

I did not spend time to do all the Assertions in the Unit tests by choice, limited time available. The important is to show that I can do it...

I made the assumptions that most parameters are optional to allow flexibility, by determining default values if they are missing. But all the cases have not been implemented as this is just an assignement. (start date, end date)

Only HTTP.GET methods are implemented as http params are small

2 services are available /transactions and /rewards. 
/transactions was not asked, but I thought it could be useful to debug and check the data in the db.


Unit tests are available at the controller level RewardControllerTests, comment/uncomment the test you wish to run.

after a maven build, it is possible to start the microservices like this : java -jar reward-rest-service-0.0.1-SNAPSHOT.jar

and then as this is just GET services, it is possible to use a browser and test it.
http://localhost:8080/transactions?customerId=cust001&start=2022-01-01T05:00:00.000Z&end=2022-03-31T05:00:00.000Z
http://localhost:8080/rewards?customerId=cust001&start=2022-01-01T05:00:00.000Z&end=2022-03-31T05:00:00.000Z

Also possible to test it using a tool that does http request like postman.

Finally, I did not add swagger in the project as this was not mentionned.


I am sure I miss few things...

MD