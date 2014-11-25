package edu.neumont.csc280.univ.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import edu.neumont.csc280.framework.Controller;
import edu.neumont.csc280.framework.ModelAndView;
import edu.neumont.csc280.framework.UrlMapping;
import edu.neumont.csc280.security.PasswordEncoder;
import edu.neumont.csc280.univ.model.Account;
import edu.neumont.csc280.univ.service.AccountService;

@Stateless
@LocalBean
public class AccountController implements Controller {
	@Inject AccountService accountService;
	@Inject HttpServletRequest request;
	@Inject PasswordEncoder passwordEncoder;
	
	@UrlMapping(value="/account", method="POST")
	public ModelAndView executeCreateAccount() {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(passwordEncoder.encode(password));
		
		accountService.updateAccount(account);
		
		return new ModelAndView(null, request.getContextPath() + "/app/student/list", true);
	}

	@UrlMapping(value="/account", method="GET")
	public ModelAndView prepareCreateAccount() {
		return new ModelAndView(null, "/WEB-INF/account/edit.jsp");
	}

}
