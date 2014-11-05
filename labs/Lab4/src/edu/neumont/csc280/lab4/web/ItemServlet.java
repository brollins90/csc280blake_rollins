package edu.neumont.csc280.lab4.web;

import edu.neumont.csc280.lab4.item.ItemGetController;
import edu.neumont.csc280.lab4.item.ItemPostController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView mv = null;
        try {
            String pathInfo = request.getPathInfo();
            pathInfo = (pathInfo == null) ? "/" : pathInfo;

            String[] parts = pathInfo.split("/");
            String itemId = (parts.length > 1) ? parts[1] : "";
            String action = (parts.length > 2) ? parts[2] : "";


            System.out.println("get");

            ItemGetController controller = new ItemGetController(request, response);

            action = ("".equalsIgnoreCase(itemId)) ? "list" : action;
            action = ("".equalsIgnoreCase(action) && !"".equalsIgnoreCase(itemId)) ? "retrieve" : action;

            System.out.println(action);
            if ("create".equalsIgnoreCase(action)) {
                mv = controller.beginCreateItemWorkflow();
            } else if ("delete".equalsIgnoreCase(action)) {
                mv = controller.beginDeleteItemWorkflow(itemId);
            } else if ("json".equalsIgnoreCase(action)) {
                mv = controller.retrieveItemJSON(itemId);
            } else if ("retrieve".equalsIgnoreCase(action)) {
                mv = controller.retrieveItem(itemId);
            } else if ("update".equalsIgnoreCase(action)) {
                mv = controller.beginUpdateItemWorkflow(itemId);
            } else if ("list".equalsIgnoreCase(action)) {
                mv = controller.getAllItems();
            } else {
                System.out.println("bad action");
                mv = new ModelAndView(null, "500");
            }

            request.setAttribute("model", mv.getModel());
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
            mv = new ModelAndView(null, "500");
        } finally {

            forwardOrRedirect(request, response, mv);

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView mv = null;
        try {
            String pathInfo = request.getPathInfo();
            pathInfo = (pathInfo == null) ? "/" : pathInfo;

            String[] parts = pathInfo.split("/");
            String itemId = (parts.length > 1) ? parts[1] : "";
            String action = (parts.length > 1) ? parts[1] : ""; // for an update, the action is the first field
            action = (parts.length > 2) ? parts[2] : action;


            System.out.println("post");

            ItemPostController controller = new ItemPostController(request, response);

            if ("create".equalsIgnoreCase(action)) {
                System.out.println("create");
                mv = controller.commitItemFormWorkflow(null);

            } else if ("update".equalsIgnoreCase(action)) {
                System.out.println("update");
                mv = controller.commitItemFormWorkflow(itemId);

            } else if ("bid".equalsIgnoreCase(action)) {
                System.out.println("bid");
                mv = controller.placeBid(itemId);

            } else {
                System.out.println("bad action, doing get");
                // do get
                doGet(request, response);
            }


        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        } finally {

            forwardOrRedirect(request, response, mv);
        }
    }

    private void forwardOrRedirect(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) throws ServletException, IOException {
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
