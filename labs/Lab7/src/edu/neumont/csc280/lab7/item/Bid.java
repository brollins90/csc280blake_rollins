package edu.neumont.csc280.lab7.item;//package edu.neumont.csc280.lab6.item;
//
//import edu.neumont.csc280.lab7.Money;
//
//import java.util.Date;
//
//
//public class Bid {
//
//    private Date date;
//    private final String itemId;
//    private final Money bidAmount;
//    private final String user;
//
//    public Bid(String itemID, Double amount, String user) {
//        this(itemID, Money.dollars(amount), user);
//    }
//
//    public Bid(String itemID, Money amount, String user) {
//        this.itemId = itemID;
//        this.bidAmount = amount;
//        this.user = user;
//    }
//
//    public Money getAmount() {
//        return this.bidAmount;
//    }
//
//    public Date getBidDate() {
//        return this.date;
//    }
//
//    public String getItemId() {
//        return this.itemId;
//    }
//
//    public String getUser() {
//        return this.user;
//    }
//}