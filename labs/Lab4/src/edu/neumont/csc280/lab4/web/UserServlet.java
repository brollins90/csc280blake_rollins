package edu.neumont.csc280.lab4.web;

import edu.neumont.csc280.lab4.user.HashMapUserService;
import edu.neumont.csc280.lab4.user.UserGetController;
import edu.neumont.csc280.lab4.user.UserPostController;
import edu.neumont.csc280.lab4.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by blakerollins on 10/27/14.
 */
public class UserServlet extends HttpServlet {

    private static final UserService us = new HashMapUserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserGetController ugc = new UserGetController(us);

        ModelAndView mav = ugc.beginRegisterUserWorkflow();
        request.setAttribute("model", mav.getModel());
        RequestDispatcher rd = request.getRequestDispatcher(mav.getViewName());
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserPostController upc = new UserPostController(us);
// redirect:
        ModelAndView mav = upc.commitRegisterUserWorkflow(request, response);
        request.setAttribute("model", mav.getModel());
        RequestDispatcher rd = request.getRequestDispatcher(mav.getViewName());
        rd.forward(request, response);
    }
}
