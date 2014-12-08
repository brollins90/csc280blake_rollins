//package edu.neumont.csc280.lab7.auth;
//
//import edu.neumont.csc280.lab7.user.CurrentUser;
//import edu.neumont.csc280.lab7.user.User;
//import edu.neumont.csc280.lab7.user.UserService;
//
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.Produces;
//import java.io.IOException;
//import java.security.Principal;
//
//@WebFilter("/*")
//public class AuthenticationFilter implements Filter {
//
//    @Inject
//    UserService userService;
//
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if (request instanceof HttpServletRequest) {
//            HttpServletRequest req = ((HttpServletRequest) request);
//            try {
//                Principal p = req.getUserPrincipal();
//
//                if (p != null) {
//                    User user = userService.getUserByUsername(p.getName());
//                    CurrentUser.setUser(user);
//                }
//                chain.doFilter(request, response);
//            } finally {
//                CurrentUser.setUser(null);
//            }
//        } else {
//            chain.doFilter(request, response);
//        }
//
//
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//
//    }
//
//    @Produces
//    @RequestScoped
//    public User getUser() {
//
//    }
//
//}
