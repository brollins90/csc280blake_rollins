package edu.neumont.csc280.lab3.nubay;

import java.util.Date;


public class Bid {

    private Date date;
    private final String itemId;
    private final Money bidAmount;
    private final String user;

    public Bid(String itemID, Double amount, String user) {
        this(itemID, Money.dollars(amount), user);
    }

    public Bid(String itemID, Money amount, String user) {
        this.itemId = itemID;
        //this.date = System.nanoTime();
        this.bidAmount = amount;
        this.user = user;
    }

    public Money getAmount() {
        return this.bidAmount;
    }

    public Date getBidDate() {
        return this.date;
    }

    public String getItemId() {
        return this.itemId;
    }

    public String getUser() {
        return this.user;
    }
}
