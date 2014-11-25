package edu.neumont.csc280.lab6.item;

import edu.neumont.csc280.lab6.Money;
import edu.neumont.csc280.lab6.search.SearchModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ItemService {

    public AuctionItem createItem(String title, String description, String imageUrl, Money startPrice, long startTime, long endTime);

    public void deleteItem(String id);

    public AuctionItem getItem(String id);

    public List<AuctionItem> getItems();

    public SearchModel searchForItems(String searchTerm, int count, int offset);

    public void updateItem(String id, String title, String description, String imageUrl, Money startPrice, long startTime, long endTime);

    public void placeBid(String itemId, Bid bid);
}
