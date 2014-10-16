package edu.neumont.csc280.lab4;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("processRequest");
            String pathInfo = request.getPathInfo();
            pathInfo = (pathInfo == null) ? "/" : pathInfo;

            String[] parts = pathInfo.split("/");
            String method = request.getMethod();
            String itemId = (parts.length > 1) ? parts[1] : "";
            String action = (parts.length > 2) ? parts[2] : "";

            ModelAndView mv = null;

            if ("get".equalsIgnoreCase(method)) {
                System.out.println("get");

                ItemGetController controller = new ItemGetController(request, response);

                action = ("".equalsIgnoreCase(action) && !"".equalsIgnoreCase(itemId)) ? "retrieve" : action;

                if ("retrieve".equalsIgnoreCase(action)) {
                    System.out.println("retrieve");
                    mv = controller.retreiveItem(itemId);
                } else {
                    System.out.println("action not retrieve");
                    // i am pretty sure we need to list all items here
                    mv = controller.getAllItems();
                }

            } else if ("post".equalsIgnoreCase(method)) {
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

            } else {
                System.out.println("not supported");
                // not supported
            }

            request.setAttribute("model", mv.getModel());
            String viewLocation = "/WEB-INF/" + mv.getViewName() + ".jsp";
            System.out.println("viewLocation: " + viewLocation);
            RequestDispatcher rd = request.getRequestDispatcher(viewLocation);
            rd.forward(request, response);

        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

    }
}
