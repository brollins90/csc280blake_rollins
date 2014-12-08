package edu.neumont.csc280.lab7.web;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blakerollins on 11/12/14.
 */
public class ModelAndView {

    private Object model;
    private String view;
    private List<String> errors;

    public ModelAndView() {
    }

    public ModelAndView(Object model, String view) {
        this.model = model;
        this.view = view;
        this.errors = new ArrayList<>();
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getErrorCount() {
        return errors.size();
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}
