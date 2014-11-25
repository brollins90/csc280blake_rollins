package edu.neumont.csc280.lab6.search;

import edu.neumont.csc280.lab6.item.ItemService;
import edu.neumont.csc280.lab6.web.ModelAndView;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by blakerollins on 11/12/14.
 */
@Stateless
@LocalBean
public class SearchGetController {

    @Inject
    ItemService itemService;
    @Inject
    HttpServletRequest request;

    public SearchGetController() {
    }

    public ModelAndView search() {
        String searchTerm = request.getParameter("s");
        String countString = request.getParameter("c");
        String offsetString = request.getParameter("o");
        int count = 10;
        int offset = 0;
        try {
            count = Integer.parseInt(countString);
        } catch (Exception e) {

        }
        try {
            offset = Integer.parseInt(offsetString);
        } catch (Exception e) {

        }

        System.out.println("searchTerm: " + searchTerm);
        System.out.println("count: " + count);
        System.out.println("offset: " + offset);

        SearchModel search = itemService.searchForItems(searchTerm, count, offset);

        ModelAndView mv = new ModelAndView(search, "itemList");
        return mv;
    }
}
