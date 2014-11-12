package edu.neumont.csc280.lab5.item;

import edu.neumont.csc280.lab5.Money;
import edu.neumont.csc280.lab5.ValidationResult;

import java.math.BigDecimal;
import java.util.*;


public class ItemServiceHashMapImpl implements ItemService {

    private static long nextItemId = 1;
    private final Map<String, AuctionItem> idToItemMap = new HashMap<>();

    public ItemServiceHashMapImpl() {

        createItem("Item 1 Title.", "Item 1 description.", "http://localhost:8080/lab4/img/item_1.png",
                Money.dollars(new BigDecimal(0.01d)), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
        createItem("Item 2 Title.", "Item 2 description.", "http://localhost:8080/lab4/img/item_2.png",
                Money.dollars(new BigDecimal(0.01d)), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
    }

    private AuctionItem item(String title, String description, String imageUrl,Money startPrice, long startTime, long endTime) {

        AuctionItem item = new AuctionItem("0");
        updateItemTitle(item, title);
        updateItemDescription(item, description);
        updateItemImageUrl(item, imageUrl);
        updateItemStartPrice(item, startPrice);
        updateItemStartTime(item, startTime);
        updateItemEndTime(item, endTime);
        return item;
    }

    private void doPutItem(AuctionItem item) {
        synchronized (this) {

            if (this.idToItemMap.containsKey(item.getId())) {
                throw new AuctionException("The new ID aready exists.");
            }

            this.idToItemMap.put(item.getId(), item);
        }
    }

    @Override
    public AuctionItem createItem(String title, String description, String imageUrl, Money startPrice, long startTime, long endTime) {

        AuctionItem item = item(title, description, imageUrl, startPrice, startTime, endTime);
        String thisItemId = "" + nextItemId++;
        item.setId(thisItemId);

        doPutItem(item);

        return item;
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

        AuctionItem item = this.getItem(id);

        updateItemTitle(item, title);
        updateItemDescription(item, description);
        updateItemImageUrl(item, imageUrl);
        updateItemStartPrice(item, startPrice);
        updateItemStartTime(item, startTime);
        updateItemEndTime(item, endTime);
    }

    private void updateItemTitle(AuctionItem item, String newValue) {

        if (item.hasValueChanged(item.getTitle(),newValue)) {
            ValidationResult validationResult = item.validateTitle(newValue);
            if (validationResult.getSuccess()) {
                item.setTitle(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    private void updateItemDescription(AuctionItem item, String newValue) {

        if (item.hasValueChanged(item.getDescription(),newValue)) {
            ValidationResult validationResult = item.validateDescription(newValue);
            if (validationResult.getSuccess()) {
                item.setDescription(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    private void updateItemImageUrl(AuctionItem item, String newValue) {

        if (item.hasValueChanged(item.getImageUrl(),newValue)) {
            ValidationResult validationResult = item.validateImageUrl(newValue);
            if (validationResult.getSuccess()) {
                item.setImageUrl(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    private void updateItemStartPrice(AuctionItem item, Money newValue) {

        if (item.hasValueChanged(item.getStartPrice(),newValue)) {
            ValidationResult validationResult = item.validateStartPrice(newValue);
            if (validationResult.getSuccess()) {
                item.setStartPrice(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    private void updateItemStartTime(AuctionItem item, long newValue) {

        if (item.hasValueChanged(item.getStartTime(),newValue)) {
            ValidationResult validationResult = item.validateStartTime(newValue);
            if (validationResult.getSuccess()) {
                item.setStartTime(newValue);
            } else {
                throw new AuctionException(validationResult.getFirstMessage());
            }
        }
    }

    private void updateItemEndTime(AuctionItem item, long newValue) {

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
