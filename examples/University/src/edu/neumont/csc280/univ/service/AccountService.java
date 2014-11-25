package edu.neumont.csc280.univ.service;

import edu.neumont.csc280.univ.model.Account;

public interface AccountService {
	public Account getAccountByUsername(String username);
	public Account getAccount(Long id);
	public void updateAccount(Account account);
}
