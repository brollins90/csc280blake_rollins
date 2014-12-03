//package edu.neumont.csc280.lab6b.User;
//
//import org.junit.Test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//import static org.junit.Assert.*;
//
//public class UserServiceHsqlImplTest {
//
//    @Test
//    public void testLoginAndRegister() {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
//        EntityManager em = emf.createEntityManager();
//        UserServiceHsqlImpl s = new UserServiceHsqlImpl(em);
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        UserEntity registered = s.register("bobs", "yeruncle");
//        System.out.println("Created user");
//        tx.rollback();
//
//    }
//}
