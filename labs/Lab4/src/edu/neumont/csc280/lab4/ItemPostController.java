package edu.neumont.csc280.lab4;

import edu.neumont.csc280.lab4.auction.AuctionItem;
import edu.neumont.csc280.lab4.auction.AuctionManager;
import edu.neumont.csc280.lab4.auction.Bid;
import edu.neumont.csc280.lab4.auction.Money;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

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

    public ModelAndView updateItem(String id) {
        //TODO
        return null;
    }

    public ModelAndView placeBid(String id) {String itemID = request.getParameter("id");
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
