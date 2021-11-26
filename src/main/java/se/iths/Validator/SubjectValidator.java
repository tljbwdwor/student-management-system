package se.iths.Validator;

import se.iths.entity.Subject;
import se.iths.Exception.EntityNotFound;
import se.iths.Exception.InvalidEntry;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectValidator {

    @Inject
    SubjectService subjectService;

    public void validateName(Subject subject) {
        if (subject.getName().isEmpty() || subject.getName().getBytes().length < 2) {
            throw new InvalidEntry("Subject requires name of min 2 characters");
        }
    }

    public void validateNameString(String name) {
        if (name.isEmpty() || name.getBytes().length < 2) {
            throw new InvalidEntry("Subject requires name of min 2 characters");
        }
    }

    public void verifySubjectExists(Long id) {
        if (subjectService.findSubjectById(id) == null) {
            throw new EntityNotFound("No subject found with ID " + id);
        }
    }

    public void verifyDatabaseNotEmpty() {
        List<Subject> subjectList = subjectService.findAllSubjects();
        if (subjectList.isEmpty()) {
            throw new EntityNotFound("Database contains no subject records");
        }
    }

}
