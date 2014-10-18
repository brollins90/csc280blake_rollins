package edu.neumont.csc280.lab4;

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
                mv = controller.createItem();
            }
            else if ("delete".equalsIgnoreCase(action)) {
                mv = controller.deleteItem(itemId);
            }
            else if ("retrieve".equalsIgnoreCase(action)) {
                mv = controller.retrieveItem(itemId);
            }
//            else if ("retrievejson".equalsIgnoreCase(action)) {
//                mv = controller.retrieveItemJSON(itemId);
//            }
            else if ("update".equalsIgnoreCase(action)) {
                mv = controller.updateItem(itemId);
            }
            else if ("list".equalsIgnoreCase(action)) {
                mv = controller.getAllItems();
            }
            else {
                System.out.println("bad action");
                mv = new ModelAndView(null, "500");
            }

            request.setAttribute("model", mv.getModel());
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
            mv = new ModelAndView(null, "500");
        } finally {

            String viewLocation = "/WEB-INF/" + mv.getViewName() + ".jsp";
            System.out.println("viewLocation: " + viewLocation);
            RequestDispatcher rd = request.getRequestDispatcher(viewLocation);
            rd.forward(request, response);

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();
            pathInfo = (pathInfo == null) ? "/" : pathInfo;

            String[] parts = pathInfo.split("/");
            String itemId = (parts.length > 1) ? parts[1] : "";
            String action = (parts.length > 2) ? parts[2] : "";

            ModelAndView mv = null;

            System.out.println("post");

            ItemPostController controller = new ItemPostController(request, response);

            if ("create".equalsIgnoreCase(action)) {
                System.out.println("create");
                mv = controller.createItem();

            } else if ("update".equalsIgnoreCase(action)) {
                System.out.println("update");
                mv = controller.updateItem(itemId);

            } else if ("bid".equalsIgnoreCase(action)) {
                System.out.println("bid");
                mv = controller.placeBid(itemId);

            } else {
                System.out.println("bad action, doing get");
                // do get
                doGet(request, response);
            }

            String redirectLocation = request.getRequestURI();
            redirectLocation = redirectLocation.substring(0, redirectLocation.lastIndexOf('/'));
            System.out.println("redirectLocation: " + redirectLocation);
            response.sendRedirect(redirectLocation);

        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }

}
