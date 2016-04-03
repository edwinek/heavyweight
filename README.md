# heavyweight
A Spring 4 web application that allows the user to see who the heavyweight boxing champion of the world was on a specified date (and not much else!).

## System requirements
* Java 7
* Maven
* MongoDB
* Tomcat / Tomcat manager admin

## Notes
* Uses the Maven Tomcat Plugin v7
    * pom.xml specifies that the server should be called "TomcatServer" but this can be changed.

## Deployment
* Start Tomcat
* Start MongoDB
* mvn tomcat7:deploy

## Useage
* http://localhost:8080/heavyweight/query?date=yyyy-mm-dd
    * to query by date
* http://localhost:8080/heavyweight/update
    * to update the database
