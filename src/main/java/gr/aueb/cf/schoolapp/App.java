package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class App {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("school8PU");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        // Select active teachers - JPQL
//        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t WHERE active = true", Teacher.class);
//        List<Teacher> teachers = query.getResultList();
//        teachers.forEach(System.out::println);

// Criteria API - Select active teachers
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> query1 = cb.createQuery(Teacher.class);
        Root<Teacher> teacher = query1.from(Teacher.class);
        query1.select(teacher)
                .where(
                        cb.isTrue(teacher.get("active"))
                );

        List<Teacher> teachers = em.createQuery(query1).getResultList();
        teachers.forEach(System.out::println);

    }
}