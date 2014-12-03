package edu.neumont.csc280.lab6.item;

import edu.neumont.csc280.lab6.search.SearchModel;

import javax.ejb.Local;
import java.util.Set;

@Local
public interface AuctionService {

    void build();

    Auction create(Auction auction);

    Auction retreive(Long id);

    Auction update(Auction auction);

    void delete(Long id);

    Set<Auction> findByTextSearch(String query);

    public SearchModel findByTextSearch(String searchTerm, int count, int offset);

    Auction placeBid(Long id, Double bidAmount);
}
