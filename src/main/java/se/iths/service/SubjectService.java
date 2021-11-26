package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public List<Subject> findAllSubjects() {
        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class)
                .getResultList();
    }

    public Subject findSubjectById(Long id) {
        return entityManager.find(Subject.class, id);
    }

    public void updateSubject(Subject subject) {
        entityManager.merge(subject);
    }

    public void deleteSubject(Long id) {
        Subject subject = findSubjectById(id);
        entityManager.remove(subject);
    }

    public void removeTeacherFromSubject(Long id) {
        Subject subject = findSubjectById(id);
        subject.setTeacher(null);
    }

    public void removeStudentFromSubject(Long id, Long student_id) {
        Subject subject = findSubjectById(id);
        Student student = entityManager.find(Student.class, student_id);

        student.getEnrolledCourses().remove(subject);
        subject.getEnrolledStudents().remove(student);

        /*List<Subject> subjects = student.getEnrolledCourses();
        subjects.remove(subject);
        student.setEnrolledCourses(subjects);
        entityManager.merge(student_id);

        List<Student> students = subject.getEnrolledStudents();
        students.remove(student);
        subject.setEnrolledStudents(students);
        entityManager.merge(id);*/
    }

}
