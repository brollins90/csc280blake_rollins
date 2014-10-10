package edu.neumont.csc280.lab3.nubay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Stack;

public class AuctionItem {

    private Stack<Bid> bids;
    private String id;
    private String imageUrl;
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;

//    public AuctionItem() {
//        CreateBids();
//    }

    public AuctionItem(String id, String imageUrl, String name, String description) {

        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
        CreateBids();

    }

    private void CreateBids() {
        this.bids = new Stack<>();
        this.bids.add(new Bid(this.id, 0.0d, "Default"));
    }

    public String getId() {
        return this.id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return  this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getDescription(String description) {
        return this.description;
    }

    private  void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    private void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    private void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getNumBids() {
        return this.bids.size();
    }

    public boolean addBid(Bid newBid) {

        if (bids.peek().getAmount().getAmount().compareTo(newBid.getAmount().getAmount()) < 1) {
            bids.push(newBid);
            return true;
        }
        return false;
    }

    public Money getCurrentPrice() {
        return bids.peek().getAmount();
    }
}
