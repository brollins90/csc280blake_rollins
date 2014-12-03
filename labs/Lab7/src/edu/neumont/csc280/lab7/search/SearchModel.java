package edu.neumont.csc280.lab7.search;

import edu.neumont.csc280.lab7.item.Auction;

import java.util.List;

public class SearchModel {
    private long expireTime;
    private String searchTerm;
    private List<Auction> items;
    private int count;
    private int offset;
    private int itemsCountFound;

    public SearchModel() {
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<Auction> getItems() {
        return this.items;
    }

    public void setItems(List<Auction> items) {
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count > items.size() ? items.size() : count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return items.size();
    }

    public int getItemsCountFound() {
        return itemsCountFound;
    }

    public void setItemsCountFound(int itemsFound) {
        this.itemsCountFound = itemsFound;
    }
}
