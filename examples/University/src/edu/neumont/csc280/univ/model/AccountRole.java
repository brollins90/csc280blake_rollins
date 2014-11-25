package edu.neumont.csc280.univ.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AccountRole")
public class AccountRole {
	@Id
	@Column(name="id")
	@SequenceGenerator(name="account_role_seq", sequenceName="account_role_seq", initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="account_role_seq")
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
