package se.iths.rest;

import se.iths.Validator.StudentValidator;
import se.iths.Validator.SubjectValidator;
import se.iths.entity.Subject;
import se.iths.Exception.EntityNotFound;
import se.iths.Exception.NotModified;
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
        if (subjectValidator.validateName(subject)) {
            subjectService.createSubject(subject);
            return Response
                    .status(201)
                    .entity(subject).build();
        } else throw new NotModified("Subject Not Created");
    }

    @Path("getall")
    @GET
    public Response getAllSubjects() {
        if (subjectValidator.verifyDatabaseNotEmpty()) {
            List<Subject> subjectList = subjectService.findAllSubjects();
            return Response
                    .status(200)
                    .entity(subjectList).build();
        } else throw new EntityNotFound("No Records Found In Subject Table");
    }

    @Path("getbyid/{id}")
    @GET
    public Response getSubjectById(@PathParam("id") Long id) {
        if (subjectValidator.verifySubjectExists(id)) {
            Subject subject = subjectService.findSubjectById(id);
            return Response
                    .status(200)
                    .entity(subject).build();
        } else throw new EntityNotFound("No Subject Found With Id_" + id);
    }

    @Path("replace")
    @PUT
    public Response replaceSubject(Subject subject) {
        if (subjectValidator.validateName(subject) && subjectValidator.validateNameString(subject.getName())) {
            subjectService.updateSubject(subject);
            return Response
                    .status(202)
                    .entity(subject).build();
        } else throw new NotModified("Subject Not Updated");
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        if (subjectValidator.verifySubjectExists(id)) {
            subjectService.deleteSubject(id);
            return Response
                    .status(202)
                    .entity("Subject With Id_" + id + "Has Been Deleted").build();
        } else throw new NotModified("Subject Not Updated");
    }

    @Path("removeteacher/{subject_id}")
    @PUT
    public Response removeTeacher(@PathParam("subject_id") Long subject_id) {
        if (subjectValidator.verifySubjectExists(subject_id)) {
            subjectService.removeTeacherFromSubject(subject_id);
            return  Response
                    .status(202)
                    .entity("Teacher Removed From Subject_" + subject_id).build();
        } else throw new NotModified("Teacher Not Removed From Subject");
    }

    @Path("removestudent/{subject_id}/{student_id}")
    @PUT
    public Response removeStudent(@PathParam("subject_id") Long subject_id, @PathParam("student_id") Long student_id) {
        if (subjectValidator.verifySubjectExists(subject_id) && studentValidator.verifyStudentExists(student_id)) {
            subjectService.removeStudentFromSubject(subject_id, student_id);
            return Response
                    .status(202)
                    .entity("Student_" + student_id + "Removed From Subject_" + subject_id).build();
        } else throw new NotModified("Student Not Removed From Subject");
    }
}
