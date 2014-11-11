package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.Money;
import edu.neumont.csc280.lab4.ValidationResult;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ItemServiceHashMapImpl implements ItemService {

    private static long nextItemId = 1;
    private final Map<String, AuctionItem> idToItemMap = new HashMap<>();

    public ItemServiceHashMapImpl() {

        String id1 = this.createItem();
        this.updateItemDescription(id1, "Item 1 description.");
        this.updateItemTitle(id1, "Item 1 Title.");
        this.updateItemImageUrl(id1, "http://localhost:8080/lab4/img/item_1.png");

        String id2 = this.createItem();
        this.updateItemDescription(id2, "Item 2 desc.");
        this.updateItemTitle(id2, "Item 2 Title.");
        this.updateItemImageUrl(id2, "http://localhost:8080/lab4/img/item_2.png");

    }

    private AuctionItem item(String title, String description, String imageUrl,Money startPrice, long startTime, long endTime) {

        AuctionItem item = new AuctionItem("0", title, description, imageUrl, startPrice, startTime, endTime);

        return item;
    }

    @Override
    public String createItem() {
        String thisItemId = "" + nextItemId++;

        assert !this.idToItemMap.containsKey(thisItemId);

        this.idToItemMap.put(thisItemId, new AuctionItem(thisItemId));
        return thisItemId;
    }

    @Override
    public String createItem(String title, String description, String imageUrl, Money startPrice, long startTime, long endTime) {

        AuctionItem item = item(title, description, imageUrl, startPrice, startTime, endTime);
        String thisItemId = "" + nextItemId++;
        item.setId(thisItemId);
        this.idToItemMap.put(thisItemId, item);
        return thisItemId;
    }


    @Override
    public void deleteItem(String id) {
        synchronized (this) {
            this.idToItemMap.remove(id);
        }
    }

    @Override
    public AuctionItem getItem(String id) {
        return this.idToItemMap.get(id);
    }

    @Override
    public List<AuctionItem> getItems() {
        return new ArrayList<>(this.idToItemMap.values());
    }

    @Override
    public void updateItem(String id, String title, String description, String imageUrl, Money startPrice, long startTime, long endTime) {

        updateItemTitle(id, title);
        updateItemDescription(id, description);
        updateItemImageUrl(id, imageUrl);
        updateItemStartPrice(id, startPrice);
        updateItemStartTime(id, startTime);
        updateItemEndTime(id, endTime);
    }

    private void updateItemTitle(String itemId, String newValue) {
        AuctionItem item = getItem(itemId);

        if (item.hasValueChanged(item.getTitle(),newValue)) {
            ValidationResult validationResult = item.validateTitle(newValue);
            if (validationResult.getSuccess()) {
                item.setTitle(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    private void updateItemDescription(String itemId, String newValue) {
        AuctionItem item = getItem(itemId);

        if (item.hasValueChanged(item.getDescription(),newValue)) {
            ValidationResult validationResult = item.validateDescription(newValue);
            if (validationResult.getSuccess()) {
                item.setDescription(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    private void updateItemImageUrl(String itemId, String newValue) {
        AuctionItem item = getItem(itemId);

        if (item.hasValueChanged(item.getImageUrl(),newValue)) {
            ValidationResult validationResult = item.validateImageUrl(newValue);
            if (validationResult.getSuccess()) {
                item.setImageUrl(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    private void updateItemStartPrice(String itemId, Money newValue) {
        AuctionItem item = getItem(itemId);

        if (item.hasValueChanged(item.getStartPrice(),newValue)) {
            ValidationResult validationResult = item.validateStartPrice(newValue);
            if (validationResult.getSuccess()) {
                item.setStartPrice(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    private void updateItemStartTime(String itemId, long newValue) {
        AuctionItem item = getItem(itemId);

        if (item.hasValueChanged(item.getStartTime(),newValue)) {
            ValidationResult validationResult = item.validateStartTime(newValue);
            if (validationResult.getSuccess()) {
                item.setStartTime(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    private void updateItemEndTime(String itemId, long newValue) {
        AuctionItem item = getItem(itemId);

        if (item.hasValueChanged(item.getEndTime(),newValue)) {
            ValidationResult validationResult = item.validateEndTime(newValue);
            if (validationResult.getSuccess()) {
                item.setEndTime(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    @Override
    public void placeBid(String itemId, Bid bid) {
        this.idToItemMap.get(itemId).placeBid(bid);
    }
}
