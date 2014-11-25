package edu.neumont.csc280.lab5.search;

import edu.neumont.csc280.lab5.item.AuctionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blakerollins on 11/18/14.
 */
public class SearchModel {
    private long expireTime;
    private String searchTerm;
    private List<AuctionItem> items;
    private int count;
    private int offset;

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

    public List<AuctionItem> getItems() {

        int start = (offset > items.size() - 1) ? items.size() - 1 : offset;
        int end = (start + count > items.size()) ? items.size() : start + count;
        return items.subList(start, end);
    }

    public List<AuctionItem> getAllItems() {
        return items;
    }

    public void setItems(List<AuctionItem> items) {
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
}
