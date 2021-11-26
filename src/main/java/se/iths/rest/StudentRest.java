package se.iths.rest;

import se.iths.Exception.EntityNotFound;
import se.iths.Validator.StudentValidator;
import se.iths.Validator.SubjectValidator;
import se.iths.entity.Student;
import se.iths.service.StudentService;

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
    StudentValidator studentValidator;
    @Inject
    SubjectValidator subjectValidator;

    @Path("create")
    @POST
    public Response createStudent(Student student) {
        studentValidator.validateFirstName(student);
        studentValidator.validateLastName(student);
        studentValidator.validateEmail(student);

        studentService.createStudent(student);
            return Response.status(201).entity(student).build();
    }

    @Path("getall")
    @GET
    public Response getAllStudents() {
        studentValidator.verifyDatabaseNotEmpty();

        List<Student> studentList = studentService.findAllStudents();
            return Response.status(200).entity(studentList).build();
    }

    @Path("getbyid/{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) {
        studentValidator.verifyStudentExists(id);

        Student student = studentService.findStudentById(id);
            return Response.status(200).entity(student).build();
    }

    @Path("getbylastname")
    @GET
    public Response getStudentsByLastName(@QueryParam("lastname") String lastName) {
        List<Student> studentList = studentService.findStudentByLastName(lastName);
        if (studentList.isEmpty()) {
            throw new EntityNotFound("No students with last name of " + lastName);
        } else
            return Response.ok(studentList).build();
    }

    @Path("replace")
    @PUT
    public Response replaceStudent(Student student) {
       studentValidator.validateFirstName(student);
       studentValidator.validateLastName(student);
       studentValidator.validateEmail(student);

       studentService.replaceStudent(student);
           return Response.status(202).entity(student).build();
    }

    @Path("update/firstname/{id}")
    @PATCH
    public Response updateFirstName(@PathParam("id") Long id, @QueryParam("firstname") String firstName) {
       studentValidator.verifyStudentExists(id);
       studentValidator.validateFirstNameString(firstName);

       Student student = studentService.updateFirstName(id, firstName);
           return Response.status(202).entity(student).build();
    }

    @Path("update/lastname/{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, @QueryParam("lastname") String lastName) {
        studentValidator.verifyStudentExists(id);
        studentValidator.validateLastNameString(lastName);

        Student student = studentService.updateLastName(id, lastName);
            return Response.status(202).entity(student).build();
    }

    @Path("update/email/{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        studentValidator.verifyStudentExists(id);
        studentValidator.validateEmailString(email);

        Student student = studentService.updateEmail(id, email);
            return Response.status(202).entity(student).build();
    }

    @Path("update/phonenumber/{id}")
    @PATCH
    public Response updatePhoneNumber(@PathParam("id") Long id, @QueryParam("phonenumber") String phoneNumber) {
        studentValidator.verifyStudentExists(id);
        studentValidator.validatePhoneNumber(phoneNumber);

        Student student = studentService.updatePhoneNumber(id, phoneNumber);
            return Response.status(202).entity(student).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentValidator.verifyStudentExists(id);

        studentService.deleteStudent(id);
            return Response.status(202).entity("Deleted student with ID " + id).build();
    }

    @Path("addsubject/{student_id}/{subject_id}")
    @PUT
    public Response addSubject(@PathParam("student_id") long student_id, @PathParam("subject_id") long subject_id) {
        studentValidator.verifyStudentExists(student_id);
        subjectValidator.verifySubjectExists(subject_id);

        studentService.addSubjectToStudent(student_id,subject_id);
            return Response.status(201).entity("Student has been enrolled").build();
    }

}
