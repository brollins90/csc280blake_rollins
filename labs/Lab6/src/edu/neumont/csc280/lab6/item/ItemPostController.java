package edu.neumont.csc280.lab6.item;

import edu.neumont.csc280.lab6.Money;
import edu.neumont.csc280.lab6.web.ModelAndView;
import edu.neumont.csc280.lab6.web.ValidationResult;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Stateless
@LocalBean
public class ItemPostController {

    @Inject
    ItemService itemService;
    @Inject
    HttpServletRequest request;

    public ItemPostController() {
    }

    public ModelAndView deleteItem(String id) {

        try {
            itemService.deleteItem(id);
        } catch (Exception e) {

        }
        return new ModelAndView(null, "redirect:" + request.getServletContext().getContextPath() + "/item");
    }

    public ModelAndView updateItem(String id) {

        ModelItemForm model = new ModelItemForm();

        String updatedTitle = request.getParameter("title");
        String updatedDescription = request.getParameter("description");
        String updatedImageUrl = request.getParameter("image_url");
        Money updatedStartPrice = null;
        Long updatedStartTime = null;
        Long updatedEndTime = null;

        String startPriceString = request.getParameter("start_price");
        if (startPriceString != null && !startPriceString.isEmpty()) {
            try {
                updatedStartPrice = Money.dollars(BigDecimal.valueOf(Double.parseDouble(startPriceString)));
            } catch (Exception e) {
                model.addValidationResult(new ValidationResult("Start price is not in the correct format."));
            }
        }

        String startTimeString = request.getParameter("start_time_long");
        if (startTimeString != null && !startTimeString.isEmpty()) {
            try {
                updatedStartTime = Long.parseLong(startTimeString);
            } catch (Exception e) {
                model.addValidationResult(new ValidationResult("Start time is not in the correct format."));
            }
        }

        String endTimeString = request.getParameter("end_time_long");
        if (endTimeString != null && !endTimeString.isEmpty()) {
            try {
                updatedEndTime = Long.parseLong(endTimeString);
            } catch (Exception e) {
                model.addValidationResult(new ValidationResult("End time is not in the correct format."));
            }
        }


        // Do the update
        try {
            if (id != null && !id.isEmpty()) {
                itemService.updateItem(id, updatedTitle, updatedDescription, updatedImageUrl, updatedStartPrice, updatedStartTime, updatedEndTime);
            } else {

                id = (itemService.createItem(updatedTitle, updatedDescription, updatedImageUrl, updatedStartPrice, updatedStartTime, updatedEndTime)).getId();
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult(e.getMessage()));
        }

        // set the view
        AuctionItem item = itemService.getItem(id);
        item = (item == null) ? new AuctionItem(null, updatedTitle, updatedDescription, updatedImageUrl, updatedStartPrice, updatedStartTime, updatedEndTime) : item;

        if (model.getValidationResult().getSuccess()) {
            return new ModelAndView(item, "redirect:" + request.getServletContext().getContextPath() + "/item/" + item.getId());
        }
        model.setItem(item);
        return new ModelAndView(model, "itemForm");
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

        Money newAmount = Money.dollars(itemService.getItem(itemID).getCurrentPrice().getAmount().add(incAmount.getAmount()));

        AuctionItem item = itemService.getItem(itemID);

        item.placeBid(new Bid(itemID, newAmount, "Blake"));

        return new ModelAndView(item, "itemView");
    }
}
