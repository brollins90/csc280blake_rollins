package edu.neumont.csc280.lab3.nubay;

import java.util.Iterator;
import java.util.Map;

public interface AuctionManager {

    public Iterator<AuctionItem> items();
    public AuctionItem getItem(String id);
//    public Double getCurrentPrice(String item);
//    public void placeBid(String item, Double price, String user);
}
