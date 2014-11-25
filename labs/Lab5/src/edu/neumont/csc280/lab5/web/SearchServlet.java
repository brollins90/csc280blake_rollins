package edu.neumont.csc280.lab5.web;


import edu.neumont.csc280.lab5.item.ItemGetController;
import edu.neumont.csc280.lab5.search.SearchGetController;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SearchServlet extends HttpServlet {

    @Inject
    SearchGetController searchGetController;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView mv = null;
        try {
            String pathInfo = request.getPathInfo();
            pathInfo = (pathInfo == null) ? "/" : pathInfo;

            String[] parts = pathInfo.split("/");
            String searchTermEncoded = (parts.length > 1) ? parts[1] : "";

            System.out.println("get-search");

            mv = searchGetController.search();


        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
            mv = new ModelAndView(null, "500");
        } finally {

            request.setAttribute("model", mv.getModel());
            DispatchServlet.forwardOrRedirect(request, response, mv);

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
