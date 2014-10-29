package edu.neumont.csc280.lab4.user;

/**
 * Created by blakerollins on 10/27/14.
 */
public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
