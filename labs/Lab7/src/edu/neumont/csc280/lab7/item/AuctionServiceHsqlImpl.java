package edu.neumont.csc280.lab7.item;

import edu.neumont.csc280.lab7.Money;
import edu.neumont.csc280.lab7.search.SearchModel;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Stateless
@LocalBean
@Local(AuctionService.class)
public class AuctionServiceHsqlImpl implements AuctionService {

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private SearchCache searchCache;

    private static boolean built = false;

    public AuctionServiceHsqlImpl() {
//        build();
    }

    @Override
    public void build() {

        if (!built) {
            for (int i = 1; i < 41; i++) {
                Auction a = new Auction();
                a.setTitle("Item " + i + " Title.");
                a.setDescription("Item " + i + " description.");
                a.setImageUrl("http://localhost:8080/lab6/img/item_" + i + ".png");
                a.setPrice(Money.dollars(new BigDecimal(0.01d)));
                a.setStartTime(new Date());
                a.setEndTime(new Date(a.getStartTime().getTime() + 7 * 24 * 60 * 60 * 1000));
                create(a);
            }

            built = true;
        }
    }

    @Override
    public Auction create(Auction auction) {
        if (auction == null) {
            throw new IllegalArgumentException("Auction cannot be null.");
        }

        TypedQuery<Auction> query = entityManager.createNamedQuery("Auction.byTitle", Auction.class);
        query.setParameter("title", auction.getTitle());
        Auction existingAuction = first(query.getResultList());

        if (existingAuction != null) {
            throw new IllegalArgumentException("Auction title already exists");
        }

        entityManager.persist(auction);
        return auction;

    }

    @Override
    public Auction retreive(Long id) {
        TypedQuery<Auction> query = entityManager.createNamedQuery("Auction.byId", Auction.class);
        query.setParameter("id", id);
        Auction auction = first(query.getResultList());
        return auction;

    }

    @Override
    public Auction update(Auction auction) {
        Auction current = retreive(auction.getId());

        if (current == null) {
            throw new IllegalArgumentException("Unknown auction id");
        }

        current.setTitle(auction.getTitle());
        current.setDescription(auction.getDescription());
        current.setImageUrl(auction.getImageUrl());
        if (current.getNumBids() < 1) {
            current.setPrice(auction.getPrice());

            long startMillis = auction.getStartTime().getTime();

            if (startMillis > new Date().getTime()) {
                current.setStartTime(auction.getStartTime());
                if (startMillis < auction.getEndTime().getTime()) {
                    current.setEndTime(auction.getEndTime());
                } else {
                    current.setEndTime(new Date(auction.getEndTime().getTime() + 7 * 24 * 60 * 60 * 1000));
                }
            }
        }
        return current;
    }

    @Override
    public void delete(Long id) {
        Auction auction = retreive(id);

        if (auction != null) {
            entityManager.remove(auction);
        }

    }

    @Override
    public Set<Auction> findByTextSearch(String query) {
        return null;
    }

    @Override
    public SearchModel findByTextSearch(String searchTerm, int count, int offset) {

        if (searchTerm == null) {
            searchTerm = "";
        }

        searchTerm = searchTerm.toLowerCase();

        SearchResult searchResult = searchCache.getFromCache(searchTerm);
        if (searchResult == null) {
            System.out.println("Search was not in the cache.");

            TypedQuery<Auction> query = entityManager.createNamedQuery("Auction.like", Auction.class);
            query.setParameter("like", "%" + searchTerm.toUpperCase() + "%");
            List<Auction> searchResults = query.getResultList();
//
//            Collections.sort(searchResults, new Comparator<Auction>() {
//                @Override
//                public int compare(Auction a, Auction b) {
//                    return a.getTitle().compareTo(b.getTitle());
//                }
//            });

            searchResult = new SearchResult();
            searchResult.setItems(searchResults);
            searchResult.setSearchTerm(searchTerm);

            searchCache.addToCache(searchResult);
        }

        SearchModel model = searchResult.getModel(count, offset);

        System.out.println("found " + model.getItems().size() + " items");
        return model;//.getItems().subList(offset, offset + count);
    }

    @Override
    public Auction placeBid(Long id, Double bidAmount) {

        Auction auction = retreive(id);
        Money newAmount = Money.dollars(bidAmount);

        if (auction != null) {

            if (bidAmount > 0.01) {
                auction.setNumBids(auction.getNumBids() + 1);
                auction.setPrice(newAmount);
            }
//            Double currentBidValue = (Double) entityManager.createQuery("SELECT MAX(b.amount) FROM Bid b WHERE b.auction = :auction")
//                    .setParameter("auction", auction)
//                    .getSingleResult();
//
//            if (currentBidValue == null || bidAmount > currentBidValue) {
//                Bid bid = new Bid();
//                bid.setAuction(auction);
//                bid.setAmount(bidAmount);
//                entityManager.persist(bid);
//                auction.addBid(bid);
//            }
        }
        return auction;
    }

    private <T> T first(Collection<T> col) {
        return col == null ? null : col.size() == 0 ? null : col.iterator().next();
    }

}
