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

    public boolean validateName(Subject subject) {
        if (subject.getName().isEmpty() || subject.getName().getBytes().length < 2) {
            throw new InvalidEntry("Subject Requires Name Of Min 2 Characters");
        } else return true;
    }

    public boolean validateNameString(String name) {
        if (name.isEmpty() || name.getBytes().length < 2) {
            throw new InvalidEntry("Subject Requires Name Of Min 2 Characters");
        } else return true;
    }

    public boolean verifySubjectExists(Long id) {
        if (subjectService.findSubjectById(id) == null) {
            throw new EntityNotFound("No Subject Found With Id_" + id);
        } else return true;
    }

    public boolean verifyDatabaseNotEmpty() {
        List<Subject> subjectList = subjectService.findAllSubjects();
        if (subjectList.isEmpty()) {
            throw new EntityNotFound("Database Contains No Subject Records");
        } else return true;
    }

}
