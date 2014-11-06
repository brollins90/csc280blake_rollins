package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.ModelAndView;
import edu.neumont.csc280.lab4.Money;
import edu.neumont.csc280.lab4.ValidationResult;

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

        // Description
        String description = request.getParameter("description");
        if (hasValueChanged(item.getDescription(), description)) {

            ValidationResult validation = item.validateDescription(description);
            if (validation.getSuccess()) {
                manager.updateItemDescription(id, description);
            } else {
                model.addValidationResult(validation);
            }
        }

        // Image Url
        String imageUrl = request.getParameter("image_url");
        if (hasValueChanged(item.getImageUrl(), imageUrl)) {

            ValidationResult validation = item.validateImageUrl(imageUrl);
            if (validation.getSuccess()) {
                manager.updateItemImageUrl(id, imageUrl);
            } else {
                model.addValidationResult(validation);
            }
        }

        // Start price
        try {
            Money startPrice = Money.dollars(BigDecimal.valueOf(Double.parseDouble(request.getParameter("start_price"))));
            if (hasValueChanged(item.getStartPrice().getAmount(), startPrice.getAmount())) {

                ValidationResult validation = item.validateStartPrice(startPrice);
                if (validation.getSuccess()) {
                    manager.updateItemStartPrice(id, startPrice);
                } else {
                    model.addValidationResult(validation);
                }
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult("Start price is not in the correct format."));
        }

        // Start Time
        try {
            long startTime = Long.parseLong(request.getParameter("start_time_long"));
            if (hasValueChanged(item.getStartTime(), startTime)) {

                ValidationResult validation = item.validateStartTime(startTime);
                if (validation.getSuccess()) {
                    manager.updateItemStartTime(id, startTime);

                    // To make sure that the end time is always after the start time, update the end time to 7
                    // days after the start time.  If they want to change the end time in the same instance
                    // of this POST then that will overwrite this change since it runs afterward anyway.
                    manager.updateItemEndTime(id, startTime + 7 * 24 * 60 * 60 * 1000);
                }
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult("Start time is not in the correct format."));
        }

        // End time
        try {
            long endTime = Long.parseLong(request.getParameter("end_time_long"));
            if (hasValueChanged(item.getEndTime(), endTime)) {

                ValidationResult validation = item.validateEndTime(endTime);
                if (validation.getSuccess()) {
                    manager.updateItemEndTime(id, endTime);
                }
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult("End time is not in the correct format."));
        }

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
