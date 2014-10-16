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
//
//        String url = ctx.getInitParameter("DBURL");
//        String u = ctx.getInitParameter("DBUSER");
//        String p = ctx.getInitParameter("DBPWD");

        //create database connection from init parameters and set it to context
        //DBConnectionManager dbManager = new DBConnectionManager(url, u, p);
        AuctionManager manager = new ArrayAuctionManager();
        ctx.setAttribute("manager", manager);

//        ItemGetController itemGetController = new ItemGetController();
//        ctx.setAttribute("itemGetController", itemGetController);
//        System.out.println("Created the auction manager.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
//        DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
//        dbManager.closeConnection();
//        System.out.println("Database connection closed for Application.");
    }
}
