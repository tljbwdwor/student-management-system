package se.iths.Util;


import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {

        Student student1 = new Student("Adam","Adamsson","email@1.com","0192837465",new ArrayList<>());
        Student student2 = new Student("Brian", "Bobcat", "email@2.com","9283746501", new ArrayList<>());
        Student student3 = new Student("Christine", "Christensson", "email@3.com", "3847561029", new ArrayList<>());
        Student student4 = new Student("David", "Davidsson", "email@4.com", "7465019283", new ArrayList<>());

        Teacher teacher1 = new Teacher("Guthrie", "Govan", "guthrie@govan.co.uk", "0165738293764", new ArrayList<>());
        Teacher teacher2 = new Teacher("John","Petrucci","john@dt.com","02937467283", new ArrayList<>());

        Subject subject1 = new Subject("Advanced shredding", new Teacher(), new ArrayList<>());
        Subject subject2 = new Subject("Moving Beyond Pentatonics", new Teacher(), new ArrayList<>());
        Subject subject3 = new Subject("Sweep 101", new Teacher(), new ArrayList<>());

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);

        entityManager.persist(teacher1);
        entityManager.persist(teacher2);

        entityManager.persist(subject1);
        entityManager.persist(subject2);
        entityManager.persist(subject3);

        student1.addSubject(subject1);
        student1.addSubject(subject2);
        student2.addSubject(subject1);
        student3.addSubject(subject2);
        student3.addSubject(subject3);
        student4.addSubject(subject2);
        student4.addSubject(subject1);
        student4.addSubject(subject3);

        teacher1.addSubject(subject1);
        teacher2.addSubject(subject2);
        teacher1.addSubject(subject3);

    }

}
