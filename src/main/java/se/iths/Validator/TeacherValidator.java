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
            throw new InvalidEntry("Teacher requires first name of min 2 characters");
        }
    }

    public void validateFirstNameString(String firstName) {
        if (firstName.isEmpty() || firstName.getBytes().length < 2) {
            throw new InvalidEntry("Teacher requires first name of min 2 characters");
        }
    }

    public void validateLastName(Teacher teacher) {
        if (teacher.getLastName().isEmpty() || teacher.getLastName().getBytes().length < 2) {
            throw new InvalidEntry("Teacher requires last name of min 2 characters");
        }
    }

    public void validateLastNameString(String lastName) {
        if (lastName.isEmpty() || lastName.getBytes().length < 2) {
            throw new InvalidEntry("Teacher requires last name of min 2 characters");
        }
    }

    public void validateEmail(Teacher teacher) {
        if (teacher.getEmail().isEmpty() || teacher.getEmail().isBlank()) {
            throw new InvalidEntry("Teacher requires email");
        }
    }

    public void validateEmailString(String email) {
        if (email.isEmpty()) {
            throw new InvalidEntry("Teacher requires valid email");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || ((phoneNumber.getBytes().length < 9) || (phoneNumber.getBytes().length > 13))) {
            throw new InvalidEntry("Phone number must be between 9 - 13 characters");
        }
    }

    public void verifyTeacherExists(Long id) {
        if (teacherService.findTeacherById(id) == null) {
            throw new EntityNotFound("No teacher found with ID " + id);
        }
    }

    public void verifyDatabaseNotEmpty() {
        List<Teacher> teacherList = teacherService.findAllTeachers();
        if (teacherList.isEmpty()) {
            throw new EntityNotFound("Database contains no teacher records");
        }
    }

}
