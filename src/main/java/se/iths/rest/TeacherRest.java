package se.iths.rest;

import se.iths.Validator.SubjectValidator;
import se.iths.Validator.TeacherValidator;
import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;
    @Inject
    TeacherValidator teacherValidator;
    @Inject
    SubjectValidator subjectValidator;

    @Path("create")
    @POST
    public Response createTeacher(Teacher teacher) {
        if (teacherValidator.validateFirstName(teacher) && teacherValidator.validateLastName(teacher) && teacherValidator.validateEmail(teacher)) {
            teacherService.createTeacher(teacher);
            return Response
                    .status(201)
                    .entity(teacher).build();
        } else return Response.notModified().build();
    }

    @Path("getall")
    @GET
    public Response getAllTeachers() {
        if (teacherValidator.verifyDatabaseNotEmpty()) {
            List<Teacher> teacherList = teacherService.findAllTeachers();
            return Response
                    .status(200)
                    .entity(teacherList).build();
        } else return Response.status(404).build();
    }

    @Path("getbyid/{id}")
    @GET
    public Response getTeacherById(@PathParam("id") Long id) {
        if (teacherValidator.verifyTeacherExists(id)) {
            Teacher teacher = teacherService.findTeacherById(id);
            return Response
                    .status(200)
                    .entity(teacher).build();
        } else return Response.notModified().build();
    }

    @Path("getbylastname")
    @GET
    public Response getTeachersByLastName(@QueryParam("lastname") String lastName) {
        List<Teacher> teacherList = teacherService.findTeacherByLastName(lastName);
        if (teacherList.isEmpty()) {
            return Response
                    .status(404).
                            entity("message: noTeachersWithLastNameOf " + lastName)
                    .build();
        } else return Response
                .ok(teacherList)
                .build();
    }

    @Path("replace")
    @PUT
    public Response replaceTeacher(Teacher teacher) {
        if (teacherValidator.validateFirstName(teacher) && teacherValidator.validateLastName(teacher) && teacherValidator.validateEmail(teacher)) {
            teacherService.replaceTeacher(teacher);
            return Response
                    .status(202)
                    .entity(teacher).build();
        } else return Response.notModified().build();
    }

    @Path("update/firstname/{id}")
    @PATCH
    public Response updateFirstName(@PathParam("id") Long id, @QueryParam("firstname") String firstName) {
        if (teacherValidator.verifyTeacherExists(id) && teacherValidator.validateFirstNameString(firstName)) {
            Teacher teacher = teacherService.updateFirstName(id, firstName);
            return Response
                    .status(202)
                    .entity(teacher).build();
        } else return Response.notModified().build();
    }

    @Path("update/lastname/{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, @QueryParam("lastname") String lastName) {
        if (teacherValidator.verifyTeacherExists(id) && teacherValidator.validateLastNameString(lastName)) {
            Teacher teacher = teacherService.updateLastName(id, lastName);
            return Response
                    .status(202)
                    .entity(teacher).build();
        } else return Response.notModified().build();
    }

    @Path("update/email/{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        if (teacherValidator.verifyTeacherExists(id) && teacherValidator.validateEmailString(email)) {
            Teacher teacher = teacherService.updateEmail(id, email);
            return Response
                    .status(202)
                    .entity(teacher).build();
        } else return Response.notModified().build();
    }

    @Path("update/phonenumber/{id}")
    @PATCH
    public Response updatePhoneNumber(@PathParam("id") Long id, @QueryParam("phonenumber") String phoneNumber) {
        if (teacherValidator.verifyTeacherExists(id) && teacherValidator.validatePhoneNumber(phoneNumber)) {
            Teacher teacher = teacherService.updatePhoneNumber(id, phoneNumber);
            return Response
                    .status(202)
                    .entity(teacher).build();
        } else return Response.notModified().build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        if (teacherValidator.verifyTeacherExists(id)) {
            teacherService.deleteTeacher(id);
            return Response
                    .status(202)
                    .entity("message: deletedTeacherWithId" + id).build();
        } else return Response.notModified().build();
    }

    @Path("addsubject/{teacher_id}/{subject_id}")
    @PUT
    public Response addSubject(@PathParam("teacher_id") long teacher_id, @PathParam("subject_id") long subject_id) {
        if (teacherValidator.verifyTeacherExists(teacher_id) && subjectValidator.verifySubjectExists(subject_id)) {
            teacherService.addTeacherToSubject(teacher_id,subject_id);
            return Response
                    .status(201)
                    .entity("message: teacherAddedToSubject").build();
        } else return Response.notModified().build();
    }

}
