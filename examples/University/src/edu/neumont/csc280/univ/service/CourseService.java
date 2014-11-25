package edu.neumont.csc280.univ.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.neumont.csc280.univ.model.Course;

@Stateless
public class CourseService {
	@PersistenceContext(name="university")
	EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public void updateStudent(Course c) {
		em.persist(c);
	}
}
