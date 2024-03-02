You can start application locally
### Locally:
1.  Open the folder with the compose file in the project via the command line and start creating a container with the database with this command:
   ```
    docker-compose up quick-solution-postgres-container -d
   ```
2.  Open the project in IntelliJ IDEA
3.  Start Spring Boot application in class QuickSolutionApplication.
4.  Also open the ddl.sql file and run the database build
5.  open your browser and go to
   ```
   http://localhost:8080/
   ```
    
#### Stop the application:
1. Stop Spring Boot application locally with spring profile local.
   ```
   docker compose down
   ```
>>>>>>> develop
