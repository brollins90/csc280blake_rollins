//package edu.neumont.csc280.lab6.auth;
//
//import org.junit.Test;
//
//import javax.persistence.*;
//
//public class UserServiceHsqlImplTest {
//
//    @Test
//    public void testLoginAndRegister() {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("universityUnit");
//        EntityManager em = emf.createEntityManager();
//        UserServiceHsqlImpl s = new UserServiceHsqlImpl(em);
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        User registered = s.register("bobs", "yeruncle".toCharArray());
//        System.out.println("Created user");
//        tx.rollback();
//
//    }
//
//}
