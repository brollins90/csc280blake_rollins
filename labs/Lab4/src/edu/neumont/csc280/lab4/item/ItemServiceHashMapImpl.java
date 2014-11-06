package edu.neumont.csc280.lab4.item;

import java.util.*;


public class ItemServiceHashMapImpl implements ItemService {

    private static long nextItemId = 1;
    private final Map<String, AuctionItem> idToItemMap = new HashMap<>();

    public ItemServiceHashMapImpl() {

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

        assert !this.idToItemMap.containsKey(thisItemId);

        this.idToItemMap.put(thisItemId, new AuctionItem(thisItemId));
        return thisItemId;
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
    public void updateItemTitle(String itemId, String newValue) {
        this.idToItemMap.get(itemId).setTitle(newValue);
    }

    @Override
    public void updateItemImageUrl(String itemId, String newValue) {
        this.idToItemMap.get(itemId).setImageUrl(newValue);
    }

    @Override
    public void updateItemDescription(String itemId, String newValue) {
        this.idToItemMap.get(itemId).setDescription(newValue);
    }

    @Override
    public void updateItemStartTime(String itemId, long newValue) {
        this.idToItemMap.get(itemId).setStartTime(newValue);
    }

//    @Override
//    public void updateItemStartTime(String itemId, String newValue) {
//        this.idToItemMap.get(itemId).setStartTime(newValue);
//    }

    @Override
    public void updateItemEndTime(String itemId, long newValue) {
        this.idToItemMap.get(itemId).setEndTime(newValue);
    }

//    @Override
//    public void updateItemEndTime(String itemId, String newValue) {
//        this.idToItemMap.get(itemId).setEndTime(newValue);
//    }

    @Override
    public void placeBid(String itemId, Bid bid) {
        this.idToItemMap.get(itemId).placeBid(bid);
    }
}
