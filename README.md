# Documentation 

### Introduction
Help HR manager Personia get a grasp of her ever-changing company’s hierarchy! Every week
we receives a JSON of employees and their supervisors from her demanding CEO,
who keeps changing his mind about how to structure his company. We wants a tool to help
them better understand the employee hierarchy and respond to employee’s queries about who
their boss is. Can you help her?

### Swagger UI
 - http://localhost:8080/swagger-ui/index.html#/
 
## How to run the project

### Using Docker
- ./gradlew build
- docker build -t hierarchy .
- docker run -it -p 8080:8080 --rm hierarchy

### locally
- ./gradlew build
- ./gradlew bootRun

### Run Tests
- gradle test -i

## Authorization
All endpoint are secured with basic auth.

### Credentials
- username : "theUsername"
- password : "thePassword"

example header : `-H "Authorization: Basic {base64 encrypted username:password}"`


### Endpoints
POST: http://localhost:8080/employee/hierarchy
```
Body: 

{
  "Pete": "Nicku",
  "Barbara": "Nicku",
  "Nicku": "Sophie",
  "Sophie": "Jonas"
}
```
POST: http://localhost:8080/employee/supervisor
```
Body:

Barbara
```
GET: http://localhost:8080/employee/structuredHierarchy

### Scripts
 - Run [Create hierarchy script](scripts/createHierarchyExample.sh) to add the new hierarchy to database
 - Run [Find supervisor](scripts/findSupervisor.sh) to get supervisors of an employee
 - Run [Build hierarchy](scripts/builtHierarchy.sh) to build and get all the hierarchy of your organisation
