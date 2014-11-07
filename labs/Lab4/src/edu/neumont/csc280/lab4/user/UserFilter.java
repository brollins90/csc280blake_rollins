package edu.neumont.csc280.lab4.user;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by blakerollins on 11/6/14.
 */
@WebFilter("/*")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //before the request
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            User user = (User) request.getSession().getAttribute("loggedInUser");

            try {


                UserRepository.setUser(user);

                // pass the request along the filter chain
                chain.doFilter(req, resp);
            } finally {

                // after the request
                UserRepository.setUser(null); // make sure we null the logged in user in case the thread is reused

            }

        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
