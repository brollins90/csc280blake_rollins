package edu.neumont.csc280.lab7.user;

import java.util.*;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name="User.byUsername", query="SELECT u FROM User u WHERE u.username like :username")
})

@Entity
@Table(name="Users")
public class User {

    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();

    @Id
    @Column(name="username")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user")
    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    //
//    public void addRole(Role user) {
//        AccountRole ar = new AccountRole();
//        ar.setRole(user);
//        ar.setUsername(username);
//        roles.add(ar);
//    }
//
//    public boolean hasRole(Role role) {
//        for ( AccountRole accountRole : roles ) {
//            if ( accountRole.getRole().equals(role) ) {
//                return true;
//            }
//        }
//        return false;
//    }
}
