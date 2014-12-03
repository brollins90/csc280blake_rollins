package edu.neumont.csc280.lab7.item;

import edu.neumont.csc280.lab7.search.SearchModel;

import java.util.List;

public class SearchResult {
    private long expireTime;
    private String searchTerm;
    private List<Auction> items;

    public SearchResult() {
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
        return items;
    }

    public void setItems(List<Auction> items) {
        this.items = items;
    }

    public SearchModel getModel(int count, int offset) {

        int start = (offset > items.size() - 1) ? items.size() - 1 : offset;
        start = (start < 0) ? 0 : start;
        int end = (start + count > items.size()) ? items.size() : start + count;
        SearchModel m = new SearchModel();
        m.setItems(items.subList(start, end));
        m.setCount(count);
        m.setExpireTime(expireTime);
        m.setItemsCountFound(items.size());
        m.setOffset(offset);
        m.setSearchTerm(searchTerm);

        return m;
    }
}
