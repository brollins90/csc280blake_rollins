package edu.neumont.csc280.lab5.item;

import edu.neumont.csc280.lab5.search.SearchModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by blakerollins on 11/20/14.
 */
public class SearchCache {

    private List<SearchResult> searchResults;

    public SearchCache() {
        searchResults = new ArrayList<>();
    }

    public SearchResult getFromCache(String searchTerm) {

        if (searchResults.size() == 0) {
            return null;
        }

        for (SearchResult s : searchResults) {
            if (s.getSearchTerm().toLowerCase().equals(searchTerm.toLowerCase())) {
                long now = new Date().getTime();
                if (s.getExpireTime() > now) {
                    System.out.println("Found in cache.");
                    return s;
                } else {
                    System.out.println("found in cache, but expired.");
                    searchResults.remove(s);
                    break;
                }
            } else {
                return null;
            }
        }
        return getFromCache(searchTerm);
    }

    public void addToCache(SearchResult result) {
        long now = new Date().getTime();
        result.setExpireTime(now + 1);//1 * 24 * 60 * 60 * 1000);
        searchResults.add(result);
    }
}
