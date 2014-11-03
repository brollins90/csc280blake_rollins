package edu.neumont.csc280.lab4.item;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


public class ItemServiceHashMapImpl implements ItemService {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    private static long nextItemId = 1;
    private final Map<String, AuctionItem> idToItemMap = new HashMap<>();

    public ItemServiceHashMapImpl() {

        doAddItem(item("Item 1 title", "Item 1 description", "/img/item_1.png", "0.01", dateFormat.format(new Date()), dateFormat.format(new Date().getTime() + 7 * 24 * 60 * 1000)));
        doAddItem(item("Item 2 title", "Item 2 description", "/img/item_2.png", "0.01", dateFormat.format(new Date()), dateFormat.format(new Date().getTime() + 7 * 24 * 60 * 1000)));
    }

    private AuctionItem item(String title, String description, String imageUrl, String startPriceIn, String startDateIn, String endDateIn) {

        Date startDate = null;
        Date endDate = null;
        Money startPrice = null;

        try {
            startDate = dateFormat.parse(startDateIn);
        } catch (Exception e) {
            throw new AuctionException("invalid start date");
        }

        try {
            endDate = dateFormat.parse(endDateIn);
        } catch (Exception e) {
            throw new AuctionException("invalid end date");
        }

        try {
            startPrice = Money.dollars(new BigDecimal(startPriceIn));
        } catch (Exception e) {
            throw new AuctionException("invalid start price");
        }

        return new AuctionItem("" + (nextItemId++), title, description, imageUrl, startPrice, startDate, endDate);
    }

    private void doAddItem(AuctionItem item) {
        synchronized (this) {
            this.idToItemMap.put(item.getId(), item);
        }
    }

    @Override
    public AuctionItem lookupById(String id) {
        synchronized (this) {
            return this.idToItemMap.get(id).clone();
        }
    }

    @Override
    public void addItem(String title, String description, String imageUrl, String startPrice, String startDate, String endDate) {

        AuctionItem item = item(title, description, imageUrl, startPrice, startDate, endDate);

        if (this.idToItemMap.containsKey(item.getId())) {
            throw new AuctionException("The item id already exists.");
        }

        doAddItem(item);
    }

    @Override
    public void updateItem(String id, String title, String description, String imageUrl, String startPrice, String startDate, String endDate) {
        AuctionItem item = item(title, description, imageUrl, startPrice, startDate, endDate);
        synchronized (this) {
            item.setId(id);
            this.idToItemMap.put(item.getId(), item);
        }
    }

    @Override
    public void removeItem(String id) {
        synchronized (this) {
            this.idToItemMap.remove(id);
        }
    }

    private List<AuctionItem> doListItems() {
        List<AuctionItem> items;
        synchronized (this) {
            items = new ArrayList<>(this.idToItemMap.size());
            for (AuctionItem item : this.idToItemMap.values()) {
                items.add(item.clone());
            }
        }
        return items;
    }

    @Override
    public List<AuctionItem> listItems() {
        List<AuctionItem> items = doListItems();

        Collections.sort(items, new Comparator<AuctionItem>() {
            @Override
            public int compare(AuctionItem o1, AuctionItem o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        return items;
    }


    //    @Override
//    public AuctionItem createItem() {
//        String thisItemId = "" + nextItemId++;
//
//        if (this.data.containsKey(thisItemId)) {
//            throw new AuctionException("The item id already exists.");
//        }
//
//        AuctionItem newItem = new AuctionItem(thisItemId);
//
//        this.data.put(thisItemId, newItem);
//        return newItem;
//    }
//
//    @Override
//    public AuctionItem getItem(String id) {
//        return this.data.get(id);
//    }
//
//    @Override
//    public List<AuctionItem> getItems() {
//        return new ArrayList<>(this.data.values());
//    }
//
//    @Override
//    public void updateItemDescription(String itemId, String newValue) {
//        AuctionItem item = getItem(itemId);
//
//        ValidationResult validation = item.validateDescription(newValue);
//        if (validation.getSuccess()) {
//            item.setDescription(newValue);
//        } else {
//            throw new RuntimeException(validation.toString());
//        }
//    }
//
//    @Override
//    public void updateItemStartTime(String itemId, long newValue) {
//        AuctionItem item = getItem(itemId);
//
//        ValidationResult validation = item.validateStartTime(newValue);
//        if (validation.getSuccess()) {
//            item.setStartTime(newValue);
//        } else {
//            throw new RuntimeException(validation.toString());
//        }
//    }
//
//    @Override
//    public void updateItemEndTime(String itemId, long newValue) {
//        AuctionItem item = getItem(itemId);
//
//        ValidationResult validation = item.validateEndTime(newValue);
//        if (validation.getSuccess()) {
//            item.setEndTime(newValue);
//        } else {
//            throw new RuntimeException(validation.toString());
//        }
//    }
//
//    @Override
//    public void updateItemTitle(String itemId, String newValue) {
//        AuctionItem item = getItem(itemId);
//
//        ValidationResult validation = item.validateTitle(newValue);
//        if (validation.getSuccess()) {
//            item.setTitle(newValue);
//        } else {
//            throw new RuntimeException(validation.toString());
//        }
//    }
//
//    @Override
//    public void updateItemImageUrl(String itemId, String newValue) {
//        AuctionItem item = getItem(itemId);
//
//        ValidationResult validation = item.validateImageUrl(newValue);
//        if (validation.getSuccess()) {
//            item.setImageUrl(newValue);
//        } else {
//            throw new RuntimeException(validation.toString());
//        }
//    }
    @Override
    public void placeBid(String id, Bid bid) {
        synchronized (this) {
            this.idToItemMap.get(id).placeBid(bid);
        }
    }
}
