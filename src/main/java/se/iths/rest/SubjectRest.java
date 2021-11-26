package se.iths.rest;

import se.iths.Exception.ResponseMessage;
import se.iths.Validator.StudentValidator;
import se.iths.Validator.SubjectValidator;
import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subject")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;
    @Inject
    SubjectValidator subjectValidator;
    @Inject
    StudentValidator studentValidator;

    @Path("create")
    @POST
    public Response createSubject(Subject subject) {
        subjectValidator.validateName(subject);

        subjectService.createSubject(subject);
            return Response.status(201).entity(subject).build();
    }

    @Path("getall")
    @GET
    public Response getAllSubjects() {
        subjectValidator.verifyDatabaseNotEmpty();

        List<Subject> subjectList = subjectService.findAllSubjects();
            return Response.status(200).entity(subjectList).build();
    }

    @Path("getbyid/{id}")
    @GET
    public Response getSubjectById(@PathParam("id") Long id) {
        subjectValidator.verifySubjectExists(id);

        Subject subject = subjectService.findSubjectById(id);
            return Response.status(200).entity(subject).build();
    }

    @Path("replace")
    @PUT
    public Response replaceSubject(Subject subject) {
        subjectValidator.validateName(subject);
        subjectValidator.validateNameString(subject.getName());

        subjectService.updateSubject(subject);
            return Response.status(202).entity(subject).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        subjectValidator.verifySubjectExists(id);

        subjectService.deleteSubject(id);
            return Response.status(202).entity(new ResponseMessage("Subject with ID " + id + " has been deleted")).build();
    }

    @Path("removeteacher/{subject_id}")
    @PUT
    public Response removeTeacher(@PathParam("subject_id") Long subject_id) {
        subjectValidator.verifySubjectExists(subject_id);

        subjectService.removeTeacherFromSubject(subject_id);
            return  Response.status(202).entity(new ResponseMessage("Teacher removed from subject " + subject_id)).build();
    }

    @Path("removestudent/{subject_id}/{student_id}")
    @PUT
    public Response removeStudent(@PathParam("subject_id") Long subject_id, @PathParam("student_id") Long student_id) {
        subjectValidator.verifySubjectExists(subject_id);
        studentValidator.verifyStudentExists(student_id);

        subjectService.removeStudentFromSubject(subject_id, student_id);
            return Response.status(202).entity(new ResponseMessage("Student " + student_id + " removed from subject " + subject_id)).build();
    }
}
