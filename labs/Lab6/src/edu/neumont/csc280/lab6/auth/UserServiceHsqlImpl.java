package edu.neumont.csc280.lab6.auth;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by blakerollins on 11/12/14.
 */
public class UserServiceHsqlImpl implements UserService {
    private EntityManager entityManager;

    public UserServiceHsqlImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public User login(String username, char[] password) {
        TypedQuery<User> query = entityManager.createNamedQuery("byUsername", User.class);
        query.setParameter("username", username);
        User user = first(query.getResultList());
//        User user = query.getResultList().get(0);

        List<User> users = query.getResultList();
        if (!users.isEmpty() && Arrays.equals(users.get(0).getPassword(), password)) {
            return users.get(0);
        }
        throw new IllegalArgumentException("Bad username or password");
    }

    @Override
    public User register(String username, char[] password) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        User user = first(query.getResultList());
//        User user = query.getResultList().get(0);

        if (user != null) {
            throw new IllegalArgumentException("username already exists");
        }

        user = new User(username, password);


        entityManager.persist(user);
        return user;
    }

    private <T> T first(Collection<T> col) {
        return col == null ? null : col.iterator().next();
    }
}
