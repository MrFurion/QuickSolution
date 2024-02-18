# QuickSolution

Example of REST API built on Spring Boot 3.

## How to run
You can start application locally
### Locally:
1.  Open the folder with the compose file in the project via the command line and start creating a container with the database with this command:
   ```
    docker-compose up quick-solution-postgres-container -d
    ```
2.  Start Spring Boot application in class QuickSolutionApplication.
3.  Start build database in file ddl.sql 
    
#### Stop the application:
1. Stop Spring Boot application locally with spring profile local.
   ```
   docker compose down
   ```
