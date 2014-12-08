//package edu.neumont.csc280.lab7.auth;
//
//
//import javax.enterprise.context.RequestScoped;
//import javax.enterprise.inject.Produces;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//public class InjectionFilter implements Filter {
//
//    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        requestHolder.set((HttpServletRequest)servletRequest);
//
//    }
//
//    @Override
//    public void destroy() {
//        requestHolder.remove();
//    }
//
//    @Produces
//    @RequestScoped
//    public HttpServletRequest getInstance() {
//        return requestHolder.get();
//    }
//}
