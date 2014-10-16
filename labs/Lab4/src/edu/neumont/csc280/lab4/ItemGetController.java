package edu.neumont.csc280.lab4;

import edu.neumont.csc280.lab4.auction.AuctionItem;
import edu.neumont.csc280.lab4.auction.AuctionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
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
        List<AuctionItem> itemIds = manager.getItems();
        ModelAndView mv = new ModelAndView(itemIds, "itemList");
        return mv;
    }

    public ModelAndView retreiveItem(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemView");
        return mv;
    }

    public ModelAndView updateItem(String id) {
        return retreiveItem(id);
    }

    public ModelAndView createItem() {
        //TODO
        return null;
    }

    public ModelAndView deleteItem(String id) {
        //TODO
        return null;
    }
}


