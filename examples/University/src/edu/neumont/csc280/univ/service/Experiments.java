package edu.neumont.csc280.univ.service;

public class Experiments {
	private String firstName;
	private String lastName;
	
	public Experiments(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
}
