package com.bookstore;

import java.util.List;

/**
 * Created by blakerollins on 10/30/14.
 */
public interface BookRepository {

    Book lookupById(String id);

    void addBook(String title, String description, String price, String pubDate);

    void updateBook(String id, String title, String description, String price, String pubDate);

    void removeBook(String id);

    List<Book> listBooks();

    List<Book> listBooksSortByPriceAsc();

    List<Book> listBooksSortByTitleAsc();

    List<Book> listBooksSortByTitleDesc();

    List<Book> listBooksSortByPriceDesc();

    List<Book> listBooksSortByPubDateDesc();

    List<Book> listBooksSortByPubDateAsc();
}
