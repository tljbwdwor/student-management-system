package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id private Long id;
    @NotEmpty
    @Size(min = 2)
    private String firstName;
    @NotEmpty
    @Size(min = 2)
    private String lastName;
    @NotEmpty
    private String email;
    @Size(min = 9, max = 13)
    private String phoneNumber;
    @ManyToMany(mappedBy = "enrolledStudents", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Subject> enrolledCourses = new ArrayList<>();

    public Student() {}

    public Student(@NotEmpty @Size(min = 2) String firstName, @NotEmpty @Size(min = 2) String lastName, @NotEmpty String email, @Size(min = 9, max = 13) String phoneNumber, List<Subject> enrolledCourses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enrolledCourses = enrolledCourses;
    }

    public void addSubject(Subject subject) {
        enrolledCourses.add(subject);
        subject.addStudent(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonbTransient
    public List<Subject> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Subject> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

}
