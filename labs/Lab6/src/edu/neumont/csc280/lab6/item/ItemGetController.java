package edu.neumont.csc280.lab6.item;

import edu.neumont.csc280.lab6.search.SearchModel;
import edu.neumont.csc280.lab6.web.ModelAndView;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

@Stateless
@LocalBean
public class ItemGetController {

    @Inject
    AuctionService auctionService;
    @Inject
    HttpServletRequest request;

    public ItemGetController() {
    }

    public ModelAndView getAllItems() {
        SearchModel search = auctionService.findByTextSearch(null, 100, 0);
        ModelAndView mv = new ModelAndView(search, "itemList");
        return mv;
    }

    public ModelAndView build() {
        auctionService.build();
        return getAllItems();
    }

    public ModelAndView createItem() {

        ModelItemForm model = new ModelItemForm();
        model.setItem(new Auction());
        return new ModelAndView(model, "itemForm");
    }

    public ModelAndView deleteItem(Long id) {
        Auction item = auctionService.retreive(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemDelete");
        return mv;
    }

    public ModelAndView retrieveItem(Long id) {
        Auction item = auctionService.retreive(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item, "itemView");
        return mv;
    }

    public ModelAndView retrieveItemJSON(Long id) {
        Auction item = auctionService.retreive(id);
        ModelAndView mv = (item == null) ? new ModelAndView(null, "404") : new ModelAndView(item.toJSON(), "itemJson");
        return mv;
    }

    public ModelAndView updateItem(Long id) {
        ModelAndView mav = null;

        if (id != null && id > 0) {
            Auction item = auctionService.retreive(id);
            mav = new ModelAndView(new ModelItemForm(item), "itemForm");
        }
        return mav;
    }
}


