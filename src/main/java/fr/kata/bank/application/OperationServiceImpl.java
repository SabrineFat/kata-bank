package fr.kata.bank.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import fr.kata.bank.exception.BankException;
import fr.kata.bank.model.Account;
import fr.kata.bank.model.Operation;
import fr.kata.bank.utils.ErrorCode;
import fr.kata.bank.utils.OperationType;

/**
 * 
 * @author Sabrine
 *
 */
public class OperationServiceImpl implements OperationService{
	
	@Override
	public void addOperation(Account account, OperationType operationType, BigDecimal amount) throws BankException {
		if (amount == null) {
			throw new BankException(ErrorCode.AMOUNT_ERROR);
		}
		if(OperationType.DEPOSIT.equals(operationType)){
			depositMoney(account, amount);
		}else{
			withdrawMoney(account, amount);
		}
	}
	
	private void depositMoney(Account account, BigDecimal amount) throws BankException {
		if (amount.compareTo(BigDecimal.ZERO) == -1) {
			throw new BankException(ErrorCode.DEPOSIT_AMOUNT_ERROR);
		}
		account.setBalance(account.getBalance().add(amount));
		account.getOperations().add(new Operation(OperationType.DEPOSIT, amount, account.getBalance(), LocalDateTime.now()));
	}

	private void withdrawMoney(Account account, BigDecimal amount) throws BankException {
		if (amount.compareTo(BigDecimal.ZERO) == -1) {
			throw new BankException(ErrorCode.WITHDRAWAL_AMOUNT_ERROR);
		}
		if (!hasSufficientBalance(account, amount)) {
			throw new BankException(ErrorCode.INSUFFICIENT_BALANCE);
		}
		account.setBalance(account.getBalance().subtract(amount));
		account.getOperations().add(new Operation(OperationType.WITHDRAWAL, amount, account.getBalance(), LocalDateTime.now()));
	}

	public boolean hasSufficientBalance(Account account, BigDecimal amount) {
		return account.getBalance().compareTo(amount) == 1;
	}


}
