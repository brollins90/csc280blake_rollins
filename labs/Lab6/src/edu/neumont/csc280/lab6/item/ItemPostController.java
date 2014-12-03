package edu.neumont.csc280.lab6.item;

import edu.neumont.csc280.lab6.Money;
import edu.neumont.csc280.lab6.web.ModelAndView;
import edu.neumont.csc280.lab6.web.ValidationResult;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Stateless
@LocalBean
public class ItemPostController {

    @Inject
    AuctionService auctionService;
    @Inject
    HttpServletRequest request;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    public ItemPostController() {
    }

    public ModelAndView deleteItem(Long id) {

        try {
            auctionService.delete(id);
        } catch (Exception e) {

        }
        return new ModelAndView(null, "redirect:" + request.getServletContext().getContextPath() + "/item");
    }

    public ModelAndView processAuctionForm(Long id) {

        ModelItemForm model = new ModelItemForm();
        Auction a = new Auction();

        a.setTitle(request.getParameter("title"));
        a.setDescription(request.getParameter("description"));
        a.setImageUrl(request.getParameter("image_url"));

        try {
            String priceString = request.getParameter("price");
            if (priceString != null && !priceString.isEmpty()) {
                a.setPrice(Money.dollars(new BigDecimal(priceString)));
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult("Price is not in the correct format."));
        }

        Calendar calendar = Calendar.getInstance();

        try {
            String startTimeString = request.getParameter("start_time");
            if (startTimeString != null && !startTimeString.isEmpty()) {
                calendar.setTime(dateFormat.parse(startTimeString));
                a.setStartTime(new Date(calendar.getTimeInMillis()));
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult("Start time is not in the correct format."));
        }

        try {
            String endTimeString = request.getParameter("end_time");
            if (endTimeString != null && !endTimeString.isEmpty()) {
                calendar.setTime(dateFormat.parse(endTimeString));
                a.setEndTime(new Date(calendar.getTimeInMillis()));
            }
        } catch (Exception e) {
            model.addValidationResult(new ValidationResult("End time is not in the correct format."));
        }


    // Do the update
    try

    {
        if (id != null && id > 0) {
            a.setId(id);
            auctionService.update(a);
        } else {
            a = auctionService.create(a);
            id = a.getId();
        }
    }

    catch(
    Exception e
    )

    {
        model.addValidationResult(new ValidationResult(e.getMessage()));
    }

    // set the view
//        Auction item = auctionService.retreive(id);
//        item = (item == null) ? new Auction(null, updatedTitle, updatedDescription, updatedImageUrl, updatedStartPrice, updatedStartTime, updatedEndTime) : item;

    if(model.getValidationResult().

    getSuccess()

    )

    {
        return new ModelAndView(a, "redirect:" + request.getServletContext().getContextPath() + "/item/" + a.getId());
    }

    model.setItem(a);
    return new

    ModelAndView(model, "itemForm");

}

    public ModelAndView placeBid(Long itemId) {
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

        Money newAmount = Money.dollars(auctionService.retreive(itemId).getPrice().getAmount().add(incAmount.getAmount()));

        auctionService.placeBid(itemId, newAmount.getAmount().doubleValue());
        Auction item = auctionService.retreive(itemId);

//        item.placeBid(new Bid(itemID, newAmount, "Blake"));

//        return new ModelAndView(null, "itemView");
        return new ModelAndView(item, "itemView");
    }
}
