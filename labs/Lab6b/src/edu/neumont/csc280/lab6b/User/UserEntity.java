//package edu.neumont.csc280.lab6b.User;
//
//import javax.persistence.*;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Created by blakerollins on 11/19/14.
// */
//
//@NamedQueries({
//        @NamedQuery(name = "byUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username")
//})
//
//@Entity
//public class UserEntity {
//
//    @Id
//    @Column
//    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
//    private int id;
//
//    @Column(unique = true)
//    private String username;
//
//    @Column
//    private String password;
//
//    @OneToMany(
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
//            mappedBy = "user")
//    private Set<FingerEntity> fingers = new HashSet<>();
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//
//
//    public Set<FingerEntity> getFingers() {
//        return Collections.unmodifiableSet(fingers);
//    }
//
//    public void addFinger(FingerEntity finger) {
//        fingers.add(finger.withUser(this));
//    }
//
//
//}
