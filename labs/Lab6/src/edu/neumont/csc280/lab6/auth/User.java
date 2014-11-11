package edu.neumont.csc280.lab6.auth;

/**
 * Created by blakerollins on 11/10/14.
 */
public class User {

    private final String username;
    private final char[] password;
    private final String firstName;
    private final String lastName;
    private final String suffix;

    public User(String username, char[] password, String firstName, String lastName, String suffix) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.suffix = suffix;
    }
}
