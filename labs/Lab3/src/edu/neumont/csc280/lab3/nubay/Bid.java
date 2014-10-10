package edu.neumont.csc280.lab3.nubay;

import java.util.Date;


public class Bid {

    private String itemId;
    private Date date;
    private Double bidAmount;
    private String user;

    public Bid(String itemID, Double amount, String user) {
        this.itemId = itemID;
        //this.date = System.nanoTime();
        this.bidAmount = amount;
        this.user = user;
    }

    public Double getAmount() {
        return this.bidAmount;
    }
    public Date getBidDate() {
        return this.date;
    }
    public String getItemId(){
        return this.itemId;
    }
    public String getUser() {return this.user; }
}
