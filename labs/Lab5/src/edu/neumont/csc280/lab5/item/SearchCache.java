package edu.neumont.csc280.lab5.item;

import edu.neumont.csc280.lab5.search.SearchModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by blakerollins on 11/20/14.
 */
public class SearchCache {

    private List<SearchModel> searches;

    public SearchCache() {
        searches = new ArrayList<>();
    }

    public SearchModel getFromCache(String searchTerm, int count, int offset) {

        if (searches.size() == 0) {
            return null;
        }

        for (SearchModel s : searches) {
            if (s.getSearchTerm().toLowerCase().equals(searchTerm.toLowerCase())) {
                long now = new Date().getTime();
                if (s.getExpireTime() > now) {
                    System.out.println("Found in cache.");
                    return s;
                } else {
                    System.out.println("found in cache, but expired.");
                    searches.remove(s);
                    break;
                }
            } else {
                return null;
            }
        }
        return getFromCache(searchTerm, count, offset);
    }

    public void addToCache(SearchModel model) {
        long now = new Date().getTime();
        model.setExpireTime(now + 1);//1 * 24 * 60 * 60 * 1000);
        searches.add(model);
    }
}
