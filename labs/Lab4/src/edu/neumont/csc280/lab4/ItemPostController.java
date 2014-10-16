package edu.neumont.csc280.lab4;

import edu.neumont.csc280.lab4.auction.AuctionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public ModelAndView placeBid(String id) {
        //TODO
        return null;
    }
}
