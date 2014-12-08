package edu.neumont.csc280.lab7.search;

import edu.neumont.csc280.lab7.item.AuctionService;
import edu.neumont.csc280.lab7.web.ModelAndView;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
@LocalBean
public class SearchGetController {

    @Inject
    AuctionService auctionService;
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

        SearchModel search = auctionService.findByTextSearch(searchTerm, count, offset);

        ModelAndView mv = new ModelAndView(search, "/WEB-INF/itemList.jsp");
        return mv;
    }
}
