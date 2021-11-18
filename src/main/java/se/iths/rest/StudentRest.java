package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;
import se.iths.service.Validation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;
    @Inject
    Validation validation;

    @Path("create")
    @POST
    public Response createStudent(Student student) {
        if (validation.validateFirstName(student) && validation.validateLastName(student) && validation.validateEmail(student)) {
            studentService.createStudent(student);
            return Response
                    .status(201)
                    .entity(student).build();
        } else return Response.notModified().build();
    }

    @Path("getall")
    @GET
    public Response getAllStudents() {
        if (validation.verifyDatabaseNotEmpty()) {
            List<Student> studentList = studentService.findAllStudents();
            return Response
                    .status(200)
                    .entity(studentList).build();
        } else return Response.status(404).build();
    }

    @Path("getbyid/{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) {
        if (validation.verifyStudentExists(id)) {
            Student student = studentService.findStudentById(id);
            return Response
                    .status(200)
                    .entity(student).build();
        } else return Response.notModified().build();
    }

    @Path("getbylastname")
    @GET
    public Response getStudentsByLastName(@QueryParam("lastname") String lastName) {
        List<Student> studentList = studentService.findStudentByLastName(lastName);
        if (studentList.isEmpty()) {
            return Response
                    .status(404).
                    entity("No students with last name of " + lastName)
                    .build();
        } else return Response
                .ok(studentList)
                .build();
    }

    @Path("replace")
    @PUT
    public Response replaceStudent(Student student) {
       if (validation.validateFirstName(student) && validation.validateLastName(student) && validation.validateEmail(student)) {
           studentService.replaceStudent(student);
           return Response
                   .status(202)
                   .entity(student).build();
       } else return Response.notModified().build();
    }

    @Path("update/firstname/{id}")
    @PATCH
    public Response updateFirstName(@PathParam("id") Long id, @QueryParam("firstname") String firstName) {
       if (validation.verifyStudentExists(id) && validation.validateFirstNameString(firstName)) {
           Student student = studentService.updateFirstName(id, firstName);
           return Response
                   .status(202)
                   .entity(student).build();
       } else return Response.notModified().build();
    }

    @Path("update/lastname/{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, @QueryParam("lastname") String lastName) {
        if (validation.verifyStudentExists(id) && validation.validateLastNameString(lastName)) {
            Student student = studentService.updateLastName(id, lastName);
            return Response
                    .status(202)
                    .entity(student).build();
        } else return Response.notModified().build();
    }

    @Path("update/email/{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        if (validation.verifyStudentExists(id) && validation.validateEmailString(email)) {
            Student student = studentService.updateEmail(id, email);
            return Response
                    .status(202)
                    .entity(student).build();
        } else return Response.notModified().build();
    }

    @Path("update/phonenumber/{id}")
    @PATCH
    public Response updatePhoneNumber(@PathParam("id") Long id, @QueryParam("phonenumber") String phoneNumber) {
        if (validation.verifyStudentExists(id) && validation.validatePhoneNumber(phoneNumber)) {
            Student student = studentService.updatePhoneNumber(id, phoneNumber);
            return Response
                    .status(202)
                    .entity(student).build();
        } else return Response.notModified().build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        if (validation.verifyStudentExists(id)) {
            studentService.deleteStudent(id);
            return Response
                    .status(202)
                    .entity("message: deletedEntryWithId" + id).build();
        } else return Response.notModified().build();
    }

}
