package edu.neumont.csc280.lab4;

import edu.neumont.csc280.lab4.auction.AuctionItem;
import edu.neumont.csc280.lab4.auction.AuctionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

public class ItemGetController {

    private AuctionManager manager;

    public ItemGetController(HttpServletRequest request, HttpServletResponse response)
    {
        manager = (AuctionManager)request.getServletContext().getAttribute("manager");
    }

    public ModelAndView getAllItems() {
        List<AuctionItem> itemIds = manager.getItems();
        ModelAndView mv = new ModelAndView(itemIds, "itemList");
        return mv;
    }

    public ModelAndView createItem() {
        AuctionItem item = manager.getItem(manager.createItem());
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemCreate");
        return mv;
    }

    public ModelAndView deleteItem(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemDelete");
        return mv;
    }

    public ModelAndView retreiveItem(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemView");
        return mv;
    }

//    public ModelAndView retreiveItemJSON(String id) {
//        //TODO
//        return null;
//    }

    public ModelAndView updateItem(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemUpdate");
        return mv;
    }
}


