package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.web.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class ItemPostController {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ItemService manager;

    public ItemPostController(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        manager = (ItemService) request.getServletContext().getAttribute("manager");
    }

    public ModelAndView createItem() {
        //TODO
        return null;
    }

    public ModelAndView deleteItem(String id) {
        //TODO
        return null;
    }


    public ModelAndView commitUpdateItemWorkflow(String id) {

        AuctionItem item = manager.getItem(id);

        UpdateItemModel model = new UpdateItemModel();
        model.setItem(item);

//        // Title
//        try {
//            String updatedTitle = request.getParameter("item_title");
//            if (hasValueChanged(item.getTitle(), updatedTitle)) {
//
//                manager.updateItemTitle(id, updatedTitle);
//            }
//        } catch (Exception e) {
//            model.addValidationResult(new ValidationResult(e.getMessage()));
//            return new ModelAndView(model, "itemUpdate");
//        }

        // Description
        try {
            String updatedDescription = request.getParameter("item_description");
            if (hasValueChanged(item.getDescription(), updatedDescription)) {
                manager.updateItemDescription(id, updatedDescription);
            }
        } catch (Exception e) {
//todo
            model.addValidationResult(new ValidationResult(e.getMessage()));
            return new ModelAndView(model, "itemUpdate");
        }


        // Start Time
        try {
            Long updatedStartTime = Long.parseLong(request.getParameter("item_start_time"));
            if (hasValueChanged(item.getStartTime(), updatedStartTime)) {
                ValidationResult validation = item.validateStartTime(updatedStartTime);

                if (validation.getSuccess()) {
                    manager.updateItemStartTime(id, updatedStartTime);

                    // To make sure that the end time is always after the start time, update the end time to 7
                    // days after the start time.  If they want to change the end time in the same instance
                    // of this POST then that will overwrite this change since it runs afterward anyway.
                    manager.updateItemEndTime(id, updatedStartTime + 7 * 24 * 60 * 60 * 1000);
                } else {
                    model.addValidationResult(validation);
                    return new ModelAndView(model, "itemUpdate");
                }
            }
        } catch (NumberFormatException e) {
//todo
            model.addValidationResult(new ValidationResult("there was an error updating the item start time"));
            return new ModelAndView(model, "itemUpdate");
        }

        // End Time
        try {
            Long updatedEndTime = Long.parseLong(request.getParameter("item_end_time"));
            if (hasValueChanged(item.getEndTime(), updatedEndTime)) {
                ValidationResult validation = item.validateEndTime(updatedEndTime);

                if (validation.getSuccess()) {
                    manager.updateItemEndTime(id, updatedEndTime);
                } else {
                    model.addValidationResult(validation);
                    return new ModelAndView(model, "itemUpdate");
                }
            }
        } catch (NumberFormatException e) {
//todo
            model.addValidationResult(new ValidationResult("there was an error updating the item end time"));
            return new ModelAndView(model, "itemUpdate");
        }

        // Image URL
        try {
            String updatedImageUrl = request.getParameter("item_image_url");
            if (hasValueChanged(item.getImageUrl(), updatedImageUrl)) {

                ValidationResult validation = item.validateImageUrl(updatedImageUrl);
                if (validation.getSuccess()) {
                    manager.updateItemImageUrl(id, updatedImageUrl);
                } else {
                    model.addValidationResult(validation);
                    return new ModelAndView(model, "itemUpdate");
                }
            }
        } catch (Exception e) {
//todo
            model.addValidationResult(new ValidationResult("there was an error updating the item image"));
            return new ModelAndView(model, "itemUpdate");
        }

        return new ModelAndView(item, "itemView");
    }

    public ModelAndView placeBid(String id) {
        String itemID = id;
        String bidAmount = request.getParameter("bidAmount");

        Money incAmount;
        try {
            switch (bidAmount) {
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

    private <T extends Comparable<T>> boolean hasValueChanged(T one, T two) {
        System.out.println("hasValueChanged(" + one + ", " + two + ")");
        if (one.compareTo(two) == 0) {

            System.out.println("no");
            return false;
        }
        System.out.println("yes");
        return true;
    }

}
