package edu.neumont.csc280.univ.model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class Course {
	@Id
	@Column(name="id")
	@SequenceGenerator(name="course_seq", sequenceName="course_seq")
	@GeneratedValue(generator="course_seq", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(mappedBy="courses")
	private Set<Student> students = new HashSet<Student>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void addStudent(Student s) {
		students.add(s);
	}

}
