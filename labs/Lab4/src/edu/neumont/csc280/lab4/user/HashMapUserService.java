package edu.neumont.csc280.lab4.user;

import edu.neumont.csc280.lab4.user.User;
import edu.neumont.csc280.lab4.user.UserService;
import edu.neumont.csc280.lab4.user.UsernameAlreadyExistsException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by blakerollins on 10/27/14.
 */
public class HashMapUserService implements UserService {
    private Map<Long, User> users = new HashMap<>();
    private Set<String> usernames = new HashSet<>();


    @Override
    public User createUser(String username, String password) {
        User user = new User(username, password);
        if ( !usernames.contains(username)) {
            users.put(user.getId(), user);
            usernames.add(user.getUsername());
            return user;
        }
        throw new UsernameAlreadyExistsException("That username is not unique.");
    }

}
