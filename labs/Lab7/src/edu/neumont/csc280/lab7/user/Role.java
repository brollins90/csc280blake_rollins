package edu.neumont.csc280.lab7.user;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name="Role.byUser", query="SELECT r FROM Role r WHERE r.user = :user")
})

@Entity
@Table(name="Roles")
public class Role {

    private String rolename;
    private User user;

    @Id
    @Column(name = "rolename")
    public String getRolename() {
        return rolename;
    }
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @ManyToOne
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "rolename",referencedColumnName="rolename"),
            inverseJoinColumns = @JoinColumn(name = "username",referencedColumnName="username")
    )
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
