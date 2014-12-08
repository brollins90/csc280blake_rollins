package edu.neumont.csc280.lab7.web;

import edu.neumont.csc280.lab7.user.CurrentUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatchServlet {

    public static void forwardOrRedirect(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) throws ServletException, IOException {
        request.setAttribute("model", mav.getModel());
        request.setAttribute("user", CurrentUser.getUser());

        String viewName = mav.getView();

        if (mav.getErrorCount() > 0) {
            request.setAttribute("errors", mav.getErrors());
        }

        if (viewName.startsWith("redirect:")) {
            String redirectLocation = viewName.substring(9, viewName.length());
            response.sendRedirect(redirectLocation);

        } else {
            String viewLocation;
            viewLocation = (viewName.startsWith("/") ? "" : "/") + viewName;
            RequestDispatcher rd = request.getRequestDispatcher(viewLocation);
            rd.forward(request, response);
        }
    }
}
