package edu.neumont.csc280.lab5.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by blakerollins on 11/12/14.
 */
public class ServletShared {

    protected static void forwardOrRedirect(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) throws ServletException, IOException {
        request.setAttribute("model", mav.getModel());
        String viewName = mav.getViewName();
        System.out.println("viewName: " + viewName);

        if (viewName.startsWith("redirect:")) {
            String redirectLocation = viewName.substring(9, viewName.length());
            System.out.println("redirectLocation: " + redirectLocation);
            response.sendRedirect(redirectLocation);

        } else {
            String viewLocation = "/WEB-INF/" + viewName + ".jsp";
            RequestDispatcher rd = request.getRequestDispatcher(viewLocation);
            rd.forward(request, response);
        }
    }
}
