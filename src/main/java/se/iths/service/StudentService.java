package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
        entityManager.persist(student);
    }

    public List<Student> findAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public void replaceStudent(Student student) {
        entityManager.merge(student);
    }

    public Student updateFirstName(Long id, String firstName) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setFirstName(firstName);
        return entityManager.find(Student.class, id);
    }

    public Student updateLastName(Long id, String lastName) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setLastName(lastName);
        return entityManager.find(Student.class, id);
    }

    public Student updateEmail(Long id, String email) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setEmail(email);
        return entityManager.find(Student.class, id);
    }

    public Student updatePhone(Long id, String phone) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setPhoneNumber(phone);
        return entityManager.find(Student.class, id);
    }

    public void deleteStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

}
