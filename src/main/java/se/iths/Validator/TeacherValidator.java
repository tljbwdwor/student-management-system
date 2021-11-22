package se.iths.Validator;

import se.iths.Exception.EntityNotFound;
import se.iths.Exception.InvalidEntry;
import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import java.util.List;

public class TeacherValidator {

    @Inject
    TeacherService teacherService;

    public boolean validateFirstName(Teacher teacher) {
        if (teacher.getFirstName().isEmpty() || teacher.getFirstName().getBytes().length < 2) {
            throw new InvalidEntry("Teacher Requires First Name Of Min 2 Characters");
        } else return true;
    }

    public boolean validateFirstNameString(String firstName) {
        if (firstName.isEmpty() || firstName.getBytes().length < 2) {
            throw new InvalidEntry("Teacher Requires First Name Of Min 2 Characters");
        } else return true;
    }

    public boolean validateLastName(Teacher teacher) {
        if (teacher.getLastName().isEmpty() || teacher.getLastName().getBytes().length < 2) {
            throw new InvalidEntry("Teacher Requires Last Name Of Min 2 Characters");
        } else return true;
    }

    public boolean validateLastNameString(String lastName) {
        if (lastName.isEmpty() || lastName.getBytes().length < 2) {
            throw new InvalidEntry("Teacher Requires Last Name Of Min 2 Characters");
        } else return true;
    }

    public boolean validateEmail(Teacher teacher) {
        if (teacher.getEmail().isEmpty() || teacher.getEmail().isBlank()) {
            throw new InvalidEntry("Teacher Email Required");
        } else return true;
    }

    public boolean validateEmailString(String email) {
        if (email.isEmpty()) {
            throw new InvalidEntry("Teacher Email Required");
        } else return true;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || ((phoneNumber.getBytes().length < 9) || (phoneNumber.getBytes().length > 13))) {
            throw new InvalidEntry("Teacher Phone Number Must Be Between 9 And 13 Chars");
        } else return true;
    }

    public boolean verifyTeacherExists(Long id) {
        if (teacherService.findTeacherById(id) == null) {
            throw new EntityNotFound("No Teacher Found With Id-" +id);
        } else return true;
    }

    public boolean verifyDatabaseNotEmpty() {
        List<Teacher> teacherList = teacherService.findAllTeachers();
        if (teacherList.isEmpty()) {
            throw new EntityNotFound("No Teacher Records In Database");
        } else return true;
    }

}
