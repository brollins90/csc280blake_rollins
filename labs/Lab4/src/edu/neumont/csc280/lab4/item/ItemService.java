package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.Money;

import java.util.List;

public interface ItemService {

    /**
     * Creates an AuctionItem and returns the ID
     * @return the ID of the created item
     */
    public String createItem();

    public String createItem(String title, String description, String imageUrl, Money startPrice, long endTime, long startTime);


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
