package fr.kata.bank.application;

import java.util.List;

import fr.kata.bank.exception.BankException;
import fr.kata.bank.model.Account;
import fr.kata.bank.model.Operation;

/**
 * AccountService.
 * @author Sabrine
 *
 */
public interface AccountService {
	
	/**
	 * Returns account operations history.
	 * 
	 * @param account
	 *            the account
	 * @return a list of operations
	 * @throws BankException
	 *             Signals that an BankException has occurred.
	 */
	List<Operation> getOperationsHistory(Account account) throws BankException;

	/**
	 * Shows account operations history.
	 * 
	 * @param account
	 *            the account
	 * @throws BankException
	 *             Signals that a BankException has occurred.
	 */
	void showOperationsHistory(Account account) throws BankException;
	
}
