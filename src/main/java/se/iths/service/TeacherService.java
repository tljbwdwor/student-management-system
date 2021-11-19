package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public List<Teacher> findAllTeachers() {
        return entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class)
                .getResultList();
    }

    public List<Teacher> findTeacherByLastName(String lastName) {
        return entityManager.createQuery("SELECT t FROM Teacher t WHERE t.lastName LIKE :lastName", Teacher.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    public Teacher findTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public void replaceTeacher(Teacher teacher) {
        entityManager.merge(teacher);
    }

    public Teacher updateFirstName(Long id, String firstName) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        foundTeacher.setFirstName(firstName);
        return entityManager.merge(foundTeacher);
    }

    public Teacher updateLastName(Long id, String lastName) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        foundTeacher.setLastName(lastName);
        return entityManager.merge(foundTeacher);
    }

    public Teacher updateEmail(Long id, String email) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        foundTeacher.setEmail(email);
        return entityManager.merge(foundTeacher);
    }

    public Teacher updatePhoneNumber(Long id, String phoneNumber) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        foundTeacher.setPhoneNumber(phoneNumber);
        return entityManager.merge(foundTeacher);
    }

    public void deleteTeacher(Long id) {
        Teacher teacher = entityManager.find(Teacher.class, id);
        entityManager.remove(teacher);
    }

    public void addTeacherToSubject(Long teacher_id, Long subject_id) {
        Teacher teacher = entityManager.find(Teacher.class, teacher_id);
        Subject subject = entityManager.find(Subject.class, subject_id);
        teacher.addSubject(subject);
    }

}
