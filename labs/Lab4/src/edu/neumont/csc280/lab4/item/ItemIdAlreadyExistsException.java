package edu.neumont.csc280.lab4.item;

/**
 * Created by blakerollins on 10/29/14.
 */
public class ItemIdAlreadyExistsException extends RuntimeException {

    public ItemIdAlreadyExistsException(String message) {
        super(message);
    }
}
