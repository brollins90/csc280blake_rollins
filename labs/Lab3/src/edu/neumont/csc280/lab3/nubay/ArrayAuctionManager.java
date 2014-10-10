package edu.neumont.csc280.lab3.nubay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ArrayAuctionManager implements AuctionManager {

    private final Map<String, AuctionItem> data;
    private final Double StartingPrice;

    public ArrayAuctionManager() {
        data = new HashMap<>();
        StartingPrice = 0.01d;

        // load some test data for me

        AuctionItem put = data.put(
                "1234",
                new AuctionItem(
                        "1234",
                        "url",
                        "itemname",
                        "Items decs"
                ));
    }

    @Override
    public Iterator<AuctionItem> items() {
        return data.values().iterator();
    }

    @Override
    public AuctionItem getItem(String id) {
        return data.get(id);
    }
}
