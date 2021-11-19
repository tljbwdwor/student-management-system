package se.iths.Util;


import se.iths.entity.Student;
import se.iths.entity.Subject;

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

        Subject subject1 = new Subject("Music", new ArrayList<>());
        Subject subject2 = new Subject("Particle Physics", new ArrayList<>());

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);

        entityManager.persist(subject1);
        entityManager.persist(subject2);

        /*student1.addSubject(subject1);
        student1.addSubject(subject2);
        student2.addSubject(subject1);
        student3.addSubject(subject2);
        student4.addSubject(subject2);
        student4.addSubject(subject1);*/

    }

}
