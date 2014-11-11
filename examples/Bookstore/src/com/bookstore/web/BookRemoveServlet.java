package com.bookstore.web;

import com.bookstore.BookRepository;
import com.bookstore.InMemory;
import com.bookstore.JDBC;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book/remove")
public class BookRemoveServlet extends HttpServlet {

    @Inject @InMemory
    private BookRepository bookRepo;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        bookRepo.removeBook(id);

        /* Redirect to book-form */
        getServletContext().getRequestDispatcher("/book/").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
