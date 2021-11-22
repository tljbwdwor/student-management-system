package se.iths.rest;

import se.iths.Validator.StudentValidator;
import se.iths.Validator.SubjectValidator;
import se.iths.entity.Student;
import se.iths.Exception.EntityNotFound;
import se.iths.Exception.NotModified;
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
        if (studentValidator.validateFirstName(student) && studentValidator.validateLastName(student) && studentValidator.validateEmail(student)) {
            studentService.createStudent(student);
            return Response
                    .status(201)
                    .entity(student).build();
        } else throw new NotModified("Student Not Created");
    }

    @Path("getall")
    @GET
    public Response getAllStudents() {
        if (studentValidator.verifyDatabaseNotEmpty()) {
            List<Student> studentList = studentService.findAllStudents();
            return Response
                    .status(200)
                    .entity(studentList).build();
        } else throw new EntityNotFound("No Records Found In Student Table");
    }

    @Path("getbyid/{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) {
        if (studentValidator.verifyStudentExists(id)) {
            Student student = studentService.findStudentById(id);
            return Response
                    .status(200)
                    .entity(student).build();
        } else throw new EntityNotFound("No Student Found With Id_" + id);
    }

    @Path("getbylastname")
    @GET
    public Response getStudentsByLastName(@QueryParam("lastname") String lastName) {
        List<Student> studentList = studentService.findStudentByLastName(lastName);
        if (studentList.isEmpty()) {
            throw new EntityNotFound("No Students With Last Name Of_" + lastName);
        } else return Response
                .ok(studentList)
                .build();
    }

    @Path("replace")
    @PUT
    public Response replaceStudent(Student student) {
       if (studentValidator.validateFirstName(student) && studentValidator.validateLastName(student) && studentValidator.validateEmail(student)) {
           studentService.replaceStudent(student);
           return Response
                   .status(202)
                   .entity(student).build();
       } else throw new NotModified("Student Not Updated");
    }

    @Path("update/firstname/{id}")
    @PATCH
    public Response updateFirstName(@PathParam("id") Long id, @QueryParam("firstname") String firstName) {
       if (studentValidator.verifyStudentExists(id) && studentValidator.validateFirstNameString(firstName)) {
           Student student = studentService.updateFirstName(id, firstName);
           return Response
                   .status(202)
                   .entity(student).build();
       } else throw new NotModified("Student First Name Not Updated");
    }

    @Path("update/lastname/{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, @QueryParam("lastname") String lastName) {
        if (studentValidator.verifyStudentExists(id) && studentValidator.validateLastNameString(lastName)) {
            Student student = studentService.updateLastName(id, lastName);
            return Response
                    .status(202)
                    .entity(student).build();
        } else throw new NotModified("Student Last Name Not Updated");
    }

    @Path("update/email/{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        if (studentValidator.verifyStudentExists(id) && studentValidator.validateEmailString(email)) {
            Student student = studentService.updateEmail(id, email);
            return Response
                    .status(202)
                    .entity(student).build();
        } else throw new NotModified("Student Email Not Updated");
    }

    @Path("update/phonenumber/{id}")
    @PATCH
    public Response updatePhoneNumber(@PathParam("id") Long id, @QueryParam("phonenumber") String phoneNumber) {
        if (studentValidator.verifyStudentExists(id) && studentValidator.validatePhoneNumber(phoneNumber)) {
            Student student = studentService.updatePhoneNumber(id, phoneNumber);
            return Response
                    .status(202)
                    .entity(student).build();
        } else throw new NotModified("Student Phone Not Updated");
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        if (studentValidator.verifyStudentExists(id)) {
            studentService.deleteStudent(id);
            return Response
                    .status(202)
                    .entity("Deleted Entry With Id_" + id).build();
        } else throw new NotModified("Student Not Deleted");
    }

    @Path("addsubject/{student_id}/{subject_id}")
    @PUT
    public Response addSubject(@PathParam("student_id") long student_id, @PathParam("subject_id") long subject_id) {
        if (studentValidator.verifyStudentExists(student_id) && subjectValidator.verifySubjectExists(subject_id)) {
            studentService.addSubjectToStudent(student_id,subject_id);
            return Response
                    .status(201)
                    .entity("Student Has Been Enrolled").build();
        } else throw new NotModified("Student Not Enrolled");
    }

}
