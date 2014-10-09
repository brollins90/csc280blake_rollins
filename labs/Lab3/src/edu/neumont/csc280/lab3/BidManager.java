package edu.neumont.csc280.lab3;

interface BidManager {

    public Double getCurrentPrice(String item);
    public void placeBid(String item, Double price, String user);
}
