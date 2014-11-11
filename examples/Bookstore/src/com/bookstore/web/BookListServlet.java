package com.bookstore.web;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.Book;
import com.bookstore.BookRepository;
import com.bookstore.InMemory;
import com.bookstore.JDBC;

@WebServlet("/book/")
public class BookListServlet extends HttpServlet {

    @Inject @InMemory
    private BookRepository bookRepo;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String field = request.getParameter("field");
        String order = request.getParameter("order");

        List<Book> books = null;

        if ("asc".equals(order) && "title".equals(field)) {
            books = bookRepo.listBooksSortByTitleAsc();
        } else if ("desc".equals(order) && "title".equals(field)) {
            books = bookRepo.listBooksSortByTitleDesc();
        } else if ("asc".equals(order) && "price".equals(field)) {
            books = bookRepo.listBooksSortByPriceAsc();
        } else if ("desc".equals(order) && "price".equals(field)) {
            books = bookRepo.listBooksSortByPriceDesc();
        } else if ("asc".equals(order) && "pubDate".equals(field)) {
            books = bookRepo.listBooksSortByPubDateAsc();
        } else if ("desc".equals(order) && "pubDate".equals(field)) {
            books = bookRepo.listBooksSortByPubDateDesc();
        } else {
            books = bookRepo.listBooks();
        }

        request.setAttribute("books", books);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/book-list.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
