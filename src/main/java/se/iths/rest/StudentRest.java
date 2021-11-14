package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("create")
    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response
                .status(201)
                .entity(student).build();
    }

    @Path("getall")
    @GET
    public Response getAllStudents() {
        List<Student> studentList = studentService.findAllStudents();
        if (studentList.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
            .entity("Student database currently has no entries.")
            .type(MediaType.TEXT_PLAIN_TYPE).build());
        } else
        return Response
                .status(200)
                .entity(studentList).build();
    }

    @Path("getbyid/{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        if (student == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + id + " was not found in the database.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        } else
        return Response
                .status(200)
                .entity(student).build();
    }

    @Path("getbylastname")
    @GET
    public Response getStudentsByLastName(@QueryParam("lastname") String lastName) {
        List<Student> allStudents = studentService.findAllStudents();
        List<Student> filteredList = new ArrayList<>();

        for (Student student: allStudents)
        {
            if (student.getLastName().equals(lastName)) {
                Objects.requireNonNull(filteredList).add(student);
            }
        }

        if (filteredList.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
            .entity("No students found with last name of " + lastName)
            .type(MediaType.TEXT_PLAIN_TYPE).build());
        } else
        return Response
                .status(200)
                .entity(filteredList).build();
    }

    @Path("replace")
    @PUT
    public Response replaceStudent(Student student) {
        studentService.replaceStudent(student);
        return Response
                .status(202)
                .entity(student).build();
    }

    @Path("update/firstname/{id}")
    @PATCH
    public Response updateFirstName(@PathParam("id") Long id, @QueryParam("firstname") String firstName) {
        Student student = studentService.updateFirstName(id, firstName);
        return Response
                .status(202)
                .entity(student).build();
    }

    @Path("update/lastname/{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, @QueryParam("lastname") String lastName) {
        Student student = studentService.updateLastName(id, lastName);
        return Response
                .status(202)
                .entity(student).build();
    }

    @Path("update/email/{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        Student student = studentService.updateEmail(id, email);
        return Response
                .status(202)
                .entity(student).build();
    }

    @Path("update/phonenumber/{id}")
    @PATCH
    public Response updatePhoneNumber(@PathParam("id") Long id, @QueryParam("phonenumber") String phoneNumber) {
        Student student = studentService.updatePhoneNumber(id, phoneNumber);
        return Response
                .status(202)
                .entity(student).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.status(202).build();
    }

}
