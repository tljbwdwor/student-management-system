package se.iths.Validator;

import se.iths.entity.Teacher;
import se.iths.Exception.EntityNotFound;
import se.iths.Exception.InvalidEntry;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import java.util.List;

public class TeacherValidator {

    @Inject
    TeacherService teacherService;

    public void validateFirstName(Teacher teacher) {
        if (teacher.getFirstName().isEmpty() || teacher.getFirstName().getBytes().length < 2) {
            throw new InvalidEntry("Teacher Requires First Name Of Min 2 Characters");
        }
    }

    public void validateFirstNameString(String firstName) {
        if (firstName.isEmpty() || firstName.getBytes().length < 2) {
            throw new InvalidEntry("Teacher Requires First Name Of Min 2 Characters");
        }
    }

    public void validateLastName(Teacher teacher) {
        if (teacher.getLastName().isEmpty() || teacher.getLastName().getBytes().length < 2) {
            throw new InvalidEntry("Teacher Requires Last Name Of Min 2 Characters");
        }
    }

    public void validateLastNameString(String lastName) {
        if (lastName.isEmpty() || lastName.getBytes().length < 2) {
            throw new InvalidEntry("Teacher Requires Last Name Of Min 2 Characters");
        }
    }

    public void validateEmail(Teacher teacher) {
        if (teacher.getEmail().isEmpty() || teacher.getEmail().isBlank()) {
            throw new InvalidEntry("Teacher Requires Email");
        }
    }

    public void validateEmailString(String email) {
        if (email.isEmpty()) {
            throw new InvalidEntry("Teacher Requires Valid Email");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || ((phoneNumber.getBytes().length < 9) || (phoneNumber.getBytes().length > 13))) {
            throw new InvalidEntry("Phone Number Must Be Between 9 - 13 Characters");
        }
    }

    public void verifyTeacherExists(Long id) {
        if (teacherService.findTeacherById(id) == null) {
            throw new EntityNotFound("No Teacher Found With Id_ " + id);
        }
    }

    public void verifyDatabaseNotEmpty() {
        List<Teacher> teacherList = teacherService.findAllTeachers();
        if (teacherList.isEmpty()) {
            throw new EntityNotFound("Database Contains No Teacher Records");
        }
    }

}
