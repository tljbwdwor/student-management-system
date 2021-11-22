package se.iths.Validator;

import se.iths.entity.Student;
import se.iths.Exception.InvalidEntry;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
public class StudentValidator {

    @Inject
    StudentService studentService;

    public boolean validateFirstName(Student student) {
        if (student.getFirstName().isEmpty() || student.getFirstName().getBytes().length < 2) {
            throw new InvalidEntry("Student Requires First Name Of Min 2 Characters");
        } else return true;
    }

    public boolean validateFirstNameString(String firstName) {
        if (firstName.isEmpty() || firstName.getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: studentFirstNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateLastName(Student student) {
        if (student.getLastName().isEmpty() || student.getLastName().getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: studentLastNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateLastNameString(String lastName) {
        if (lastName.isEmpty() || lastName.getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: studentLastNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateEmail(Student student) {
        if (student.getEmail().isEmpty() || student.getEmail().isBlank()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: studentEmailRequired")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateEmailString(String email) {
        if (email.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: studentEmailRequired")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || ((phoneNumber.getBytes().length < 9) || (phoneNumber.getBytes().length > 13))) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: invalidStudentPhoneNumber")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

    public boolean verifyStudentExists(Long id) {
        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("message: noStudentWithId" + id)
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

    public boolean verifyDatabaseNotEmpty() {
        List<Student> studentList = studentService.findAllStudents();
        if (studentList.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("message: databaseContainsNoStudents")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

}
