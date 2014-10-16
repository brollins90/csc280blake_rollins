package edu.neumont.csc280.lab4.auction;

import java.util.Iterator;
import java.util.List;

public interface AuctionManager {

    /**
     * Creates an AuctionItem and returns the ID
     * @return the ID of the created item
     */
    public String createItem();

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

    public void updateItemDescription(String itemId, String newValue);
    public void updateItemEndTime(String itemId, long newValue);
    public void updateItemImageUrl(String itemId, String newValue);
    public void updateItemStartTime(String itemId, long newValue);
    public void updateItemTitle(String itemId, String newValue);

    public void placeBid(String itemId, Bid bid);

}
