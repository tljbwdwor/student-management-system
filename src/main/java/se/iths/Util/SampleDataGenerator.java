package se.iths.Util;


import se.iths.entity.Student;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {

        Student student1 = new Student("Adam","Adamsson","email@1.com","0192837465");
        Student student2 = new Student("Brian", "Bobcat", "email@2.com","9283746501");
        Student student3 = new Student("Christine", "Christensson", "email@3.com", "3847561029");
        Student student4 = new Student("David", "Davidsson", "email@4.com", "7465019283");

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);

    }

}
