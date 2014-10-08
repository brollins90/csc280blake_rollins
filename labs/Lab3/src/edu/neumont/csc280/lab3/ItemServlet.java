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
        String item = parts[1];
        if (uri.endsWith("/image")) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/item_" + item + ".png");
            rd.forward(request, response);
        } else if(uri.endsWith("/bid")) {
            //RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/item_" + item + ".png");
            doPost(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/item.jsp");
            //RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/item_" + item + ".jsp");
            rd.forward(request, response);
        }
//        response.getWriter().println(uri);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
