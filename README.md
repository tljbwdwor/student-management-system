To test the app: 
1. Run in Payara or a suitable alternative. 
2. Use Insomnia or Postman to make calls to the following URL's. Please observe the requirements for each url ie 
   QueryParams or required objects.
   
A small amount of sample data will be present at startup.

***PONTUS*** To see the full information for a subject (ie teacher and student(s)), use the following url:
http://localhost:8080/student-management-system/api/v1/subject/getall

There are 3 entity classes:
1. Student
2. Teacher
3. Subject

A subject can have many students but only one teacher.
Teachers and students may be added to multiple subjects.

Entries must be JSON formatted objects. 

For Student and Teacher objects, all fields are mandatory except phoneNumber. These objects should take the 
following form:


    "firstName": "String, > 2 char",
    "lastName": "String, > 2 char",
    "email": "String",
    "phoneNumber": "String, > 9 && < 13 char"

For Subject objects, a single String field of "name" is mandatory.

URL endpoints

BASE URL: http://localhost:8080/student-management-system/api/v1
SECONDARY URL (REQUIRED): /student OR /teacher OR /subject

POST METHODS

baseUrl + secondaryUrl + /create  

GET METHODS

baseUrl + secondaryUrl + /getall

baseUrl + secondaryUrl + /getbyid/{id}

Get Student(s)/Teacher(s) By Last Name: base url + /student OR /teacher + /getbylastname *requires QueryParam lastname*

PUT METHOD

baseUrl + secondaryUrl + /replace (Send a JSON object with the request. Will update record with matching id or create 
new if no current record with that id.)

PATCH METHODS (For Student and Teacher classes only)

baseUrl + secondaryUrl + /update/firstname/{id} *requires QueryParam firstname*

baseUrl + secondaryUrl + /update/lastname/{id} *requires QueryParam lastname*

baseUrl + secondaryUrl + /update/email/{id} *requires QueryParam email*

baseUrl + secondaryUrl + /update/phonenumber/{id} *requires QueryParam phonenumber*

DELETE METHOD

baseUrl + secondaryUrl + /delete/{id}