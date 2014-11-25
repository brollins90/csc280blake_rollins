package edu.neumont.csc280.univ.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Account")
@NamedQueries({
	@NamedQuery(name="byUsername", query="SELECT a FROM Account a WHERE a.username like :username")
})
public class Account {
	@Id
	@Column(name="id")
	@SequenceGenerator(name="account_seq", sequenceName="account_seq", initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="account_seq")
	private Long id;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="account", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<AccountRole> roles = new HashSet<>();
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<AccountRole> getRoles() {
		return Collections.unmodifiableSet(roles);
	}
	
	public void addRole(Role user) {
		AccountRole ar = new AccountRole();
		ar.setRole(user);
		ar.setUsername(username);
		roles.add(ar);
	}
	
	public boolean hasRole(Role role) {
		for ( AccountRole accountRole : roles ) {
			if ( accountRole.getRole().equals(role) ) {
				return true;
			}
		}
		return false;
	}
}
