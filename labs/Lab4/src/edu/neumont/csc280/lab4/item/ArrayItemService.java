package edu.neumont.csc280.lab4.item;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class ArrayItemService implements ItemService {

    private static long nextItemId = 1;
    private final Map<String, AuctionItem> data;

    public ArrayItemService() {
        data = new ConcurrentHashMap<>();

        String id1 = this.createItem();
        this.updateItemDescription(id1, "Item 1 description.");
        this.updateItemTitle(id1, "Item 1 Title.");
        this.updateItemImageUrl(id1, "/img/item_1.png");

        String id2 = this.createItem();
        this.updateItemDescription(id2, "Item 2 desc.");
        this.updateItemTitle(id2, "Item 3 Title.");
        this.updateItemImageUrl(id2, "/img/item_2.png");

    }

    @Override
    public String createItem() {
        String thisItemId = "" + nextItemId++;

        assert !this.data.containsKey(thisItemId);

        this.data.put(thisItemId, new AuctionItem(thisItemId));
        return thisItemId;
    }

    @Override
    public AuctionItem getItem(String id) {
        return this.data.get(id);
    }

    @Override
    public List<AuctionItem> getItems() {
        return new ArrayList<>(this.data.values());
    }

    @Override
    public void updateItemDescription(String itemId, String newValue) {
        this.data.get(itemId).setDescription(newValue);
    }

    @Override
    public void updateItemStartTime(String itemId, long newValue) {
        this.data.get(itemId).setStartTime(newValue);
    }

    @Override
    public void updateItemStartTime(String itemId, String newValue) {
        this.data.get(itemId).setStartTime(newValue);
    }

    @Override
    public void updateItemEndTime(String itemId, long newValue) {
        this.data.get(itemId).setEndTime(newValue);
    }

    @Override
    public void updateItemEndTime(String itemId, String newValue) {
        this.data.get(itemId).setEndTime(newValue);
    }

    @Override
    public void updateItemTitle(String itemId, String newValue) {
        this.data.get(itemId).setTitle(newValue);
    }

    @Override
    public void updateItemImageUrl(String itemId, String newValue) {
        this.data.get(itemId).setImageUrl(newValue);
    }

    @Override
    public void placeBid(String itemId, Bid bid) {
        this.data.get(itemId).placeBid(bid);
    }
}
