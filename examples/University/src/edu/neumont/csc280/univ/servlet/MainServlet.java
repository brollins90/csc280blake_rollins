package edu.neumont.csc280.univ.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.regex.Matcher;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neumont.csc280.framework.AbstractControllerFactory.ClassResourceInfo;
import edu.neumont.csc280.framework.AbstractControllerFactory.OperationResourceInfo;
import edu.neumont.csc280.framework.BadRequestException;
import edu.neumont.csc280.framework.ModelAndView;
import edu.neumont.csc280.framework.NotFoundException;

/**
 * A servlet that can be used in conjuction with any set of controllers.
 * 
 * The controllers member variable is injected by the EJB container; it is Produced
 * by {@code ControllerFactory} in this project.
 * 
 * @author jcummings
 *
 */
@WebServlet("/app/*")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject Map<OperationResourceInfo, ClassResourceInfo> controllers;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String patterns = config.getInitParameter("urlPatternsToExclude");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		go(req, resp, "GET");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		go(req, resp, "POST");
	}
	
	private void go(HttpServletRequest req, HttpServletResponse resp, String method) 
			throws ServletException, IOException {
		for ( OperationResourceInfo info : controllers.keySet() ) {
			Matcher matcher = info.url.matcher(req.getRequestURI());
			if ( matcher.find() && method.equals(info.method) ) {
				ClassResourceInfo cInfo = controllers.get(info);
				Class<?>[] types = cInfo.method.getParameterTypes();
				Object[] values = new Object[types.length];
				for ( int i = 0; i < types.length; i++ ) {
					if ( types[i].isAssignableFrom(Long.class) ) {
						values[i] = Long.parseLong(matcher.group(i + 1));
					} else if ( types[i].isAssignableFrom(String.class) ) {
						values[i] = matcher.group(i + 1);
					}
				}
				
				try {
					ModelAndView mav = (ModelAndView) cInfo.method.invoke(cInfo.instance, values);
					if ( mav.isRedirect() ) {
						resp.sendRedirect(mav.getViewName());
					} else {
						req.setAttribute("model", mav.getModel());
						RequestDispatcher rd = req.getRequestDispatcher(mav.getViewName());
						rd.forward(req, resp);
					}
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					throw new ServletException(e);
				} catch ( NotFoundException e ) {
					resp.setStatus(404);
					resp.getWriter().println(e.getMessage());
				} catch ( BadRequestException e ) {
					resp.setStatus(400);
					resp.getWriter().println(e.getMessage());
				}
				
				return;
			}
		}
	}
}
