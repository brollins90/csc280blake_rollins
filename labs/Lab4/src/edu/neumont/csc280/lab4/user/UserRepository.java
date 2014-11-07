package edu.neumont.csc280.lab4.user;

/**
 * Created by blakerollins on 11/6/14.
 */
public class UserRepository {
    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static User getUser() {
        return userHolder.get();
    }

    public static void setUser(User user) {
        userHolder.set(user);
    }
}
