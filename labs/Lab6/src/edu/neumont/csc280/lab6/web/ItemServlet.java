package edu.neumont.csc280.lab6.web;

import edu.neumont.csc280.lab6.item.ItemGetController;
import edu.neumont.csc280.lab6.item.ItemPostController;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ItemServlet extends HttpServlet {

    @Inject
    ItemGetController itemGetController;
    @Inject
    ItemPostController itemPostController;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView mv = null;
        try {
            String pathInfo = request.getPathInfo();
            pathInfo = (pathInfo == null) ? "/" : pathInfo;

            String[] parts = pathInfo.split("/");
            String itemIdString = (parts.length > 1) ? parts[1] : "";
            String action = (parts.length > 2) ? parts[2] : "";

            System.out.println("get-item");

            action = ("".equalsIgnoreCase(itemIdString)) ? "list" : action;
            action = ("".equalsIgnoreCase(action) && !"".equalsIgnoreCase(itemIdString)) ? "retrieve" : action;

            Long itemId = null;
            try {
                itemId = Long.parseLong(itemIdString);
            } catch (Exception e) {

            }

            System.out.println(action);
            if ("build".equalsIgnoreCase(action)) {
                mv = itemGetController.build();

            } else if ("create".equalsIgnoreCase(action)) {
                mv = itemGetController.createItem();

            } else if ("delete".equalsIgnoreCase(action)) {
                mv = itemGetController.deleteItem(itemId);

            } else if ("json".equalsIgnoreCase(action)) {
                mv = itemGetController.retrieveItemJSON(itemId);

            } else if ("retrieve".equalsIgnoreCase(action)) {
                mv = itemGetController.retrieveItem(itemId);

            } else if ("update".equalsIgnoreCase(action)) {
                mv = itemGetController.updateItem(itemId);

            } else if ("list".equalsIgnoreCase(action)) {
                mv = itemGetController.getAllItems();

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

            DispatchServlet.forwardOrRedirect(request, response, mv);

        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView mv = null;
        try {
            String pathInfo = request.getPathInfo();
            pathInfo = (pathInfo == null) ? "/" : pathInfo;

            String[] parts = pathInfo.split("/");
            String itemIdString = (parts.length > 1) ? parts[1] : "";
            String action = (parts.length > 1) ? parts[1] : ""; // for an update/create, the action is the first field because the id doesnt exist
            action = (parts.length > 2) ? parts[2] : action;

            itemIdString = (itemIdString.equals(action)) ? null : itemIdString;

            Long itemId = null;
            try {
                itemId = Long.parseLong(itemIdString);
            } catch (Exception e) {

            }

            System.out.println("post");

            if ("delete".equalsIgnoreCase(action)) {
                System.out.println("delete");
                mv = itemPostController.deleteItem(itemId);

            } else if ("update".equalsIgnoreCase(action)) {
                System.out.println("update");
                mv = itemPostController.processAuctionForm(itemId);

            } else if ("bid".equalsIgnoreCase(action)) {
                System.out.println("bid");
                mv = itemPostController.placeBid(itemId);

            } else {
                System.out.println("bad action, doing get");
                // do get
                doGet(request, response);
            }


        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        } finally {

            DispatchServlet.forwardOrRedirect(request, response, mv);
        }
    }

}
