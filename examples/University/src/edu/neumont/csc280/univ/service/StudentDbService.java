package edu.neumont.csc280.univ.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.neumont.csc280.univ.model.Student;

/**
 * Note the @Local annotation for EJB interface-view.
 * 
 * @author jcummings
 *
 */
@Stateless
@LocalBean
@Local(StudentService.class)
public class StudentDbService implements StudentService {
	private static final Logger logger = LoggerFactory.getLogger(StudentDbService.class);
	
	@PersistenceContext(name="university")
	EntityManager em;
	
	public List<Student> findAll() {
		logger.info("Querying for Students...");
		return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
	}
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public void updateStudent(Student s) {
		em.persist(s);
	}
}