package edu.neumont.csc280.lab7.user;


/** An example of the ThreadLocal pattern.
 * 
 * This is just like any other getter/setter POJO with the exception
 * that the property is stored in a wrapper class (ThreadLocal).
 * 
 * ThreadLocal makes so that each thread has its own copy of the property
 * being stored so that when one thread changes it, no other threads notice.
 * 
 * The ThreadLocal pattern is often used in servlet filters when we want
 * some property to be globally visible across a request.  The filter
 * saves the value in here, and it is picked up down the road by other
 * classes (@see edu.neumont.csc280.UserFilter).
 * 
 * @author jcummings
 *
 */
public class CurrentUser {
	private static final ThreadLocal<User> userStorage = new ThreadLocal<User>();
	
	public static User getUser() {
		return userStorage.get();
	}
	
	public static void setUser(User p) {
		userStorage.set(p);
	}
}
