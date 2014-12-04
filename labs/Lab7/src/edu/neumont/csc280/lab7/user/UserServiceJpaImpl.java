package edu.neumont.csc280.lab7.user;


import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
@Local(UserService.class)
public class UserServiceJpaImpl implements UserService {

    @PersistenceContext(name = "csc280")
    private EntityManager em;


    public UserServiceJpaImpl() {
    }

    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        return em.createNamedQuery("User.byUsername", User.class)
                .setParameter("username", "%" + username + "%").getSingleResult();
    }

    @Override
    public void updateUser(User user) {
//        // here is where we would check the password, etc.
//
//        // add the appropriate role
//        if ( !user.hasRole(Role.USER) ) {
//            user.addRole(Role.USER);
//        }

        em.persist(user);

    }
}
