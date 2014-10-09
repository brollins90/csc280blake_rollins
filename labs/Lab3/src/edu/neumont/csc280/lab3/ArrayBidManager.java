package edu.neumont.csc280.lab3;

import java.util.HashMap;
import java.util.Map;


public class ArrayBidManager implements BidManager {

    private final Map<String, Double> data;
    private final Double StartingPrice;

    public ArrayBidManager() {
        data = new HashMap<>();
        StartingPrice = 0.01d;
    }

    @Override
    public Double getCurrentPrice(String item) {
        if (! data.containsKey(item)) {
            data.put(item, StartingPrice);
        }

        return data.get(item);
    }

    @Override
    public void placeBid(String item, Double price, String user) {
        data.put(item, price);
    }
}
