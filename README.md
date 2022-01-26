# How to use it

## Run quarkus app
mvn clean compile quarkus:dev

## Run Vue app
npm run serve

## Create users
The only way to create users it's calling the endpoint provided for it under the path (http://localhost:8080/users) with a POST request.

## Security 
Only users with role "ADMIN" can create new notes, but the notes are visible even if you are not logged in.

## Database Set-up
Use the docker-composer file stored in infraestructure directory to run the Postgres docker container.


Made by Daniel Falcón Ruiz
