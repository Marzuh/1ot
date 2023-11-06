# About

Weather forecast application which consists of two independent modules. Web - for frontend and Background for backend.  
Used technologies: Springboot3, Java17, node20, vue3, postgres16, luquibase.

# How to run the project

## BE: Background

### Requirments:

Java 17

Database with given parameters:  
Address: localhost:5432  
Database: weather  
Username: weather   
Password: weather

Port 7001 must be free.

For the local development up a docker container from 'docker-compose.yml'

```bash
docker-compose up -d database
```

Run backend application with gradle task background-process.Tasks.application.bootRun or from main class
java/com/oneot/testassigment/AssigmentApplication.java

```bash
./gradlew :background-process:bootRun
```

## FE: Web

Port 7000 must be free.
Better is to execute 'dev' script form 'package.json'

```bash
cd ./web
yarn vite
```

Other way is to run FE with gradle task web.Tasks.node.viteRun (Warning! You must be able to stop/kill process manually,
because IDE can not do it)
```bash
./gradlew :web:viteRun
```

**_Tested_**
Project was developed and tested (mostly manual for FE) on Ubuntu 22.04 64bit, Chrome, Mozilla Firefox 119.0 (64-bit) 

## Implemented features

### FE: Web

Created SPA with vite + vue3. Setup vite proxy. Used vue-router for page navigation. Created example pages "About" and "
Forecasts". Forecasts page contains filter block for choosing search parameters and table for showing results.  
If browser sends a request with empty parameters BE will return first 20 forecast from DB. 20 is page size, it can be changed
on frontend. Pagination is set for 3 items per page for demo purpose. For production, it could be changed back to 20
items per page or added customer feature to choose size of the page.

### BE: Background

Springboot application with connection to DB. Used liquibase for DB migrations. Created auditable table, function and
trigger. BE executed scheduled task every 30 min, that option can be changed in application.properties. Also used
shedlock, so BE can run on several pods and scheduled job will execute only once. Created endpoint for receiving all
location names and another endpoint for filtered pagination search. A custom mapper was added to handle with 1 - * - 1
table connections.

**_Why RestTemplate was chosen_**
RestTemplate was chosen because request to weather API can be done in synchronous way. For asynchronous and thread
non-blocking requests WebClient will be better choice.

**_Feature development_**  
Features were developed in their own git branches. As well as I was the only developer, the main branch looks plain.

## In progress

### Known issues and not finished tasks

- If launch web application through gradle then process will not stop properly, you must be able to do this manually.
- Web UI needs to be improved with a responsive design.

### Implementations that were not finished

- Swagger. Rest controller classes and methods are annotated.
- Need to think more about data representation. The current implementation designed as it is designed because for the test assigment I show my skill of using mapper for a complicated request and
  pagination. One way of improvement is to change request so that only forecast objects are searched in DB and @click on
  any of them send next request for all place forecast linked to given forecast. Other way is to change UI. Right now if
  forecast have any place, then places will count as item. If no then forecast itself will be counted as item.

## Good to implement

- Pinia. Good to add for storing filters values when user is moving inside SPA.
- Multiselect with text input with autocomplete for place search options 
