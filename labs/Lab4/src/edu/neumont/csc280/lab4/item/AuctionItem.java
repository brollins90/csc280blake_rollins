package edu.neumont.csc280.lab4.item;

import java.util.Date;
import java.util.Stack;

public class AuctionItem {

    private String description;
    private String id;
    private String imageUrl;
    private String title;

    private Money startPrice;

    private long endTime;
    private long startTime;

    private Stack<Bid> bids;

    public AuctionItem(String id) {
        this.id = id;
        this.description = "Default description.";
        this.imageUrl = "/img/default.png";
        this.title = "Default title";

        this.startPrice = Money.dollars(0.01d);

        this.startTime = new Date().getTime();
        this.endTime = startTime + 7 * 24 * 60 * 60 * 1000;

        this.bids = new Stack<>();
    }

    public String getId() {
        return this.id;
    }

//    protected void setId(String id) {
//        // TODO
//        this.id = id;
//    }

    public ValidationResult validateId(String newValue) {
        // TODO
        ValidationResult result = new ValidationResult();
//        result.setSuccess(false);
//        result.addMessage("Changing the Id is not implemented.");
        return result;
    }


    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ValidationResult validateImageUrl(String newValue) {
        // TODO
        ValidationResult result = new ValidationResult();
//        result.setSuccess(false);
//        result.addMessage("Changing the ImageUrl is not implemented.");
        return result;
    }


    public String getTitle() {
        return this.title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public ValidationResult validateTitle(String newValue) {
        // TODO
        ValidationResult result = new ValidationResult();
//        result.setSuccess(false);
//        result.addMessage("Changing the title is not implemented.");
        return result;
    }


    public String getDescription() {
        return this.description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public ValidationResult validateDescription(String newValue) {
        // TODO
        ValidationResult result = new ValidationResult();
//        result.setSuccess(false);
//        result.addMessage("Changing the description is not implemented.");
        return result;
    }


    public long getStartTime() {
        return this.startTime;
    }

    protected void setStartTime(long startTime) {
        this.startTime = startTime;
    }


    public ValidationResult validateStartTime(long newValue) {
        ValidationResult result = new ValidationResult();
        Date now = new Date();

        if (newValue + 1 < now.getTime()) {
            result.setSuccess(false);
            result.addMessage("Start Time must be later than Now.");
        }

        if (this.bids.size() > 0) {
            result.setSuccess(false);
            result.addMessage("Start time cannot change since bids have been placed");
        }

        System.out.println("validateStartTime: " + result.toJSON());
        return result;
    }


    public Money getStartPrice() {
        return this.startPrice;
    }

    protected void setStartPrice(Money startPrice) {
        this.startPrice = startPrice;
    }


    public ValidationResult validateStartPrice(long newValue) {
        ValidationResult result = new ValidationResult();

        if (this.bids.size() > 0) {
            result.setSuccess(false);
            result.addMessage("You cannot change the start price if there are bids");
        }

        if (newValue <= 0) {
            result.setSuccess(false);
            result.addMessage("The starting price must be greater than 0");
        }

        return result;
    }


    public long getEndTime() {
        return this.endTime;
    }

    protected void setEndTime(long endTime) {
        this.endTime = endTime;
    }


    public ValidationResult validateEndTime(long newValue) {
        ValidationResult result = new ValidationResult();
        Date now = new Date();

        if (newValue + 1 < this.startTime + 1000 + 60 + 60) {
            result.setSuccess(false);
            result.addMessage("End Time must be more than one hour after the start time.");
        }

        if (newValue + 1 < now.getTime()) {
            result.setSuccess(false);
            result.addMessage("End Time must be later than Now.");
        }

        if (this.bids.size() > 0) {
            result.setSuccess(false);
            result.addMessage("End time cannot change since bids have been placed");
        }

        System.out.println("validateEndTime: " + result.toJSON());
        return result;
    }


    public int getNumBids() {
        return this.bids.size();
    }

    public void placeBid(Bid newBid) {

        if (getNumBids() == 0 || bids.peek().getAmount().getAmount().compareTo(newBid.getAmount().getAmount()) < 1) {
            bids.push(newBid);
        }
    }

    public Money getCurrentPrice() {

        return (getNumBids() > 0) ? bids.peek().getAmount() : startPrice;
    }

    public String toJSON() {

        String json = "{ \"id\": \"" + this.id + "\", ";
        json += "\"title\": \"" + this.title + "\", ";
        json += "\"description\": \"" + this.description + "\", ";
        json += "\"current_bid\": \"" + this.getCurrentPrice() + "\", ";
        json += "\"num_bids\": \"" + this.getNumBids() + "\", ";
        json += "\"start_time\": \"" + this.startTime + "\", ";
        json += "\"end_time\": \"" + this.endTime + "\" }";

        return json;
    }
}
