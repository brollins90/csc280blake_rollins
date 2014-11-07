package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.Money;
import edu.neumont.csc280.lab4.ValidationResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Stack;

public class AuctionItem {

    private String id;
    private String title;
    private String description;
    private String imageUrl;

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

        CreateBids();
    }

    public String getId() {
        return this.id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public ValidationResult validateId(String newValue) {
        // TODO
        ValidationResult result = new ValidationResult();
        result.setSuccess(false);
        result.addMessage("Changing the Id is not implemented.");
        return result;
    }


    public String getTitle() {
        return this.title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public ValidationResult validateTitle(String newValue) {
        return new ValidationResult();
    }


    public String getDescription() {
        return this.description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public ValidationResult validateDescription(String newValue) {
        return new ValidationResult();
    }


    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ValidationResult validateImageUrl(String newValue) {
        return new ValidationResult();
    }


    public Money getStartPrice() {
        return this.startPrice;
    }

    public void setStartPrice(Money startPrice) {
        this.startPrice = startPrice;
    }

    public ValidationResult validateStartPrice(Money newValue) {
        ValidationResult result = new ValidationResult();

        if (this.bids.size() > 0) {
            result.setSuccess(false);
            result.addMessage("Start price cannot change since bids have been placed");
        }

        if (newValue.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            result.setSuccess(false);
            result.addMessage("Start price must be greater than 0.0.");
        }

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


    public long getEndTime() {
        return this.endTime;
    }

    protected void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    protected void setEndTime(String endTime) throws NumberFormatException {
        setEndTime(Long.parseLong(endTime));
    }

    public ValidationResult validateEndTime(String newValue) {
        ValidationResult result = new ValidationResult();
        try {
            result.combine(validateEndTime(Long.parseLong(newValue)));
        } catch (NumberFormatException e) {
            result.setSuccess(false);
            result.addMessage(e.getMessage());
        }
        return result;
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


//
//    public String getTimeLeft() {
//
//        String diffString = "";
//
//        Date start = new Date(this.getStartTime());
//        Date end = new Date(this.getEndTime());
//
//        long diff = end.getTime() - new Date().getTime();
//        if (diff > 1) {
//
//            long diffSeconds = diff / 1000 % 60;
//            long diffMinutes = diff / (60 * 1000) % 60;
//            long diffHours = diff / (60 * 60 * 1000) % 24;
//            long diffDays = diff / (24 * 60 * 60 * 1000);
//
//            diffString += (diffDays == 0) ? "" : diffDays + " days, ";
//            diffString += (diffHours == 0) ? "" : diffHours + " hours, ";
//            diffString += (diffMinutes == 0) ? "" : diffMinutes + " minutes, ";
//            diffString += (diffSeconds == 0) ? "" : diffSeconds + " seconds.";
//        } else {
//            diffString = "Time has passed.";
//        }
//        return diffString;
//    }

    private void CreateBids() {
        this.bids = new Stack<>();
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
