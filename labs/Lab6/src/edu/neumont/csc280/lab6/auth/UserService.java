package edu.neumont.csc280.lab6.auth;

/**
 * Created by blakerollins on 11/12/14.
 */
public interface UserService {
    User login(String username, char[] password);
    User register(String username, char[] password);
}
