package fr.kata.bank.application;

import java.util.List;

import fr.kata.bank.exception.BankException;
import fr.kata.bank.model.Account;
import fr.kata.bank.model.Operation;
import fr.kata.bank.utils.ErrorCode;

/**
 * 
 * @author Sabrine
 *
 */
public class AccountServiceImpl implements AccountService{

	@Override
	public List<Operation> getOperationsHistory(Account account) throws BankException {
		if(account == null){
			throw new BankException(ErrorCode.ACCOUNT_INEXISTENT);
		}
		return account.getOperations();
	}

	@Override
	public void showOperationsHistory(Account account) throws BankException {
		if(account == null){
			throw new BankException(ErrorCode.ACCOUNT_INEXISTENT);
		}
		account.getOperations().forEach(System.out::println);
	}
	
}
