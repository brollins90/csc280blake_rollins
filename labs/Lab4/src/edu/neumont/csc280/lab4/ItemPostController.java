package edu.neumont.csc280.lab4;

import edu.neumont.csc280.lab4.auction.AuctionItem;
import edu.neumont.csc280.lab4.auction.AuctionManager;
import edu.neumont.csc280.lab4.auction.Bid;
import edu.neumont.csc280.lab4.auction.Money;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

public class ItemPostController {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private AuctionManager manager;

    public ItemPostController(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        manager = (AuctionManager)request.getServletContext().getAttribute("manager");
    }

    public ModelAndView createItem() {
        //TODO
        return null;
    }

    public ModelAndView deleteItem(String id) {
        //TODO
        return null;
    }

    public ModelAndView updateItem(String id) {
        AuctionItem item = manager.getItem(id);

        String itemStartBid = request.getParameter("item_start_bid");
//
//        // Title
//        String itemTitle = request.getParameter("item_title");
//        if (! item.getTitle().equals(itemTitle)) {
//            manager.updateItemTitle(id,itemTitle);
//        }
//
//        // Description
//        String itemDescription = request.getParameter("item_description");
//        if (! item.getDescription().equals(itemDescription)) {
//            manager.updateItemDescription(id, itemDescription);
//        }

        // Start Time
        String itemStartTime = request.getParameter("item_start_time");
        if (hasValueChanged(String.valueOf(item.getStartTime()), itemStartTime)) {

            if (item.validateStartTime(itemStartTime).getSuccess()) {
                manager.updateItemStartTime(id, itemStartTime);

                // To make sure that the end time is always after the start time, update the end time to 7
                // days after the start time.  If they want to change the end time in the same instance
                // of this POST then that will overwrite this change since it runs afterward anyway.
                manager.updateItemEndTime(id, Long.valueOf(itemStartTime) + 7 * 24 * 60 * 60 * 1000);
            }
        }
//
//        // End Time
//        String itemEndTime = request.getParameter("item_end_time");
//        long endTimeLong = 0;
//        try { endTimeLong = Long.parseLong(itemEndTime); } catch (Exception e) {}
//        if (item.getEndTime() != (endTimeLong)) {
//            manager.updateItemStartTime(id, endTimeLong);
//        }
//
//        // Image URL
//        String itemImageUrl = request.getParameter("item_image_url");
//        if (! item.getImageUrl().equals(itemImageUrl)) {
//            manager.updateItemImageUrl(id, itemImageUrl);
//        }

        return null;
    }

    public ModelAndView placeBid(String id) {
        String itemID = id;
        String bidAmount = request.getParameter("bidAmount");

        Money incAmount;
        try {
            switch(bidAmount){
                default:
                case "one":
                    incAmount = Money.dollars(1.00d);
                    break;
                case "five":
                    incAmount = Money.dollars(5.00d);
                    break;
                case "ten":
                    incAmount = Money.dollars(10.00d);
                    break;
                case "custom":
                    incAmount = Money.dollars(new BigDecimal(request.getParameter("incrementBid")));
                    break;
            }
        } catch (Exception e) {
            incAmount = Money.dollars(0.01d);
        }
//        BigDecimal current = manager.getItem(itemID).getCurrentPrice();

        Money newAmount = Money.dollars(manager.getItem(itemID).getCurrentPrice().getAmount().add(incAmount.getAmount()));

        AuctionItem item = manager.getItem(itemID);

        item.placeBid(new Bid(itemID, newAmount, "Blake"));

        return new ModelAndView(item, "itemView");
    }

    private<T extends Comparable<T>> boolean hasValueChanged (T one, T two) {
        System.out.println("hasValueChanged(" + one + ", " + two + ")");
        if (one.compareTo(two) == 0) {

            System.out.println("no");
            return false;
        }
        System.out.println("yes");
        return true;
    }

}
