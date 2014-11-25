package edu.neumont.csc280.univ.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Book")
public class Book {
	@Id
	@Column(name="id")
	@SequenceGenerator(name="book_seq", sequenceName="book_seq")
	@GeneratedValue(generator="book_seq", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
