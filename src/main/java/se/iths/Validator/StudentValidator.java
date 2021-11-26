package se.iths.Validator;

import se.iths.entity.Student;
import se.iths.Exception.EntityNotFound;
import se.iths.Exception.InvalidEntry;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentValidator {

    @Inject
    StudentService studentService;

    public void validateFirstName(Student student) {
        if (student.getFirstName().isEmpty() || student.getFirstName().getBytes().length < 2) {
            throw new InvalidEntry("Student requires first name of min 2 characters");
        }
    }

    public void validateFirstNameString(String firstName) {
        if (firstName.isEmpty() || firstName.getBytes().length < 2) {
            throw new InvalidEntry("Student requires first name of min 2 characters");
        }
    }

    public void validateLastName(Student student) {
        if (student.getLastName().isEmpty() || student.getLastName().getBytes().length < 2) {
            throw new InvalidEntry("Student requires last name of min 2 characters");
        }
    }

    public void validateLastNameString(String lastName) {
        if (lastName.isEmpty() || lastName.getBytes().length < 2) {
            throw new InvalidEntry("Student requires last name of min 2 characters");
        }
    }

    public void validateEmail(Student student) {
        if (student.getEmail().isEmpty() || student.getEmail().isBlank()) {
            throw new InvalidEntry("Student requires email");
        }
    }

    public void validateEmailString(String email) {
        if (email.isEmpty()) {
            throw new InvalidEntry("Student requires email");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || ((phoneNumber.getBytes().length < 9) || (phoneNumber.getBytes().length > 13))) {
            throw new InvalidEntry("Phone number must be between 9 - 13 characters");
        }
    }

    public void verifyStudentExists(Long id) {
        if (studentService.findStudentById(id) == null) {
            throw new EntityNotFound("No student found with ID " + id);
        }
    }

    public void verifyDatabaseNotEmpty() {
        List<Student> studentList = studentService.findAllStudents();
        if (studentList.isEmpty()) {
            throw new EntityNotFound("Database contains no student records");
        }
    }

}
