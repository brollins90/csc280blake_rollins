package edu.neumont.csc280.lab4.item;

import java.util.List;

public interface ItemService {

    AuctionItem lookupById(String id);

    void addItem(String title, String description, String imageUrl, String startPrice, String startDate, String endDate);

    void updateItem(String id, String title, String description, String imageUrl, String startPrice, String startDate, String endDate);

    void removeItem(String id);

    public List<AuctionItem> listItems();

    public void placeBid(String itemId, Bid bid);

}
