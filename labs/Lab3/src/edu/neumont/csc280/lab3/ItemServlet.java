package edu.neumont.csc280.lab3;

import edu.neumont.csc280.lab3.nubay.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ItemServlet extends HttpServlet {

    private static final AuctionManager manager = new ArrayAuctionManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String uri = request.getPathInfo();

//


            // check for a null path
            uri = (uri == null) ? "/" : uri;

            String[] parts = uri.split("/");
            String itemID = (parts.length == 0) ? "" : parts[1];

            AuctionItem thisItem = manager.getItem(itemID);
            if (thisItem != null)
            {
                request.setAttribute("id", itemID);
                request.setAttribute("currentBid", manager.getItem(itemID).getCurrentPrice().toString());

                if (uri.endsWith("/image")) {
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/images/item_" + itemID + ".png");
                     rd.forward(request, response);
                } else if (uri.endsWith("/bid")) {
                    response.sendRedirect(request.getRequestURI().substring(0, request.getRequestURI().length() - 4));
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/itemTemplate.jsp");
                    rd.forward(request, response);
                }
            } // item does not exist
            else {
                response.setStatus(404);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/404.jsp");
                rd.forward(request, response);
            }
        }
        catch (Exception e) {
            response.setStatus(500);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/500.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String itemID = request.getParameter("id");
        Money incAmount;
        try{
            incAmount = Money.dollars(new BigDecimal(request.getParameter("incrementBid")));
        } catch (Exception e) {
            incAmount = Money.dollars(0.01d);
        }
//        BigDecimal current = manager.getItem(itemID).getCurrentPrice();

        Money newAmount = Money.dollars(manager.getItem(itemID).getCurrentPrice().getAmount().add(incAmount.getAmount()));

        manager.getItem(itemID).addBid(new Bid(itemID, newAmount, "Blake"));

                response.sendRedirect(request.getRequestURI().substring(0, request.getRequestURI().length() - 4));
    }
}
