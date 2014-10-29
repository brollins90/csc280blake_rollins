package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.item.AuctionItem;
import edu.neumont.csc280.lab4.item.ItemService;
import edu.neumont.csc280.lab4.web.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ItemGetController {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ItemService manager;

    public ItemGetController(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        manager = (ItemService)request.getServletContext().getAttribute("manager");
    }

    public ModelAndView beginCreateItemWorkflow() {
        AuctionItem item = manager.createItem();
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemCreate");
        return mv;
    }

    public ModelAndView beginDeleteItemWorkflow(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemDelete");
        return mv;
    }

    public ModelAndView beginUpdateItemWorkflow(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(new UpdateItemModel(item), "itemUpdate");
        return mv;
    }

    public ModelAndView getAllItems() {
        List<AuctionItem> itemIds = manager.getItems();
        ModelAndView mv = new ModelAndView(itemIds, "itemList");
        return mv;
    }

    public ModelAndView retrieveItem(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemView");
        return mv;
    }

    public ModelAndView retrieveItemJSON(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item.toJSON(), "itemJson");
        return mv;
    }
}


