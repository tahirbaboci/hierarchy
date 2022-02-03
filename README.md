#Documentation 

###Introduction: Welcome to the Ever-changing Hierarchy GmbH.
Help HR manager Personia get a grasp of her ever-changing company’s hierarchy! Every week
Personia receives a JSON of employees and their supervisors from her demanding CEO Chris,
who keeps changing his mind about how to structure his company. Personia wants a tool to help
her better understand the employee hierarchy and respond to employee’s queries about who
their boss is. Can you help her?

###Personia’s initial requirements were the following:
1. I would like a pure REST API to post the JSON from Chris. This JSON represents an Employee ->
   Supervisor relationship that looks like this:
   ```json
   {
   "Pete": "Nick",
   "Barbara": "Nick",
   "Nick": "Sophie",
   "Sophie": "Jonas"
   }
   ```
   In this case, Nick is a supervisor of Pete and Barbara, Sophie supervises Nick. The supervisor list is
   not always in order.
   
2. As a response to querying the endpoint, I would like to have a properly formatted JSON which
   reflects the employee hierarchy in a way, where the most senior employee is at the top of the JSON
   nested dictionary. For instance, previous input would result in:
   ```json
   {
    "Jonas": {
       "Sophie": {
            "Nick": {
                "Pete": {},
                "Barbara": {}
            }
       }
    }
   }
   ```
   Sometimes Chris gives me nonsense hierarchies that contain loops or multiple roots. I would be
   grateful if the endpoint could handle the mistakes and tell her what went wrong. The more
   detailed the error messages are, the better!

####The HR Operating System
3. I would really like it if the hierarchy could be stored in a relational database (e.g. SQLite) and
   queried to get the supervisor and the supervisor’s supervisor of a given employee. I want to send
   the name of an employee to an endpoint, and receive the name of the supervisor and the name of
   the supervisor’s supervisor in return.
4. I would like the API to be secure so that only I can use it. Please implement some kind of
   authentication.


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