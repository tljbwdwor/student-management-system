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
        if (student.getFirstName().isEmpty() || student.getFirstName().getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
            .entity("message: firstNameRequiredMin2Characters")
            .type(MediaType.APPLICATION_JSON).build());
        }

        if (student.getLastName().isEmpty() || student.getLastName().getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
            .entity("message: lastNameRequiredMin2Characters")
            .type(MediaType.APPLICATION_JSON).build());
        }

        if (student.getEmail().isEmpty() || student.getEmail().isBlank()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
            .entity("message: emailRequired")
            .type(MediaType.APPLICATION_JSON).build());
        }

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
            .entity("message: databaseContainsNoRecords")
            .type(MediaType.APPLICATION_JSON).build());
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
                    .entity("message: noRecordOfId " + id)
                    .type(MediaType.APPLICATION_JSON).build());
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
            .entity("message: noStudentsWithLastNameOf " + lastName)
            .type(MediaType.APPLICATION_JSON).build());
        } else
        return Response
                .status(200)
                .entity(filteredList).build();
    }

    @Path("replace")
    @PUT
    public Response replaceStudent(Student student) {
        if (student.getFirstName().isEmpty() || student.getFirstName().getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
            .entity("message: firstNameInvalid")
            .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        if (student.getLastName().isEmpty() || student.getLastName().getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
            .entity("message: lastNameInvalid")
            .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        if (student.getEmail().isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: emailInvalid")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        studentService.replaceStudent(student);
        return Response
                .status(202)
                .entity(student).build();
    }

    @Path("update/firstname/{id}")
    @PATCH
    public Response updateFirstName(@PathParam("id") Long id, @QueryParam("firstname") String firstName) {
        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("message: noRecordToUpdate")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        if (firstName.isEmpty() || firstName.getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
            .entity("message: firstNameRequiresMin2Characters")
            .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        Student student = studentService.updateFirstName(id, firstName);
        return Response
                .status(202)
                .entity(student).build();
    }

    @Path("update/lastname/{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, @QueryParam("lastname") String lastName) {
        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("message: noRecordToUpdate")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        if (lastName.isEmpty() || lastName.getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: lastNameRequiresMin2Characters")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        Student student = studentService.updateLastName(id, lastName);
        return Response
                .status(202)
                .entity(student).build();
    }

    @Path("update/email/{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("message: noRecordToUpdate")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        if (email.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: emailFieldIsEmpty")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        Student student = studentService.updateEmail(id, email);
        return Response
                .status(202)
                .entity(student).build();
    }

    @Path("update/phonenumber/{id}")
    @PATCH
    public Response updatePhoneNumber(@PathParam("id") Long id, @QueryParam("phonenumber") String phoneNumber) {
        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("message: noRecordToUpdate")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        if (phoneNumber.isEmpty() || ((phoneNumber.getBytes().length < 9) || (phoneNumber.getBytes().length > 13))) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: invalidPhoneNumber")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        Student student = studentService.updatePhoneNumber(id, phoneNumber);
        return Response
                .status(202)
                .entity(student).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
            .entity("message: noRecordToDelete")
            .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
        studentService.deleteStudent(id);
        return Response
                .status(202)
                .entity("message: deletedEntryWithId" + id).build();
    }

}
