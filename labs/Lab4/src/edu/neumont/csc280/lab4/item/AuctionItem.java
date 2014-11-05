package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.Money;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

public class AuctionItem extends AuctionItemDB implements Cloneable {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");


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

        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
        this.setImageUrl(imageUrl);

        this.setStartPrice(startPrice);

        this.setStartTime(startDate.getTime());
        this.setEndTime(endDate.getTime());
    }

    public AuctionItem(String id, String title, String description, String imageUrl, Money startPrice, long startTime, long endTime) {
        super(id, title, description, imageUrl, startPrice, startTime, endTime);
    }

    @Override
    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new AuctionException("The id cannot be null.");
        }
        super.setId(id);
    }

    @Override
    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new AuctionException("The title cannot be null.");
        }
        super.setTitle(title);
    }

    @Override
    public void setImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new AuctionException("The imageUrl cannot be null.");
        }
        super.setImageUrl(imageUrl);
    }

    @Override
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new AuctionException("The description cannot be null.");
        }
        super.setDescription(description);
    }

    @Override
    public void setStartTime(long newValue) {
        Date now = new Date();

        if (newValue + 1 < now.getTime()) {
            throw new AuctionException("Start Time must be later than Now.");
        }

        if (this.getBids().size() > 0) {
            throw new AuctionException("Start time cannot change since bids have been placed");
        }
        super.setStartTime(newValue);
    }

    @Override
    public void setStartPrice(Money newValue) {

        if (this.getBids().size() > 0) {
            throw new AuctionException("You cannot change the start price if there are bids");
        }

//        if (newValue <= 0) {
//            throw new AuctionException("The starting price must be greater than 0");
//        }
        super.setStartPrice(newValue);
    }

    @Override
    public void setEndTime(long newValue) {
        Date now = new Date();

        if (newValue + 1 < this.getStartTime() + 1000 + 60 + 60) {
            throw new AuctionException("End Time must be more than one hour after the start time.");
        }

        if (newValue + 1 < now.getTime()) {
            throw new AuctionException("End Time must be later than Now.");
        }

        if (this.getBids().size() > 0) {
            throw new AuctionException("End time cannot change since bids have been placed");
        }

        super.setEndTime(newValue);
    }
//
    public void placeBid(Bid newBid) {

        if (getNumBids() == 0 || bids.peek().getAmount().getAmount().compareTo(newBid.getAmount().getAmount()) < 1) {
            bids.push(newBid);
        }
    }
//
    public Money getCurrentPrice() {

        return (getNumBids() > 0) ? bids.peek().getAmount() : getStartPrice();
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
