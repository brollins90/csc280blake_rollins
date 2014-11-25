package edu.neumont.csc280.framework;

public class ModelAndView {
	private final Object model;
	private final String viewName;
	private final boolean isRedirect;
	
	public ModelAndView(Object model, String viewName) {
		this(model, viewName, false);
	}
	
	public ModelAndView(Object model, String viewName, boolean isRedirect) {
		this.model = model;
		this.viewName = viewName;
		this.isRedirect = isRedirect;
	}
	
	public Object getModel() {
		return model;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public boolean isRedirect() {
		return isRedirect;
	}
}
