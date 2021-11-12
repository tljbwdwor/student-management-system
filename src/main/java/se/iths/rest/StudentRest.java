package se.iths.rest;

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
        return Response
                .status(200)
                .entity(studentList).build();
    }

    @Path("getbyid/{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        return Response
                .status(200)
                .entity(student).build();
    }

    @Path("replace")
    @PUT
    public Response replaceStudent(Student student) {
        studentService.replaceStudent(student);
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
