# heavyweight
A Spring 4 web application that allows the user to see who the heavyweight boxing champion of the world was on a specified date (and not much else!). The data is taken from Wikipedia and stuck in a document database. This database can be updated.

## System requirements
* Java 7
* Maven
* MongoDB
* Tomcat / Tomcat manager admin

## Notes
* Uses the Maven Tomcat Plugin v7
    * The pom.xml specifies that the server should be called "TomcatServer" but this can be changed.
* UPDATE: It's now necessary to add a hosts file route from mongoip -> 127.0.0.1 in your hostsfile
    * This is for compatibility with heavyweight-deploy

## Deployment
* Start Tomcat
* Start MongoDB
* mvn tomcat7:deploy

## Useage
* Service will deploy to `http://localhost:8080/heavyweight`
* Resource (GET): `/query?date=yyyy-mm-dd`
    * to query by date
* Resource (GET): `/update`
    * to update the database
