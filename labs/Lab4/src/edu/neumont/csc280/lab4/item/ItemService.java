package edu.neumont.csc280.lab4.item;

import java.util.List;

public interface ItemService {

    /**
     * Creates an AuctionItem and returns the ID
     *
     * @return the ID of the created item
     */
    public AuctionItem createItem();

    /**
     * Gets the AuctionItem with the specified ID
     *
     * @param id The ID of the item
     * @return The item
     */
    public AuctionItem getItem(String id);

    /**
     * Gets a List of all the item ids
     *
     * @return
     */
    public List<AuctionItem> getItems();

    public void updateItemDescription(String itemId, String newValue);

    public void updateItemEndTime(String itemId, long newValue);

    public void updateItemImageUrl(String itemId, String newValue);

    public void updateItemStartTime(String itemId, long newValue);

    public void updateItemTitle(String itemId, String newValue);

    public void placeBid(String itemId, Bid bid);

}
