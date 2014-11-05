package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.Money;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

public class AuctionItem implements Cloneable {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    private AuctionItemDB item;

    public AuctionItem(String id, String title, String description, String imageUrl, String startPriceIn, String startTimeIn, String endTimeIn) {

        Date startDate = null;
        Date endDate = null;
        Money startPrice = null;

        try {
            startDate = dateFormat.parse(startTimeIn);
        } catch (Exception e) {
            throw new AuctionException("invalid start date");
        }

        try {
            endDate = dateFormat.parse(endTimeIn);
        } catch (Exception e) {
            throw new AuctionException("invalid end date");
        }

        try {
            startPrice = Money.dollars(new BigDecimal(startPriceIn));
        } catch (Exception e) {
            throw new AuctionException("invalid start price");
        }


        this.item = new AuctionItemDB(id, "", "", "",
                Money.dollars(Double.parseDouble("0.01")), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);

        item.setTitle(title);
        item.setDescription(description);
        item.setImageUrl(imageUrl);

        item.setStartPrice(startPrice);

        item.setStartTime(startDate.getTime());
        item.setEndTime(endDate.getTime());
    }

//    public AuctionItem(String id, String title, String description, String imageUrl, Money startPrice, long startTime, long endTime) {
//
//        this.item = new AuctionItemDB(id, "", "", "",
//                Money.dollars(Double.parseDouble("0.01")), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
//        //this.item = new AuctionItemDB(id, title, description, imageUrl, startPrice, startTime, endTime);
//
//        item.setTitle(title);
//        item.setDescription(description);
//        item.setImageUrl(imageUrl);
//
//        item.setStartPrice(startPrice);
//
//        item.setStartTime(startTime);
//        item.setEndTime(endTime);
//    }

    public String getId() {
        return this.item.getId();
    }

    protected void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new AuctionException("The id cannot be null.");
        }
        this.item.setId(id);
    }

    public String getTitle() {
        return this.item.getTitle();
    }

    protected void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new AuctionException("The title cannot be null.");
        }
        this.item.setTitle(title);
    }

    public String getImageUrl() {
        return this.item.getImageUrl();
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new AuctionException("The imageUrl cannot be null.");
        }
        this.item.setImageUrl(imageUrl);
    }

    public String getDescription() {
        return this.item.getDescription();
    }

    protected void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new AuctionException("The description cannot be null.");
        }
        this.item.setDescription(description);
    }

    public long getStartTime() {
        return this.item.getStartTime();
    }

    protected void setStartTime(long newValue) {
        Date now = new Date();

        if (newValue + 1 < now.getTime()) {
            throw new AuctionException("Start Time must be later than Now.");
        }

        if (this.item.getBids().size() > 0) {
            throw new AuctionException("Start time cannot change since bids have been placed");
        }
        this.item.setStartTime(newValue);
    }

    public Money getStartPrice() {
        return this.item.getStartPrice();
    }

    protected void setStartPrice(Money newValue) {

        if (this.item.getBids().size() > 0) {
            throw new AuctionException("You cannot change the start price if there are bids");
        }

//        if (newValue <= 0) {
//            throw new AuctionException("The starting price must be greater than 0");
//        }
        this.item.setStartPrice(newValue);
    }

//
//    public ValidationResult validateStartPrice(long newValue) {
//        ValidationResult result = new ValidationResult();
//
//        if (this.item.getBids().size() > 0) {
//            result.setSuccess(false);
//            result.addMessage("You cannot change the start price if there are bids");
//        }
//
//        if (newValue <= 0) {
//            result.setSuccess(false);
//            result.addMessage("The starting price must be greater than 0");
//        }
//
//        return result;
//    }


    public long getEndTime() {
        return this.item.getEndTime();
    }

    protected void setEndTime(long newValue) {
        Date now = new Date();

        if (newValue + 1 < this.item.getStartTime() + 1000 + 60 + 60) {
            throw new AuctionException("End Time must be more than one hour after the start time.");
        }

        if (newValue + 1 < now.getTime()) {
            throw new AuctionException("End Time must be later than Now.");
        }

        if (this.item.getBids().size() > 0) {
            throw new AuctionException("End time cannot change since bids have been placed");
        }

        this.item.setEndTime(newValue);
    }

//
//    public ValidationResult validateEndTime(long newValue) {
//        ValidationResult result = new ValidationResult();
//        Date now = new Date();
//
//        if (newValue + 1 < this.item.getStartTime() + 1000 + 60 + 60) {
//            result.setSuccess(false);
//            result.addMessage("End Time must be more than one hour after the start time.");
//        }
//
//        if (newValue + 1 < now.getTime()) {
//            result.setSuccess(false);
//            result.addMessage("End Time must be later than Now.");
//        }
//
//        if (this.item.getBids().size() > 0) {
//            result.setSuccess(false);
//            result.addMessage("End time cannot change since bids have been placed");
//        }
//
//        System.out.println("validateEndTime: " + result.toJSON());
//        return result;
//    }




    public int getNumBids() {
        return this.item.getBids().size();
    }

    public void placeBid(Bid newBid) {

        if (getNumBids() == 0 || item.getBids().peek().getAmount().getAmount().compareTo(newBid.getAmount().getAmount()) < 1) {
            item.getBids().push(newBid);
        }
    }

    public Money getCurrentPrice() {

        return (getNumBids() > 0) ? item.getBids().peek().getAmount() : this.item.getStartPrice();
    }

    public String toJSON() {

        String json = "{ \"id\": \"" + this.getId() + "\", ";
        json += "\"title\": \"" + this.getTitle() + "\", ";
        json += "\"description\": \"" + this.getDescription() + "\", ";
        json += "\"current_bid\": \"" + this.getCurrentPrice() + "\", ";
        json += "\"num_bids\": \"" + this.getNumBids() + "\", ";
        json += "\"start_time\": \"" + this.getStartTime() + "\", ";
        json += "\"end_time\": \"" + this.getEndTime() + "\" }";

        return json;
    }

    public AuctionItem clone() {
        try {
            return (AuctionItem) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
//
//    private <T extends Comparable<T>> boolean hasValueChanged(T one, T two) {
//        System.out.println("hasValueChanged(" + one + ", " + two + ")");
//        if (one.compareTo(two) == 0) {
//
//            System.out.println("no");
//            return false;
//        }
//        System.out.println("yes");
//        return true;
//    }
}
