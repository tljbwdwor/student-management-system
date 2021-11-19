package se.iths.service;

import se.iths.entity.Student;

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
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: firstNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateFirstNameString(String firstName) {
        if (firstName.isEmpty() || firstName.getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: firstNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateLastName(Student student) {
        if (student.getLastName().isEmpty() || student.getLastName().getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: lastNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateLastNameString(String lastName) {
        if (lastName.isEmpty() || lastName.getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: lastNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateEmail(Student student) {
        if (student.getEmail().isEmpty() || student.getEmail().isBlank()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: emailRequired")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateEmailString(String email) {
        if (email.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: emailRequired")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || ((phoneNumber.getBytes().length < 9) || (phoneNumber.getBytes().length > 13))) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: invalidPhoneNumber")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

    public boolean verifyStudentExists(Long id) {
        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("message: noRecordForId" + id)
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

    public boolean verifyDatabaseNotEmpty() {
        List<Student> studentList = studentService.findAllStudents();
        if (studentList.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("message: databaseContainsNoRecords")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

}
