package se.iths.Validator;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
public class SubjectValidator {

    @Inject
    SubjectService subjectService;

    public boolean validateName(Subject subject) {
        if (subject.getName().isEmpty() || subject.getName().getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: subjectNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateNameString(String name) {
        if (name.isEmpty() || name.getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: subjectNameRequiresMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean verifySubjectExists(Long id) {
        if (subjectService.findSubjectById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
            .entity("message: noSubjectWithId" + id)
            .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

    public boolean verifyDatabaseNotEmpty() {
        List<Subject> subjectList = subjectService.findAllSubjects();
        if (subjectList.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
            .entity("message: databaseContainsNoSubjects")
            .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

}
