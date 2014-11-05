package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.Money;
import edu.neumont.csc280.lab4.web.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;

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


    public ModelAndView commitItemFormWorkflow(String id) {

//        if (id == null || id.isEmpty()) {
//            id = manager.addItemDefault();
//        }

        UpdateItemModel model = new UpdateItemModel();

        AuctionItemDB item = new AuctionItemDB();
        item.setId(request.getParameter("id"));
        item.setTitle(request.getParameter("title"));
        item.setDescription(request.getParameter("description"));
        item.setImageUrl(request.getParameter("imageUrl"));
        item.setStartPrice(Money.dollars(0.01d));
        item.setStartTime(new Date().getTime());
        item.setEndTime(item.getStartTime() + 1000 * 60 * 60 * 24 * 7);



        // Title
        {
            String title = model.getItem().getTitle();
            if (title == null || title.isEmpty()) {
                model.addValidationResult(new ValidationResult("Title cannot be empty."));
            }
            item.setTitle(title);
        }

        // Description
        {
            String description = model.getItem().getDescription();
            if (description == null || description.isEmpty()) {
                model.addValidationResult(new ValidationResult("Description cannot be empty."));
            }
            item.setDescription(description);
        }
//            String title = model.getTitle();
//
//            manager.up
//        } catch (Exception e) {
//
//            model.addValidationResult(new ValidationResult("Bad title"));
////            model.addValidationResult(new ValidationResult(e.getMessage()));
////            return new ModelAndView(model, "itemForm");
//        }

        // Description
        if (model.getTitle() == null || model.getTitle().isEmpty()) {
            model.addValidationResult(new ValidationResult("Title cannot be empty."));
        }
        try {
            description = request.getParameter("description");
            if (description == null || description.isEmpty()) {
                model.addValidationResult(new ValidationResult("Bad desc"));
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult("Bad desc"));
//            model.addValidationResult(new ValidationResult(e.getMessage()));
//            return new ModelAndView(model, "itemForm");
        }

        // Image URL
        try {
            imageUrl = request.getParameter("image_url");
            if (imageUrl == null || imageUrl.isEmpty()) {
                model.addValidationResult(new ValidationResult("Bad image"));
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult("Bad image"));
//            model.addValidationResult(new ValidationResult(e.getMessage()));
//            return new ModelAndView(model, "itemForm");
        }
//
//        // Start Price
//        try {
//            startPrice = Money.dollars(new BigDecimal(request.getParameter("start_price")));
//        } catch (Exception e) {
//            model.addValidationResult(new ValidationResult("Bad price"));
////            model.addValidationResult(new ValidationResult(e.getMessage()));
////            return new ModelAndView(model, "itemForm");
//        }
//
//        // Start Time
//        try {
//            startDate = Long.parseLong(request.getParameter("start_time_long"));
//        } catch (Exception e) {
//            model.addValidationResult(new ValidationResult("Bad start"));
////            model.addValidationResult(new ValidationResult(e.getMessage()));
////            return new ModelAndView(model, "itemForm");
//        }
//
//        // End Time
//        try {
//            endDate = Long.parseLong(request.getParameter("end_time_long"));
//        } catch (Exception e) {
//            model.addValidationResult(new ValidationResult("Bad end"));
////            model.addValidationResult(new ValidationResult(e.getMessage()));
////            return new ModelAndView(model, "itemForm");
//        }

        // do it
        try {
            if (id == null || id.isEmpty()) {
                id = manager.addItem(title, description, imageUrl, request.getParameter("start_price"), request.getParameter("start_time"), request.getParameter("end_time"));
            } else {
                manager.updateItem(id, title, description, imageUrl, request.getParameter("start_price"), request.getParameter("start_time"), request.getParameter("end_time"));
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult("bad update"));
        }

        model.setItem(item);


        AuctionItem item = manager.lookupById(id);
        model.setItem(item);


        if (model.getValidationResult().getSuccess()) {
            return new ModelAndView(item, "redirect:" + request.getServletContext().getContextPath() + "/item/" + item.getId());
        }
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

        Money newAmount = Money.dollars(manager.lookupById(itemID).getCurrentPrice().getAmount().add(incAmount.getAmount()));

        AuctionItem item = manager.lookupById(itemID);

        item.placeBid(new Bid(itemID, newAmount, "Blake"));

        return new ModelAndView(item, "redirect:" + request.getServletContext().getContextPath() + "/item/" + item.getId());
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
