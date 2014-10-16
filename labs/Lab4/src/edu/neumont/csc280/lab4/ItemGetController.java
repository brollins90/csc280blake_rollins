package edu.neumont.csc280.lab4;

import edu.neumont.csc280.lab4.auction.AuctionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ItemGetController {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private AuctionManager manager;

    public ItemGetController(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        manager = (AuctionManager)request.getServletContext().getAttribute("manager");
    }

    public ModelAndView getAllItems() {
        List<String> itemIds = manager.itemIds();
        ModelAndView mv = new ModelAndView(itemIds, "itemList");
        return mv;
    }

    public ModelAndView retreiveItem(String id) {
        //TODO
        return null;
    }

    public ModelAndView updateItem(Long id) {
        //TODO
        return null;
    }

    public ModelAndView createItem() {
        //TODO
        return null;
    }

    public ModelAndView deleteItem(Long id) {
        //TODO
        return null;
    }
}


