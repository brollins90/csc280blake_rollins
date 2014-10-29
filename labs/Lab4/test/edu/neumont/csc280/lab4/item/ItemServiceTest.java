package edu.neumont.csc280.lab4.item;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by blakerollins on 10/29/14.
 */
public class ItemServiceTest {
    private ItemService is = new HashMapItemService();

    @Test
    public void testCreateItem() {
        AuctionItem item = is.createItem();
        Assert.assertNotNull(item);
    }

}
