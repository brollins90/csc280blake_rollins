package edu.neumont.csc280.lab4.user;

/**
 * Created by blakerollins on 10/27/14.
 */
public class User {

    private static long counter = 0;
    private Long id;
    private final String username;
    private final String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.id = ++counter;
    }

    public Long getId() {return this.id;}
    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}

}
