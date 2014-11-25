package edu.neumont.csc280.univ.service;

import java.util.List;

import edu.neumont.csc280.univ.model.Student;

public interface StudentService {
	public List<Student> findAll();
	
	public Student findById(Long id);
	
	public void updateStudent(Student s);
}
