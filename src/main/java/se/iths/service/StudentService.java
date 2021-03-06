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
        return entityManager.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    public List<Student> findStudentByLastName(String lastName) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName LIKE :lastName", Student.class)
                .setParameter("lastName", lastName)
                .getResultList();
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
        return entityManager.merge(foundStudent);
    }

    public Student updateLastName(Long id, String lastName) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setLastName(lastName);
        return entityManager.merge(foundStudent);
    }

    public Student updateEmail(Long id, String email) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setEmail(email);
        return entityManager.merge(foundStudent);
    }

    public Student updatePhoneNumber(Long id, String phoneNumber) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setPhoneNumber(phoneNumber);
        return entityManager.merge(foundStudent);
    }

    public void deleteStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

}
