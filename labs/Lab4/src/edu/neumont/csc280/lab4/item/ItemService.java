package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.Money;

import java.util.List;

public interface ItemService {

    AuctionItem lookupById(String id);

//    String addItem(String title, String description, String imageUrl, String startPrice, String startDate, String endDate);

    String addItem(String title, String description, String imageUrl, Money startPrice, long startDate, long endDate);

//    String addItemDefault();

    void updateItem(String id, String title, String description, String imageUrl, String startPrice, String startDate, String endDate);

    void updateItemTitle(String id, String title);
    void updateItemDescription(String id, String description);
    void updateItemImageUrl(String id, String imageUrl);
    void updateItemStartPrice(String id, Money startPrice);
    void updateItemStartTime(String id, long startTime);
    void updateItemEndTime(String id, long endTime);

    void updateItem(String id, String title, String description, String imageUrl, Money startPrice, long startDate, long endDate);

    void removeItem(String id);

    public List<AuctionItem> listItems();

    public void placeBid(String itemId, Bid bid);

}
