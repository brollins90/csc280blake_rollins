package edu.neumont.csc280.lab4.item;

import edu.neumont.csc280.lab4.Money;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
* Created by blakerollins on 10/29/14.
*/
public class ItemServiceTest {
    private ItemService is = new ItemServiceHashMapImpl();

    @Test
    public void testCreateItem() {
        AuctionItem item = null;
        Assert.assertNull(item);
        item = is.createItem("title", "description", "imageUrl", Money.dollars(0.01d),new Date().getTime(),new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
        Assert.assertNotNull(item);
    }
//
//    @Test
//    public void testGetItem() {
//        //todo
//    }
//
//    @Test
//    public void testGetItems() {
//        //todo
//    }
//
//    @Test
//    public void testUpdateItemDescription() {
//        AuctionItem item = is.createItem();
//        String itemId = item.getId();
//
//        item.setDescription("first");
//        Assert.assertEquals("first", item.getDescription());
//        is.updateItemDescription(itemId, "second");
//        Assert.assertEquals("second", item.getDescription());
//    }
//
//    @Test
//    public void testUpdateItemDescriptionHTML() {
//        //todo
//    }
//
//    @Test
//    public void testUpdateItemEndTime() {
//        //todo
//    }
//
//    @Test
//    public void testUpdateItemEndTimeBeforeStart() {
//        //todo
//    }
//
//    @Test
//    public void testUpdateItemStartTime() {
//        //todo
//    }
//
//    @Test
//    public void testUpdateItemStartTimeBeforeToday() {
//        //todo
//    }
//
//    @Test
//    public void testUpdateItemStartTimeNegativeDay() {
//        //todo
//    }
//
//    @Test
//    public void testUpdateItemImageUrl() {
//        //todo
//    }
//
//    @Test
//    public void testUpdateItemTitle() {
//        //todo
//    }
//
//    @Test
//    public void testPlaceBid() {
//        //todo
//    }

}
