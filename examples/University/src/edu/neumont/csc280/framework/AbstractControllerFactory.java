package edu.neumont.csc280.framework;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A class that respects the UrlMapping annotation on controller classes.
 * @author jcummings
 *
 */
public abstract class AbstractControllerFactory {
	private Map<OperationResourceInfo, ClassResourceInfo> urlMapping;
	
	/**
	 * Scans through the controllers that concrete implementations {@see ConnectionFactory}
	 * provide it.  Looks for the UrlMapping annotation and saves the regex-method pair in a
	 * HashMap.
	 * 
	 * @return
	 */
	public Map<OperationResourceInfo, ClassResourceInfo> getUrlMapping() {
		if ( urlMapping == null ) {
			urlMapping = new LinkedHashMap<OperationResourceInfo, ClassResourceInfo>();
			List<? extends Controller> controllers = getControllers();
			for ( Controller controller : controllers ) {
				Method[] methods = controller.getClass().getSuperclass().getDeclaredMethods();
				for ( Method method : methods ) {
					UrlMapping annotation = method.getAnnotation(UrlMapping.class);
					if ( annotation != null ) {
						urlMapping.put(new OperationResourceInfo(annotation), new ClassResourceInfo(controller, method));
					}
				}
			}
		}
		return urlMapping;
	}
	
	protected abstract List<? extends Controller> getControllers();
	
	/**
	 * Java reflection requires the instance on which to invoke the method as
	 * well as the method itself, so we store them both here.
	 * 
	 * @author jcummings
	 *
	 */
	public static class ClassResourceInfo {
		public final Object instance;
		public final Method method;
		
		public ClassResourceInfo(Object instance, Method method) {
			this.instance = instance;
			this.method = method;
		}
	}
	
	/**
	 * A struct for holding onto the url pattern and the HTTP method (GET or POST).
	 * 
	 * Note that it overrides hashCode and equals so that it can be a key in a Map.
	 * 
	 * @author jcummings
	 *
	 */
	public static class OperationResourceInfo {
		public final Pattern url;
		public final String method;
		
		public OperationResourceInfo(UrlMapping urlMapping) {
			this.url = Pattern.compile(urlMapping.value());
			this.method = urlMapping.method();
		}
		
		public int hashCode() {
			return (method + url.pattern()).hashCode();
		}
		
		public boolean equals(Object obj) {
			if ( obj instanceof OperationResourceInfo ) {
				OperationResourceInfo that = (OperationResourceInfo)obj;
				return this.url.equals(that.url) && this.method.equals(that.method);
			}
			return false;
		}
	}
}
