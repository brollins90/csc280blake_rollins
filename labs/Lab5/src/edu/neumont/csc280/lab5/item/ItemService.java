package edu.neumont.csc280.lab5.item;

import edu.neumont.csc280.lab5.Money;

import java.util.List;

public interface ItemService {

    /**
     * Create a new AuctionItem with the specified parameters
     * @param title Item title
     * @param description   Item description
     * @param imageUrl  Item image Url
     * @param startPrice    Item start price
     * @param startTime Item start time
     * @param endTime   Item end time
     * @return  The new AuctionItem
     */
    public AuctionItem createItem(String title, String description, String imageUrl, Money startPrice, long startTime, long endTime);


    public void deleteItem(String id);


    /**
     * Gets the AuctionItem with the specified ID
     * @param id The ID of the item
     * @return The item
     */
    public AuctionItem getItem(String id);

    /**
     * Gets an iterator for all the item ids
     * @return
     */
    public List<AuctionItem> getItems();

    public void updateItem(String id, String title, String description, String imageUrl, Money startPrice, long startTime, long endTime);

    public void placeBid(String itemId, Bid bid);

}
