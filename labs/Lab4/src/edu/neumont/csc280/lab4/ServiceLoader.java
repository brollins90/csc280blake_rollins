package edu.neumont.csc280.lab4;

import edu.neumont.csc280.lab4.auction.ArrayAuctionManager;
import edu.neumont.csc280.lab4.auction.AuctionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServiceLoader implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        AuctionManager manager = new ArrayAuctionManager();
        ctx.setAttribute("manager", manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
