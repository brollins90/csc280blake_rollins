package edu.neumont.csc280.lab6b.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by blakerollins on 11/19/14.
 */
public class UserServiceHsqlImpl implements UserService{
    private EntityManager entityManager;

    public UserServiceHsqlImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public UserEntity login(String username, String password) {
        TypedQuery<UserEntity> query = entityManager.createNamedQuery("byUsername", UserEntity.class);
        query.setParameter("username", username);
        UserEntity user = first(query.getResultList());

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new IllegalArgumentException("Bad username or password");
    }

    @Override
    public UserEntity register(String username, String password) {
        TypedQuery<UserEntity> query = entityManager.createNamedQuery("byUsername", UserEntity.class);
        query.setParameter("username", username);
        UserEntity user = first(query.getResultList());

        if (user != null) {
            throw new IllegalArgumentException("username already exists");
        }

        user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);

        entityManager.persist(user);
        return user;
    }

    private <T> T first(Collection<T> col) {
        return col == null ? null : col.iterator().next();
    }
}
