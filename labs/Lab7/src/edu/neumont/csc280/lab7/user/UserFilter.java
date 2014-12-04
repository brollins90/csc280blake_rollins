package edu.neumont.csc280.lab7.user;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by blakerollins on 12/3/14.
 */
@WebFilter("/*")
public class UserFilter implements Filter {

    @Inject
    UserService userService;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = ((HttpServletRequest) request);
            try {
                Principal p = req.getUserPrincipal();

                if (p != null) {
                    User user = userService.getUserByUsername(p.getName());
                    CurrentUser.setUser(user);
                }
                chain.doFilter(request, response);
            } finally {
                CurrentUser.setUser(null);
            }
        } else {
            chain.doFilter(request, response);
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
