
# QuickSolution

Example of REST API built on Spring Boot 3.

## How to run
Application can be started locally (spring profile local + database in the docker container)
or in the docker container (spring profile test and both database and app are docker containers)

### Locally:
1. In the root folder start the container with database:
docker-compose up db -d

2. Start Spring Boot application locally with spring profile local.
