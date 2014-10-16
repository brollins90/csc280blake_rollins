package edu.neumont.csc280.lab4.auction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ArrayAuctionManager implements AuctionManager {

    private final Map<String, AuctionItem> data;
    //private final Double StartingPrice;

    public ArrayAuctionManager() {
        data = new ConcurrentHashMap<>();
        //StartingPrice = 0.01d;

        // load some test data for me

        data.put("1234",
                new AuctionItem(
                        "1234",
                        "/img/item_1234.png",
                        "Item one two three four",
                        "Items decs"
                ));
        data.put("12345",
                new AuctionItem(
                        "12345",
                        "/img/item_12345.png",
                        "This one goes up to five.",
                        "Items desc5"
                ));
    }

    @Override
    public Iterator<AuctionItem> items() {
        return data.values().iterator();
    }

    @Override
    public List itemIds() {
        return Arrays.asList(data.values().toArray());
    }

    @Override
    public AuctionItem getItem(String id) {
        return data.get(id);
    }
}
