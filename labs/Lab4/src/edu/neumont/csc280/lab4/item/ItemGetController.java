package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ItemGetController {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ItemService manager;

    public ItemGetController(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        manager = (ItemService) request.getServletContext().getAttribute("manager");
    }

    public ModelAndView getAllItems() {
        List<AuctionItem> itemIds = manager.getItems();
        ModelAndView mv = new ModelAndView(itemIds, "itemList");
        return mv;
    }

    public ModelAndView createItem() {

        ModelItemForm model = new ModelItemForm();

        model.setItem(new AuctionItem(null));
        return new ModelAndView(model, "itemForm");
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

    public ModelAndView retrieveItemJSON(String id) {
        AuctionItem item = manager.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item.toJSON(), "itemJson");
        return mv;
    }

    public ModelAndView updateItem(String id) {
        ModelAndView mav = null;

        if (id != null && !id.isEmpty()) {
            AuctionItem item = manager.getItem(id);
            mav = new ModelAndView(new ModelItemForm(item), "itemForm");
        }
        return mav;
    }
}


