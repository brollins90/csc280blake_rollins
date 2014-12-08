package edu.neumont.csc280.lab7.item;

import edu.neumont.csc280.lab7.Money;
import edu.neumont.csc280.lab7.search.SearchModel;
import edu.neumont.csc280.lab7.web.ModelAndView;
import edu.neumont.csc280.lab7.web.ValidationResult;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class AuctionController {

    @Inject
    AuctionService auctionService;
    @Inject
    HttpServletRequest request;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    public AuctionController() {
    }

    public ModelAndView build() {
        ModelAndView mav = new ModelAndView(null, "/WEB-INF/500.jsp");

        try {
            auctionService.build();

            mav.setModel(null);
            mav.setView("redirect:" + request.getServletContext().getContextPath() + "/item/");
        } catch (Exception e) {
            mav.addError(e.getMessage());
        }
        return mav;
    }

    public ModelAndView getAllItemsPage() {
        ModelAndView mav = new ModelAndView(null, "/WEB-INF/500.jsp");

        try {
            SearchModel search = auctionService.findByTextSearch(null, 100, 0);

            mav.setModel(search);
            mav.setView("/WEB-INF/itemList.jsp");
        } catch (Exception e) {
            mav.addError(e.getMessage());
        }
        return mav;
    }

    public ModelAndView getCreateItemPage() {
        ModelAndView mav = new ModelAndView(null, "/WEB-INF/500.jsp");

        try {
            ModelItemForm model = new ModelItemForm();
            model.setItem(new Auction());

            mav.setModel(model);
            mav.setView("/WEB-INF/itemForm.jsp");
        } catch (Exception e) {
            mav.addError(e.getMessage());
        }
        return mav;
    }

    public ModelAndView getDeleteItemPage(Long id) {
        ModelAndView mav = new ModelAndView(null, "/WEB-INF/500.jsp");

        try {
            Auction item = auctionService.retreive(id);

            mav.setModel(item);
            mav.setView("/WEB-INF/itemDelete.jsp");
        } catch (Exception e) {
            mav.addError(e.getMessage());
        }
        return mav;
    }

    public ModelAndView processDeleteItem(Long id) {
        ModelAndView mav = new ModelAndView(null, "/WEB-INF/500.jsp");

        try {
            auctionService.delete(id);

            mav.setView("redirect:" + request.getServletContext().getContextPath() + "item");
        } catch (Exception e) {
            mav.addError(e.getMessage());
        }
        return mav;
    }

    public ModelAndView getItemPage(Long id) {
        ModelAndView mav = new ModelAndView(null, "/WEB-INF/500.jsp");

        try {
            Auction item = auctionService.retreive(id);

            mav.setModel(item);
            mav.setView("/WEB-INF/itemView.jsp");
        } catch (Exception e) {
            mav.addError(e.getMessage());
        }
        return mav;
    }

    public ModelAndView getItemJSON(Long id) {
        ModelAndView mav = new ModelAndView(null, "/WEB-INF/500.jsp");

        try {
            Auction item = auctionService.retreive(id);

            mav.setModel(item);
            mav.setView("/WEB-INF/itemJson.jsp");
        } catch (Exception e) {
            mav.addError(e.getMessage());
        }
        return mav;
    }

    public ModelAndView getUpdateItemPage(Long id) {
        ModelAndView mav = new ModelAndView(null, "/WEB-INF/500.jsp");

        try {

            if (id != null && id > 0) {
                Auction item = auctionService.retreive(id);

                mav.setModel(item);
                mav.setView("/WEB-INF/itemForm.jsp");
            }
        } catch (Exception e) {
            mav.addError(e.getMessage());
        }
        return mav;
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
        } catch (
                Exception e
                )

        {
            model.addValidationResult(new ValidationResult(e.getMessage()));
        }

        // set the view
//        Auction item = auctionService.retreive(id);
//        item = (item == null) ? new Auction(null, updatedTitle, updatedDescription, updatedImageUrl, updatedStartPrice, updatedStartTime, updatedEndTime) : item;

        if (model.getValidationResult().

                getSuccess()

                )

        {
            return new ModelAndView(a, "redirect:" + request.getServletContext().getContextPath() + "/item/" + a.getId());
        }

        model.setItem(a);
        return new

                ModelAndView(model, "/WEB-INF/itemForm.jsp");

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
        return new ModelAndView(item, "/WEB-INF/itemView.jsp");
    }
}


