# About  
# Run  
## BE: Background

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

## Good to implement
- Pinia. Good to add for storing filters value when we are moving in out SPA.