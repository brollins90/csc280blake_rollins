package edu.neumont.csc280.lab5.web;

import edu.neumont.csc280.lab5.search.SearchModel;

public class ModelAndView {

    private Object model;
//    private SearchModel search;
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
//
//    public SearchModel getSearch() {
//        return search;
//    }
//
//    public void setSearch(SearchModel search) {
//        this.search = search;
//    }
}
