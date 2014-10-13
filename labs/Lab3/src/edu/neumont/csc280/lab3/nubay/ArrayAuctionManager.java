package edu.neumont.csc280.lab3.nubay;

import java.util.*;


public class ArrayAuctionManager implements AuctionManager {

    private final Map<String, AuctionItem> data;
    //private final Double StartingPrice;

    public ArrayAuctionManager() {
        data = new HashMap<>();
        //StartingPrice = 0.01d;

        // load some test data for me

        data.put("1234",
                new AuctionItem(
                        "1234",
                        "url",
                        "itemname",
                        "Items decs"
                ));
        data.put("12345",
                new AuctionItem(
                        "12345",
                        "url5",
                        "itemname5",
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
