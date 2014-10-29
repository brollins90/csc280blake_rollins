package edu.neumont.csc280.lab4.user;

/**
 * Created by blakerollins on 10/28/14.
 */
public class RegisterUserModel {

    private User user;
    private String errorMessage;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
