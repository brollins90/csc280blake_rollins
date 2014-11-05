package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.Money;

import java.util.Stack;

/**
 * Created by blakerollins on 11/4/14.
 */
public class AuctionItemDB {

    private String description;
    private String id;
    private String imageUrl;
    private String title;

    private Money startPrice;

    private long endTime;
    private long startTime;

    protected Stack<Bid> bids;

    public AuctionItemDB() {
        this.bids = new Stack<>();
    }

    public AuctionItemDB(String id, String title, String description, String imageUrl, Money startPrice, long startTime, long endTime) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;

        this.startPrice = startPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bids = new Stack<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Money getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Money startPrice) {
        this.startPrice = startPrice;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Stack<Bid> getBids() {
        return bids;
    }

    public void setBids(Stack<Bid> bids) {
        this.bids = bids;
    }

    public int getNumBids() {
        return bids.size();
    }
}
