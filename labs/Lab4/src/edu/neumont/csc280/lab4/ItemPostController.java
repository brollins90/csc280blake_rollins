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

        String itemTitle = request.getParameter("item_title");
        String itemDescription = request.getParameter("item_description");
        String itemStartBid = request.getParameter("item_start_bid");
        String itemStartTime = request.getParameter("item_start_time");
        String itemEndTime = request.getParameter("item_end_time");
        String itemImageUrl = request.getParameter("item_image_url");

        if (! item.getTitle().equals(itemTitle)) {
            manager.updateItemTitle(id,itemTitle);
        }
        if (! item.getDescription().equals(itemDescription)) {
            manager.updateItemDescription(id, itemDescription);
        }
        long startTimeLong = 0;
        try { startTimeLong = Long.parseLong(itemStartTime); } catch (Exception e) {}
        if (! item.getStartTime().equals(startTimeLong)) {
            manager.updateItemStartTime(id, startTimeLong);
        }
        long endTimeLong = 0;
        try { endTimeLong = Long.parseLong(itemEndTime); } catch (Exception e) {}
        if (! item.getEndTime().equals(endTimeLong)) {
            manager.updateItemStartTime(id, endTimeLong);
        }
        if (! item.getImageUrl().equals(itemImageUrl)) {
            manager.updateItemImageUrl(id, itemImageUrl);
        }

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
}
