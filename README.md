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

```bash
docker-compose up -d database
```

You can run backend application with gradle task background-process.Tasks.application.bootRun or from main class
java/com/oneot/testassigment/AssigmentApplication.java

## FE: Web

Better way is to execute 'dev' script form 'package.json'

```bash
cd ./web
yarn vite
```

Also you can run FE with gradle task web.Tasks.node.viteRun (Warning! You must be able to stop/kill process manually,
because IDE can not do it)

# Did, do, todo

## Ready

### FE: Web

Created SPA with vite + vue3. Setup vite proxy. Used vue-router for page navigation. Created example pages "About" and "
Forecasts". Forecasts page contains filter block for choosing search parameters and table for showing results.  
If you send request with empty parameters BE will return first 20 forecast from DB. 20 is page size, it can be changed
on frontend.

### BE: Background

Springboot application with connection to DB. Used liquibase for DB migrations. Created auditable table, function and
trigger. BE executed scheduled task every 30 min, that option can be changed in application.properties. Also used
shedlock, so BE can run on several pods and scheduled job will execute only once. Created endpoint for receiving all
location names and another endpoint for filtered pagination search. Was added custom mapper to handle with 1 - * - 1
table connections.

**_Why RestTemplate was chosen_**

I have chosen restTemplate because request to weather API can be done in synchronous way. For asynchronous and thread
non-blocking requests WebClient will be better choice.

## In progress

### Known issues and not finished tasks

- If launch web application through gradle then process will not stop properly, you must be able to do this manually.
- Object type for pagination filtered search response is not updated for pagination response.

### Implementations that were not finished

- Swagger. Rest controller classes and methods are annotated.
- Pagination in Forecasts web page. All necessary data received from BE.
- Tests. Created test profile.

### Good to implement

- Pinia. Good to add for storing filters values when we are moving in out SPA.
- Multiselect with text input with autocomplete for place search options 