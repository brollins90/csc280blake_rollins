package edu.neumont.csc280.lab4.web;

import edu.neumont.csc280.lab4.auction.ArrayAuctionManager;
import edu.neumont.csc280.lab4.auction.AuctionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServiceLoader implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        AuctionManager manager = null;
        try {
            String managerType = ctx.getInitParameter("managertype");
            System.out.println("about to create a manager of type: " + managerType);
            Class t = Class.forName(managerType);
            manager = (AuctionManager) t.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            manager = new ArrayAuctionManager();
        }

        ctx.setAttribute("manager", manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
