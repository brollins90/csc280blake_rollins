package edu.neumont.csc280.lab3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/item/*")
public class ItemServlet extends HttpServlet {

    private static final BidManager bids = new ArrayBidManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getPathInfo();
        RequestDispatcher rd;

        // check for a null path
        uri = (uri == null) ? "/" : uri;

        String[] parts = uri.split("/");
        String itemID = (parts.length == 0) ? "" : parts[1];

        request.setAttribute("id", itemID);
//        Double currentBid = bids.getCurrentPrice(itemID);
//        currentBid = (currentBid == null) ? defaultBid : currentBid;

        request.setAttribute("currentBid", bids.getCurrentPrice(itemID));

        if (uri.endsWith("/image")) {
            rd = request.getRequestDispatcher("/WEB-INF/images/item_" + itemID + ".png");
            rd.forward(request, response);
        } else if (uri.endsWith("/bid")) {
            response.sendRedirect(request.getRequestURI().substring(0, request.getRequestURI().length() - 4));
        } else {
            rd = request.getRequestDispatcher("/WEB-INF/item.jsp");
            rd.forward(request, response);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String itemID = request.getParameter("id");
        Double incAmount;
        try{
            incAmount = Double.parseDouble(request.getParameter("incrementBid"));
        } catch (Exception e) {
            incAmount = 0.01d;
        }
        Double current = bids.getCurrentPrice(itemID);

        Double newAmount = current + incAmount;

        bids.placeBid(itemID, newAmount, "Blake");

        response.sendRedirect(request.getRequestURI().substring(0, request.getRequestURI().length() - 4));
    }
}
