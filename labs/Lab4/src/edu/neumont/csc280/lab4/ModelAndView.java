package edu.neumont.csc280.lab4;

public class ModelAndView {

    private Object model;
    private String view;

    public ModelAndView(Object model, String view) {
        this.model = model;
        this.view = view;
    }

    public Object getModel() {
        return this.model;
    }

    public String getViewName() {
        return this.view;
    }
}
