package fr.kata.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import fr.kata.bank.utils.OperationType;

public class Operation {

	/**
	 * Deposit or withdrawl
	 */
	private OperationType operationType;

	/**
	 * Operation amount.
	 */
	private BigDecimal amount;

	/**
	 * The date of operation.
	 */
	private LocalDateTime dateTime;

	/**
	 * Account's balance after the execution of this operation.
	 */
	private BigDecimal balance = BigDecimal.ZERO;

	public Operation() {
	}

	public Operation(OperationType operationType, BigDecimal amount, BigDecimal balance, LocalDateTime dateTime) {
		super();
		this.operationType = operationType;
		this.amount = amount;
		this.balance = balance;
		this.dateTime = dateTime;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Operation [Type = " + operationType + ", amount = " + amount + ", balance=" + balance + ", date = "
				+ dateTime + "]";
	}

}
