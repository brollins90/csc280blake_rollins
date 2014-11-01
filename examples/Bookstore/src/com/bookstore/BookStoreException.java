package com.bookstore;

/**
 * Created by blakerollins on 10/31/14.
 */
public class BookstoreException extends RuntimeException {
    public BookstoreException(Exception e) {
        super(e);
    }

    public BookstoreException(String s) {
        super(s);
    }
}
