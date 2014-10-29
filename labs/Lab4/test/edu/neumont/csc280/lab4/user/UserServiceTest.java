package edu.neumont.csc280.lab4.user;

import edu.neumont.csc280.lab4.user.HashMapUserService;
import edu.neumont.csc280.lab4.user.User;
import edu.neumont.csc280.lab4.user.UserService;
import edu.neumont.csc280.lab4.user.UsernameAlreadyExistsException;
import org.junit.Assert;
import org.junit.Test;

/**
* Created by blakerollins on 10/27/14.
*/
public class UserServiceTest {
    private UserService us = new HashMapUserService();

    @Test
    public void testCreateUser() {
        User u = us.createUser("bobs", "yeruncle");
        Assert.assertNotNull(u);
        Assert.assertEquals("bobs", u.getUsername());
        Assert.assertEquals("yeruncle", u.getPassword());
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testCreateDuplicateUser() {
        us.createUser("one", "two");
        try{
            us.createUser("one", "two");
            Assert.fail("Should have thrown an exception already.");
        } catch (UsernameAlreadyExistsException e) {
// todo
        }
    }



}
