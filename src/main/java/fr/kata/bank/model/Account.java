package fr.kata.bank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
	
	/**
	 * Account's balance.
	 */
	private BigDecimal balance = BigDecimal.ZERO;
	
	/**
	 * The list of operations executed for this account.
	 */
	private List<Operation> operations = new ArrayList<>();
	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operationList) {
		this.operations = operationList;
	}
	
}
