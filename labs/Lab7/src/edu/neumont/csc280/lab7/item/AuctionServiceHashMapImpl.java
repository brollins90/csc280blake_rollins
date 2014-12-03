package edu.neumont.csc280.lab7.item;//package edu.neumont.csc280.lab6.item;
//
//import edu.neumont.csc280.lab7.Money;
//import edu.neumont.csc280.lab7.search.SearchModel;
//import edu.neumont.csc280.lab7.web.ValidationResult;
//
//import javax.ejb.Local;
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import java.math.BigDecimal;
//import java.util.*;
//
//
//@Stateless
//@LocalBean
//@Local(AuctionService.class)
//public class AuctionServiceHashMapImpl implements AuctionService {
//
//    private static long nextItemId = 1;
//    @Inject
//    private SearchCache searchCache;
//    private final Map<Long, Auction> idToItemMap = new HashMap<>();
//
//    public AuctionServiceHashMapImpl() {
//        build();
//    }
//
//    @Override
//    public void build() {
//
//        for (int i = 1; i < 41; i++) {
//            Auction a = new Auction();
//            a.setTitle("Item " + i + " Title.");
//            a.setDescription("Item " + i + " description.");
//            a.setImageUrl("http://localhost:8080/lab6/img/item_" + i + ".png");
//            a.setPrice(Money.dollars(new BigDecimal(0.01d)).getAmount().doubleValue());
//            a.setStartTime(new Date());
//            a.setEndTime(new Date(a.getStartTime().getTime() + 7 * 24 * 60 * 60 * 1000));
//            create(a);
//        }
//    }
//
//    @Override
//    public Auction create(Auction auction) {
//
//        auction.setId(nextItemId++);
//        idToItemMap.put(auction.getId(), auction);
//        return auction;
//    }
//
//    @Override
//    public Auction retreive(Long id) {
//
//        return idToItemMap.get(id);
//    }
//
//    @Override
//    public Auction update(Auction auction) {
//
//        Auction current = retreive(auction.getId());
//        current.setTitle(auction.getTitle());
//        current.setDescription(auction.getDescription());
//        current.setImageUrl(auction.getImageUrl());
//        current.setPrice(auction.getPrice());
//        current.setStartTime(auction.getStartTime());
//        current.setEndTime(auction.getEndTime());
//
//        idToItemMap.put(current.getId(), current);
//
//        return current;
//    }
//
//    @Override
//    public void delete(Long id) {
//
//        idToItemMap.remove(id);
//
//    }
//
//    @Override
//    public Set<Auction> findByTextSearch(String query) {
//        return null;
//    }
//
//    @Override
//    public SearchModel findByTextSearch(String searchTerm, int count, int offset) {
//
//        if (searchTerm == null) {
//            searchTerm = "";
//        }
//
//        searchTerm = searchTerm.toLowerCase();
//
//        SearchResult searchResult = searchCache.getFromCache(searchTerm);
//        if (searchResult == null) {
//            System.out.println("Search was not in the cache.");
//
//            List<Auction> searchResults = new ArrayList<>();
//
//            for (Auction i : this.idToItemMap.values()) {
//                if (i.getTitle().toLowerCase().contains(searchTerm)) {
//                    searchResults.add(i);
//                } else if (i.getDescription().toLowerCase().contains(searchTerm)) {
//                    searchResults.add(i);
//                }
//            }
//
//            Collections.sort(searchResults, new Comparator<Auction>() {
//                @Override
//                public int compare(Auction a, Auction b) {
//                    return a.getTitle().compareTo(b.getTitle());
//                }
//            });
//
//            searchResult = new SearchResult();
//            searchResult.setItems(searchResults);
//            searchResult.setSearchTerm(searchTerm);
//
//            searchCache.addToCache(searchResult);
//        }
//
//        SearchModel model = searchResult.getModel(count, offset);
//
//        System.out.println("found " + model.getItems().size() + " items");
//        return model;//.getItems().subList(offset, offset + count);
//    }
//
//    @Override
//    public Auction placeBid(Long id, Double bidAmount) {
//
//        Auction auction = retreive(id);
//        Money newAmount = Money.dollars(bidAmount);
//
//        if (newAmount.getAmount().doubleValue() > auction.getPrice()) {
//            auction.setNumBids(auction.getNumBids()+1);
//            auction.setPrice(newAmount.getAmount().doubleValue());
//        }
//        return auction;
//    }
////
////    private Auction item(String title, String description, String imageUrl, Money startPrice, long startTime, long endTime) {
////
////        Auction item = new Auction("0");
////        updateItemTitle(item, title);
////        updateItemDescription(item, description);
////        updateItemImageUrl(item, imageUrl);
////        updateItemStartPrice(item, startPrice);
////        updateItemStartTime(item, startTime);
////        updateItemEndTime(item, endTime);
////        return item;
////    }
////
////    private void doPutItem(Auction item) {
////        synchronized (this) {
////
////            if (this.idToItemMap.containsKey(item.getId())) {
////                throw new AuctionException("The new ID aready exists.");
////            }
////
////            this.idToItemMap.put(item.getId(), item);
////        }
////    }
////
////    @Override
////    public Auction createItem(String title, String description, String imageUrl, Money startPrice, long startTime, long endTime) {
////
////        Auction item = item(title, description, imageUrl, startPrice, startTime, endTime);
////        String thisItemId = "" + nextItemId++;
////        item.setId(thisItemId);
////
////        doPutItem(item);
////
////        return item;
////    }
////
////
////    @Override
////    public void deleteItem(String id) {
////        synchronized (this) {
////            this.idToItemMap.remove(id);
////        }
////    }
////
////    @Override
////    public Auction getItem(String id) {
////        return this.idToItemMap.get(id);
////    }
////
////    @Override
////    public List<Auction> getItems() {
////        return new ArrayList<>(this.idToItemMap.values());
////    }
////
////    @Override
////    public SearchModel searchForItems(String searchTerm, int count, int offset) {
////
////        if (searchTerm == null) {
////            searchTerm = "";
////        }
////
////        searchTerm = searchTerm.toLowerCase();
////
////        SearchResult searchResult = searchCache.getFromCache(searchTerm);
////        if (searchResult == null) {
////            System.out.println("Search was not in the cache.");
////
////            List<Auction> searchResults = new ArrayList<>();
////
////            for (Auction i : this.idToItemMap.values()) {
////                if (i.getTitle().toLowerCase().contains(searchTerm)) {
////                    searchResults.add(i);
////                } else if (i.getDescription().toLowerCase().contains(searchTerm)) {
////                    searchResults.add(i);
////                }
////            }
////
////            Collections.sort(searchResults, new Comparator<Auction>() {
////                @Override
////                public int compare(Auction a, Auction b) {
////                    return a.getTitle().compareTo(b.getTitle());
////                }
////            });
////
////            searchResult = new SearchResult();
////            searchResult.setItems(searchResults);
////            searchResult.setSearchTerm(searchTerm);
////
////            searchCache.addToCache(searchResult);
////        }
////
////        SearchModel model = searchResult.getModel(count, offset);
////
////        System.out.println("found " + model.getItems().size() + " items");
////        return model;//.getItems().subList(offset, offset + count);
////    }
////
////    @Override
////    public void updateItem(String id, String title, String description, String imageUrl, Money startPrice, long startTime, long endTime) {
////
////        Auction item = this.getItem(id);
////
////        updateItemTitle(item, title);
////        updateItemDescription(item, description);
////        updateItemImageUrl(item, imageUrl);
////        updateItemStartPrice(item, startPrice);
////        updateItemStartTime(item, startTime);
////        updateItemEndTime(item, endTime);
////    }
////
////    private void updateItemTitle(Auction item, String newValue) {
////
////        if (item.hasValueChanged(item.getTitle(), newValue)) {
////            ValidationResult validationResult = item.validateTitle(newValue);
////            if (validationResult.getSuccess()) {
////                item.setTitle(newValue);
////            } else {
////                throw new AuctionException(validationResult.getFirstMessage());
////            }
////        }
////    }
////
////    private void updateItemDescription(Auction item, String newValue) {
////
////        if (item.hasValueChanged(item.getDescription(), newValue)) {
////            ValidationResult validationResult = item.validateDescription(newValue);
////            if (validationResult.getSuccess()) {
////                item.setDescription(newValue);
////            } else {
////                throw new AuctionException(validationResult.getFirstMessage());
////            }
////        }
////    }
////
////    private void updateItemImageUrl(Auction item, String newValue) {
////
////        if (item.hasValueChanged(item.getImageUrl(), newValue)) {
////            ValidationResult validationResult = item.validateImageUrl(newValue);
////            if (validationResult.getSuccess()) {
////                item.setImageUrl(newValue);
////            } else {
////                throw new AuctionException(validationResult.getFirstMessage());
////            }
////        }
////    }
////
////    private void updateItemStartPrice(Auction item, Money newValue) {
////
////        if (item.hasValueChanged(item.getStartPrice(), newValue)) {
////            ValidationResult validationResult = item.validateStartPrice(newValue);
////            if (validationResult.getSuccess()) {
////                item.setStartPrice(newValue);
////            } else {
////                throw new AuctionException(validationResult.getFirstMessage());
////            }
////        }
////    }
////
////    private void updateItemStartTime(Auction item, long newValue) {
////
////        if (item.hasValueChanged(item.getStartTime(), newValue)) {
////            ValidationResult validationResult = item.validateStartTime(newValue);
////            if (validationResult.getSuccess()) {
////                item.setStartTime(newValue);
////            } else {
////                throw new AuctionException(validationResult.getFirstMessage());
////            }
////        }
////    }
////
////    private void updateItemEndTime(Auction item, long newValue) {
////
////        if (item.hasValueChanged(item.getEndTime(), newValue)) {
////            ValidationResult validationResult = item.validateEndTime(newValue);
////            if (validationResult.getSuccess()) {
////                item.setEndTime(newValue);
////            } else {
////                throw new AuctionException(validationResult.getFirstMessage());
////            }
////        }
////    }
////
////    @Override
////    public void placeBid(String itemId, Bid bid) {
////        this.idToItemMap.get(itemId).placeBid(bid);
////    }
////
////
////}
//}
