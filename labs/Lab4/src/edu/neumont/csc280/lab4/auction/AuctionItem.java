package edu.neumont.csc280.lab4.auction;

import java.util.Date;
import java.util.Stack;

public class AuctionItem {

    private String id;
    private String imageUrl;
    private String title;
    private String description;
    private Date startTime;
    private Date endTime;

    private Stack<Bid> bids;
//    public AuctionItem() {
//        CreateBids();
//    }

    public AuctionItem(String id) {
        this.id = id;
        CreateBids();
        this.startTime = new Date();
        this.endTime = new Date();
        this.endTime.setTime((this.startTime.getTime() + 1 * 24 * 60 * 60 * 1000));
    }

    public AuctionItem(String id, String imageUrl, String title, String description) {
        this(id);
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
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
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return this.title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    protected void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    protected void setStartTime(long startTime) {
        this.startTime = new Date(startTime);
    }

    public Date getEndTime() {
        return this.endTime;
    }

    protected void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    protected void setEndTime(long endTime) {
        this.endTime = new Date(endTime);
    }

    public String getTimeLeft() {

        String diffString = "";
        long diff = this.endTime.getTime() - new Date().getTime();
        if (diff > 1) {

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            diffString += (diffDays == 0) ? "" : diffDays + " days, ";
            diffString += (diffHours == 0) ? "" : diffHours + " hours, ";
            diffString += (diffMinutes == 0) ? "" : diffMinutes + " minutes, ";
            diffString += (diffSeconds == 0) ? "" : diffSeconds + " seconds.";
        } else {
            diffString = "Time has passed.";
        }
        return diffString;
    }

    public int getNumBids() {
        return this.bids.size();
    }

    public void placeBid(Bid newBid) {

        if (bids.peek().getAmount().getAmount().compareTo(newBid.getAmount().getAmount()) < 1) {
            bids.push(newBid);
        }
    }

    public Money getCurrentPrice() {
        return bids.peek().getAmount();
    }
}
