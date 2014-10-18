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

    public ModelAndView retrieveItem(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemView");
        return mv;
    }

//    public ModelAndView retrieveItemJSON(String id) {
//        //TODO
//        return null;
//    }

    public ModelAndView updateItem(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemUpdate");
        return mv;
    }
//
//    public ModelAndView validateItem(String id) {
//        AuctionItem item = manager.getItem(id);
//
//        String action = this.request.getParameter("action");
//        if ("validate_item_start_time".equals(action)) {
//            String check_value = this.request.getParameter("check_value");
//            long check_value_long = 0;
//            try { check_value_long = Long.parseLong(check_value); } catch (Exception e) {}
//            String validated = item.validateStartTime(check_value_long);
//
//        }
//    }
}


