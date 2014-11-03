package edu.neumont.csc280.lab4.web;

import edu.neumont.csc280.lab4.item.ItemService;
import edu.neumont.csc280.lab4.item.ItemServiceHashMapImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServiceLoader implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        ItemService manager = null;
        try {
            String managerType = ctx.getInitParameter("managertype");
            System.out.println("about to create a manager of type: " + managerType);
            Class t = Class.forName(managerType);
            manager = (ItemService) t.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            manager = new ItemServiceHashMapImpl();
        }

        ctx.setAttribute("manager", manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
