package edu.neumont.csc280.lab6.item;

import edu.neumont.csc280.lab6.search.SearchModel;
import edu.neumont.csc280.lab6.web.ModelAndView;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
@LocalBean
public class ItemGetController {

    @Inject
    ItemService itemService;
    @Inject
    HttpServletRequest request;

    public ItemGetController() {
    }

    public ModelAndView getAllItems() {
        SearchModel search = itemService.searchForItems(null, 100, 0);
        ModelAndView mv = new ModelAndView(search, "itemList");
        return mv;
    }

    public ModelAndView createItem() {

        ModelItemForm model = new ModelItemForm();

        model.setItem(new AuctionItem(null));
        return new ModelAndView(model, "itemForm");
    }

    public ModelAndView deleteItem(String id) {
        AuctionItem item = itemService.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemDelete");
        return mv;
    }

    public ModelAndView retrieveItem(String id) {
        AuctionItem item = itemService.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemView");
        return mv;
    }

    public ModelAndView retrieveItemJSON(String id) {
        AuctionItem item = itemService.getItem(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item.toJSON(), "itemJson");
        return mv;
    }

    public ModelAndView updateItem(String id) {
        ModelAndView mav = null;

        if (id != null && !id.isEmpty()) {
            AuctionItem item = itemService.getItem(id);
            mav = new ModelAndView(new ModelItemForm(item), "itemForm");
        }
        return mav;
    }
}


