# About  
  
 Weather forecast application that consist from two independent modules. Web - for frontend and Background for backend.  
 Used technologies: Springboot3, Java17, node20, vue3, postgres16, luquibase.

# Run  
## BE: Background
### Requirments:
Java 17

Database with given parameters:  
Address: localhost:5432  
Database: weather  
Username: weather   
Password: weather

Port 7001 must be free.  

For local development you can use up docker container from 'docker-compose.yml'
You can run backend application with gradle task bootRun or from main class java/com/oneot/testassigment/AssigmentApplication.java
### Why RestTemplate was chosen  
редкие запросы, вполне можно синхронно
no @transactional due to use case  
 
## FE: Web  


## Known issues and not finished tasks
- If launch web through gradle then process will not stop properly
- Object type for pagination filtered search not updated


## Implementation that is not finished
- Swagger. Rest controller classes and methods are annotated.
- Pagination in Forecasts web page. All necessary data received from BE.
- Tests. Created test profile.

## Good to implement
- Pinia. Good to add for storing filters value when we are moving in out SPA.