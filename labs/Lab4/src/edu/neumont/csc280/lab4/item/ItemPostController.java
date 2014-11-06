package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.ModelAndView;
import edu.neumont.csc280.lab4.Money;
import edu.neumont.csc280.lab4.ValidationResult;
import edu.neumont.csc280.lab4.item.AuctionItem;
import edu.neumont.csc280.lab4.item.ItemService;
import edu.neumont.csc280.lab4.item.Bid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class ItemPostController {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ItemService manager;

    public ItemPostController(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        manager = (ItemService)request.getServletContext().getAttribute("manager");
    }

    public ModelAndView createItem() {
        //TODO
        return null;
    }

    public ModelAndView deleteItem(String id) {
        //TODO
        return null;
    }

    public ModelAndView updateItem(String id) {


        UpdateItemModel model = new UpdateItemModel();
        AuctionItem item = manager.getItem(id);

        // Title
        String title = request.getParameter("title");
        if (hasValueChanged(item.getTitle(), title)) {

            ValidationResult validation = item.validateTitle(title);
            if (validation.getSuccess()) {
                manager.updateItemTitle(id, title);
            } else {
                model.addValidationResult(validation);
            }
        }

//        // Description
//        String itemDescription = request.getParameter("item_description");
//        if (! item.getDescription().equals(itemDescription)) {
//            manager.updateItemDescription(id, itemDescription);
//        }

        // Start Time
        try {
            long startTime = Long.parseLong(request.getParameter("start_time_long"));
            if (hasValueChanged(item.getStartTime(), startTime)) {

                if (item.validateStartTime(startTime).getSuccess()) {
                    manager.updateItemStartTime(id, startTime);

                    // To make sure that the end time is always after the start time, update the end time to 7
                    // days after the start time.  If they want to change the end time in the same instance
                    // of this POST then that will overwrite this change since it runs afterward anyway.
                    manager.updateItemEndTime(id, startTime + 7 * 24 * 60 * 60 * 1000);
                }
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult("imageUrl cannot be empty."));
        }
//
//        // End Time
//        String itemEndTime = request.getParameter("item_end_time");
//        long endTimeLong = 0;
//        try { endTimeLong = Long.parseLong(itemEndTime); } catch (Exception e) {}
//        if (item.getEndTime() != (endTimeLong)) {
//            manager.updateItemStartTime(id, endTimeLong);
//        }
//
//        // Image URL
//        String itemImageUrl = request.getParameter("item_image_url");
//        if (! item.getImageUrl().equals(itemImageUrl)) {
//            manager.updateItemImageUrl(id, itemImageUrl);
//        }
//// do it
//        try {
//
//            if (model.getValidationResult().getSuccess()) {
//                if (id == null || id.isEmpty()) {
//                    id = manager.addItem(item.getTitle(), item.getDescription(), item.getImageUrl(), item.getStartPrice(), item.getStartTime(), item.getEndTime());
//                } else {
//                    manager.updateItem(id, item.getTitle(), item.getDescription(), item.getImageUrl(), item.getStartPrice(), item.getStartTime(), item.getEndTime());
//                }
//
//                AuctionItem changedItem = manager.lookupById(id);
//                model.setItem(changedItem);
//
//                return new ModelAndView(item, "redirect:" + request.getServletContext().getContextPath() + "/item/" + item.getId());
//            } else {
//                model.setItem(item);
//                return new ModelAndView(model, "itemForm");
//            }
//
//
//        } catch (Exception e) {
//            model.addValidationResult(new ValidationResult("bad update"));
//        }
//
////
////
////
        if (model.getValidationResult().getSuccess()) {
            return new ModelAndView(item, "redirect:" + request.getServletContext().getContextPath() + "/item/" + item.getId());
        }
        model.setItem(item);
        return new ModelAndView(model, "itemUpdateForm");
    }

    public ModelAndView placeBid(String id) {
        String itemID = id;
        String bidAmount = request.getParameter("bidAmount");

        Money incAmount;
        try {
            switch(bidAmount){
                default:
                case "one":
                    incAmount = Money.dollars(1.00d);
                    break;
                case "five":
                    incAmount = Money.dollars(5.00d);
                    break;
                case "ten":
                    incAmount = Money.dollars(10.00d);
                    break;
                case "custom":
                    incAmount = Money.dollars(new BigDecimal(request.getParameter("incrementBid")));
                    break;
            }
        } catch (Exception e) {
            incAmount = Money.dollars(0.01d);
        }
//        BigDecimal current = manager.getItem(itemID).getCurrentPrice();

        Money newAmount = Money.dollars(manager.getItem(itemID).getCurrentPrice().getAmount().add(incAmount.getAmount()));

        AuctionItem item = manager.getItem(itemID);

        item.placeBid(new Bid(itemID, newAmount, "Blake"));

        return new ModelAndView(item, "itemView");
    }

    private<T extends Comparable<T>> boolean hasValueChanged (T one, T two) {
        System.out.println("hasValueChanged(" + one + ", " + two + ")");
        if (one.compareTo(two) == 0) {

            System.out.println("no");
            return false;
        }
        System.out.println("yes");
        return true;
    }

}
