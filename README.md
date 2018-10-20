# heavyweight
A Spring 4 web application that allows the user to see who the heavyweight boxing champion of the world was on a specified date (and not much else!). The data is taken from Wikipedia and stuck in a document database. This database can be updated.

## System requirements (for manual deployment to local machine)
* Java 7
* MongoDB
* Tomcat / Tomcat manager admin

## System requirements (for manual deployment to container)
* Java 7
* Docker

## System requirements (for automated deployment)
* Docker

## Notes (for manual deployment)
* Uses the Gradle Cargo Plugin v2

## Manual Deployment to local machine
* Start Tomcat
* Start MongoDB
* `./gradlew clean war cargoReDeployRemote`

## Manual Deployment to container
* See the [`tomcat-maanger-mongo` project](https://github.com/edwinek/tomcat-manager-mongo) for a ready-to-use container script 

## Automated Deployment
* See the [`heavyweight-deploy` project](https://github.com/edwinek/heavyweight-deploy) for a containerised version of this project.

## Useage
* Service will deploy to `http://localhost:8080/heavyweight`
* Resource (GET): `/query?date=yyyy-mm-dd`
    * to query by date
* Resource (GET): `/update`
    * to update the database from Wikipedia
