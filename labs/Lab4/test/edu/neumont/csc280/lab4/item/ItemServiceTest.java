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

    @Test
    public void testCreateItemEmptyTitle() {
        AuctionItem item = null;
        Assert.assertNull(item);
        try {
            item = is.createItem("", "description", "imageUrl", Money.dollars(0.00d), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
            Assert.fail("Should have thrown an exception because of title.");
        } catch (AuctionException e) {

        }
    }

    @Test
    public void testCreateItemNullTitle() {
        AuctionItem item = null;
        Assert.assertNull(item);
        try {
            item = is.createItem(null, "description", "imageUrl", Money.dollars(0.00d), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
            Assert.fail("Should have thrown an exception because of title.");
        } catch (AuctionException e) {

        }
    }

    @Test
    public void testCreateItemEmptyDescription() {
        AuctionItem item = null;
        Assert.assertNull(item);
        try {
            item = is.createItem("Title", "", "imageUrl", Money.dollars(0.00d), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
            Assert.fail("Should have thrown an exception because of title.");
        } catch (AuctionException e) {

        }
    }

    @Test
    public void testCreateItemNullDescription() {
        AuctionItem item = null;
        Assert.assertNull(item);
        try {
            item = is.createItem("Title", null, "imageUrl", Money.dollars(0.00d), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
            Assert.fail("Should have thrown an exception because of title.");
        } catch (AuctionException e) {

        }
    }

    @Test
    public void testCreateItemEmptyImageUrl() {
        AuctionItem item = null;
        Assert.assertNull(item);
        try {
            item = is.createItem("Title", "description", "", Money.dollars(0.00d), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
            Assert.fail("Should have thrown an exception because of title.");
        } catch (AuctionException e) {

        }
    }

    @Test
    public void testCreateItemNullImageUrl() {
        AuctionItem item = null;
        Assert.assertNull(item);
        try {
            item = is.createItem("Title", "description", null, Money.dollars(0.00d), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
            Assert.fail("Should have thrown an exception because of title.");
        } catch (AuctionException e) {

        }
    }

    @Test
    public void testCreateItemInvalidStartPrice() {
        AuctionItem item = null;
        Assert.assertNull(item);
        try {
            item = is.createItem("title", "description", "imageUrl", Money.dollars(0.00d), new Date().getTime(), new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
            Assert.fail("Should have thrown an exception because of price.");
        } catch (AuctionException e) {

        }
    }

    @Test
    public void testCreateItemInvalidStartTime() {
        AuctionItem item = null;
        Assert.assertNull(item);
        try {
            item = is.createItem("title", "description", "imageUrl", Money.dollars(0.00d), 100, new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
            Assert.fail("Should have thrown an exception because of start time.");
        } catch (AuctionException e) {

        }
    }

    @Test
    public void testCreateItemEndTimeBeforeStart() {
        AuctionItem item = null;
        Assert.assertNull(item);
        try {
            item = is.createItem("title", "description", "imageUrl", Money.dollars(0.00d), new Date().getTime() + 3 * 24 * 60 * 60 * 1000, new Date().getTime() + 2 * 24 * 60 * 60 * 1000);
            Assert.fail("Should have thrown an exception because of end time.");
        } catch (AuctionException e) {

        }
    }

    @Test
    public void testGetItem() {
        AuctionItem item = null;
        Assert.assertNull(item);
        item = is.createItem("title", "description", "imageUrl", Money.dollars(0.01d),new Date().getTime(),new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
        Assert.assertNotNull(item);

        AuctionItem retrievedItem = is.getItem(item.getId());
        Assert.assertEquals(retrievedItem, item);
    }
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
