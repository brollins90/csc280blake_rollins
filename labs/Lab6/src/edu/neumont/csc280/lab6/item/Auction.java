package edu.neumont.csc280.lab6.item;

import edu.neumont.csc280.lab6.Money;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "Auction.all", query = "SELECT a FROM Auction a ORDER BY a.title"),
        @NamedQuery(name = "Auction.byId", query = "SELECT a FROM Auction a WHERE a.id = :id ORDER BY a.title"),
        @NamedQuery(name = "Auction.like", query =
                "SELECT a FROM Auction a WHERE UPPER(a.title) like :like or UPPER(a.description) like :like ORDER BY a.title"),
})


@Entity
public class Auction {

    static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String imageUrl;

    @Column
    private Money price;

    @Column
    private int numBids;

    @Column
    private Date endTime;

    @Column
    private Date startTime;

    public Auction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public int getNumBids() {
        return numBids;
    }

    public void setNumBids(int numBids) {
        this.numBids = numBids;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getPrettyStart() {
        return dateFormat.format(startTime);
    }

    public String getPrettyEnd() {
        return dateFormat.format(endTime);
    }

    public String getTimeLeft() {

        String diffString = "";
        Date now = new Date();
        Date start = this.startTime;
        Date end = this.endTime;

        long diff = 0;
        if (start.getTime() > now.getTime()) {
            diff = start.getTime() - now.getTime();
        } else {
            diff = end.getTime() - now.getTime();
        }
        if (diff > 1) {

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            diffString += (diffDays == 0) ? "" : diffDays + " days, ";
            diffString += (diffHours == 0) ? "" : diffHours + " hours, ";
            diffString += (diffMinutes == 0) ? "" : diffMinutes + " minutes, ";
            diffString += (diffSeconds == 0) ? "" : diffSeconds + " seconds";
        } else {
            diffString = "Auction has ended.";
        }
        if (start.getTime() > now.getTime()) {
            diffString += " until auction starts.";
        } else {
            diffString += " left in auction.";
        }
        return diffString;
    }

    public String toJSON() {

        String json = "{ \"id\": \"" + this.id + "\", ";
        json += "\"title\": \"" + this.title + "\", ";
        json += "\"description\": \"" + this.description + "\", ";
        json += "\"current_bid\": \"" + this.price + "\", ";
        json += "\"num_bids\": \"" + this.numBids + "\", ";
        json += "\"start_time\": \"" + this.startTime + "\", ";
        json += "\"end_time\": \"" + this.endTime + "\" }";

        return json;
    }
}
