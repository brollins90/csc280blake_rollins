package edu.neumont.csc280.lab3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by blakerollins on 10/7/14.
 */
@WebServlet("/item/*")
public class ItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getPathInfo();
        // if uri is not null
        String[] parts = uri.split("/");
        String itemID = parts[1];

        request.setAttribute("id", itemID);

        RequestDispatcher rd;
        if (uri.endsWith("/image")) {
            rd = request.getRequestDispatcher("/WEB-INF/item_" + itemID + ".png");
            rd.forward(request, response);
        } else if(uri.endsWith("/bid")) {
        } else {
            rd = request.getRequestDispatcher("/WEB-INF/item.jsp");
            rd.forward(request, response);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
