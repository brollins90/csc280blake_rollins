package edu.neumont.csc280.lab7.web;


import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * A listener that uses the ThreadLocal pattern to make the request object
 * available for dependency injection.
 * 
 * The @Produces annotation is the opposite of the @Inject annotation.
 * 
 * @author jcummings
 *
 */
@WebListener
public class RequestInjectingServletRequestListener implements
		ServletRequestListener {
	private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();
	
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		requestHolder.remove();
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		requestHolder.set((HttpServletRequest)arg0.getServletRequest());
	}
	
	@Produces
	@RequestScoped
	public HttpServletRequest getInstance() {
		return requestHolder.get();
	}

}
