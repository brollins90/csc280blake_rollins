package edu.neumont.csc280.framework;

/**
 * Throw this exception to cause MainServlet to return a 400
 * 
 * @author jcummings
 *
 */
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}
}
