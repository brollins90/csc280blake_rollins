package edu.neumont.csc280.lab6.auth;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by blakerollins on 11/10/14.
 */

@NamedQueries({
        @NamedQuery(name = "byUsername", query = "SELECT u FROM User u WHERE u.username = :username")
})

@Entity
public class User {

    @Id
    @Column
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private char[] password;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            mappedBy = "user")
    private Set<Finger> fingers = new HashSet<>();


    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }
}


//    private final String firstName;
//    private final String lastName;
//    private final String suffix;

//    public User(String username, char[] password, String firstName, String lastName, String suffix) {
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.suffix = suffix;
//    }
