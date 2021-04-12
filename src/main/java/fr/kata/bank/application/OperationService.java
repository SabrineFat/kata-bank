package fr.kata.bank.application;

import java.math.BigDecimal;

import fr.kata.bank.exception.BankException;
import fr.kata.bank.model.Account;
import fr.kata.bank.utils.OperationType;

/**
 * OperationService
 * @author Sabrine
 *
 */
public interface OperationService {

	/**
	 * Adds money to the account.
	 * 
	 * @param account
	 *            the account
	 * @param operationType
	 *            type of operation DEPOSIT/WITHDRAWAL
	 * @param amount
	 *            the amount of the operation
	 * @throws BankException
	 *             Signals that an BankException has occurred.
	 */
	public void addOperation(Account account, OperationType operationType, BigDecimal amount) throws BankException;

	/**
	 * Checks if the account has a sufficient balance or not
	 * 
	 * @param account
	 *            the account
	 * @param amount
	 *            the money to withdraw
	 * @return true if the account has a sufficient balance, false if not
	 */
	boolean hasSufficientBalance(Account account, BigDecimal amount);

}
