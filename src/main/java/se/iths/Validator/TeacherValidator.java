package se.iths.Validator;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class TeacherValidator {

    @Inject
    TeacherService teacherService;

    public boolean validateFirstName(Teacher teacher) {
        if (teacher.getFirstName().isEmpty() || teacher.getFirstName().getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: teacherFirstNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateFirstNameString(String firstName) {
        if (firstName.isEmpty() || firstName.getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: teacherFirstNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateLastName(Teacher teacher) {
        if (teacher.getLastName().isEmpty() || teacher.getLastName().getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: teacherLastNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateLastNameString(String lastName) {
        if (lastName.isEmpty() || lastName.getBytes().length < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: teacherLastNameRequiredMin2Characters")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateEmail(Teacher teacher) {
        if (teacher.getEmail().isEmpty() || teacher.getEmail().isBlank()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: teacherEmailRequired")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validateEmailString(String email) {
        if (email.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: teacherEmailRequired")
                    .type(MediaType.APPLICATION_JSON).build());
        } else return true;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || ((phoneNumber.getBytes().length < 9) || (phoneNumber.getBytes().length > 13))) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("message: invalidTeacherPhoneNumber")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

    public boolean verifyTeacherExists(Long id) {
        if (teacherService.findTeacherById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("message: noTeacherWithId" + id)
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

    public boolean verifyDatabaseNotEmpty() {
        List<Teacher> teacherList = teacherService.findAllTeachers();
        if (teacherList.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("message: databaseContainsNoTeachers")
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        } else return true;
    }

}
