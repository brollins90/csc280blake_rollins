package edu.neumont.csc280.lab6.item;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class SearchCache {

    private List<SearchResult> searchResults;

    public SearchCache() {
        searchResults = new ArrayList<>();
    }

    public SearchResult getFromCache(String searchTerm) {

        synchronized (this) {
            if (searchResults.size() == 0) {
                return null;
            }
            boolean removedOne = false;
            for (SearchResult s : searchResults) {
                if (s.getSearchTerm().toLowerCase().equals(searchTerm.toLowerCase())) {
                    long now = new Date().getTime();
                    if (s.getExpireTime() > now) {
                        System.out.println("Found in cache.");
                        return s;
                    } else {
                        System.out.println("found in cache, but expired.");
                        searchResults.remove(s);
                        removedOne = true;
                        break;
                    }
                }

            }
            if (removedOne) {
                return getFromCache(searchTerm);
            }
            return null;
        }
    }

    public void addToCache(SearchResult result) {
        synchronized (this) {
            long now = new Date().getTime();
            result.setExpireTime(now + 1 * 1 * 60 * 60 * 1000);
            searchResults.add(result);
        }
    }
}
