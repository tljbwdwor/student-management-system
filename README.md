To test the app: 
1. Run in Payara or a suitable alternative. 
2. Use Insomnia or Postman to make calls to the following URL's. Please observe the requirements for each url ie 
   QueryParams or required objects.
   
The system will boot with no data in the database, so you must add entries to test. 

Entries must be JSON formatted objects. All fields are mandatory except phoneNumber. Objects should take the 
following form:

{
    "firstName": "String, > 2 char",
    "lastName": "String, > 2 char",
    "email": "String",
    "phoneNumber": "String, > 9 && < 13 char"
}

URL's
Base url: http://localhost:8080/student-management-system/api/v1/student

POST METHOD
Create Student: http://localhost:8080/student-management-system/api/v1/student/create

GET METHODS 
Get All Students: http://localhost:8080/student-management-system/api/v1/student/getall
Get Student By ID: http://localhost:8080/student-management-system/api/v1/student/getbyid/{id}
Get Student(s) By Last Name: http://localhost:8080/student-management-system/api/v1/student/getbylastname *requires QueryParam lastname*

PUT METHOD
Replace Record: http://localhost:8080/student-management-system/api/v1/student/replace (Send a JSON object with the request. Will update record with matching id or create new if no current record with that id.)

PATCH METHODS
Update First Name: http://localhost:8080/student-management-system/api/v1/student/update/firstname/{id} *requires QueryParam firstname*
Update Last Name: http://localhost:8080/student-management-system/api/v1/student/update/lastname/{id} *requires QueryParam lastname*
Update Email: http://localhost:8080/student-management-system/api/v1/student/update/email/{id} *requires QueryParam email*
Update Phone: http://localhost:8080/student-management-system/api/v1/student/update/phonenumber/{id} *requires QueryParam phonenumber*

DELETE METHOD
Delete record: http://localhost:8080/student-management-system/api/v1/student/delete/{id}