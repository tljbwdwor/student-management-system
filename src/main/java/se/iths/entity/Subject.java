package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty
    @Size(min = 2)
    private String name;
    @ManyToMany
    private List<Student> enrolledStudents = new ArrayList<>();
    @ManyToOne
    private Teacher teacher;


    public Subject() {}

    public Subject(@NotEmpty @Size(min = 2) String name, Teacher teacher, List<Student> enrolledStudents) {
        this.name = name;
        this.teacher = teacher;
        this.enrolledStudents = enrolledStudents;
    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
