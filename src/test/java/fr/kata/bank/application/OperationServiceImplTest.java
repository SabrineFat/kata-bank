package fr.kata.bank.application;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import fr.kata.bank.exception.BankException;
import fr.kata.bank.model.Account;
import fr.kata.bank.utils.ErrorCode;
import fr.kata.bank.utils.OperationType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test the application with different test cases.
 * 
 * @author Sabrine
 *
 */
public class OperationServiceImplTest {

	private OperationServiceImpl operationService;
	private AccountService accountService;
	private Account account;

	@Before
	public void init() {
		operationService = new OperationServiceImpl();
		accountService = new AccountServiceImpl();
		account = new Account();
	}

	/**
	 * Test values for an empty account Empty account has a 0.0 balance Empty
	 * account has no operations yet
	 */
	@Test
	public void testEmptyAccountHasZeroBalanceAndZeroOperations() {
		assertNotNull(account.getBalance());
		assertThat(account.getBalance()).isEqualTo(BigDecimal.ZERO);
		assertThat(account.getOperations().size()).isEqualTo(0);
	}

	/**
	 * Adds money to the account and checks that the new balance is as expected.
	 * 
	 * @throws BankException
	 */
	@Test
	public void testDepositPositiveAmount() throws BankException {
		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal(100));
		assertThat(account.getBalance()).isEqualTo(new BigDecimal(100));
	}

	/**
	 * Checks that we can not deposit a null value of amount and that will
	 * throws an exception.
	 * 
	 * @throws BankException
	 */
	@Test(expected = BankException.class)
	public void testDepositOperationWithNullAmount() throws BankException {
		operationService.addOperation(account, OperationType.DEPOSIT, null);
	}

	/**
	 * Checks that we can not deposit a negative value of amount and that will
	 * throws an exception.
	 */
	@Test(expected = BankException.class)
	public void testDepositOperationWithNegativeAmount() throws BankException {
		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("-10"));
	}

	/**
	 * Checks that we can not withdraw a null value of amount and that will
	 * throws an exception.
	 * 
	 * @throws BankException
	 */
	@Test(expected = BankException.class)
	public void testWithdrawOperationWithNullAmount() throws BankException {
		operationService.addOperation(account, OperationType.WITHDRAWAL, null);
	}

	/**
	 * Checks that we can not withdraw a negative value of amount and that will
	 * throws an exception.
	 * 
	 * @throws BankException
	 */
	@Test(expected = BankException.class)
	public void testWithdrawNegativeAmount() throws BankException {
		operationService.addOperation(account, OperationType.WITHDRAWAL, new BigDecimal("-10"));
	}

	/**
	 * Withdraws money from the account and checks that the new balance of
	 * account is as expected.
	 * 
	 * @throws BankException
	 */
	@Test
	public void testWithdrawOperationAmountSmallerThanBalance() throws BankException {
		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("130.50"));
		operationService.addOperation(account, OperationType.WITHDRAWAL, new BigDecimal("120"));
		assertThat(account.getBalance()).isEqualTo(new BigDecimal("10.50"));
	}

	/**
	 * Tests that an operation of withdrawl with insufficient account balance
	 * throws exception.
	 * 
	 * @throws BankException
	 */
	@Test(expected = BankException.class)
	public void testWithdrawOperationAmountGreaterThanBalance() throws BankException {
		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal(100));
		operationService.addOperation(account, OperationType.WITHDRAWAL, new BigDecimal(200));
	}

	/**
	 * Tests that after a successful deposit the operation is saved.
	 * 
	 * @throws BankException
	 */
	@Test
	public void testOperationAccountAfterDeposit() throws BankException {

		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("55.44"));
		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("25.11"));
		assertThat(account.getBalance()).isEqualTo(new BigDecimal("80.55"));
		assertThat(accountService.getOperationsHistory(account).size()).isEqualTo(2);

	}

	/**
	 * Tests that after a successful withdrawal the operation is saved.
	 * 
	 * @throws BankException
	 */
	@Test
	public void testOperationAccountAfterWithdrawal() throws BankException {

		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("10.33"));
		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("79.34"));
		assertThat(account.getOperations().get(1).getBalance()).isEqualTo(new BigDecimal("89.67"));
		operationService.addOperation(account, OperationType.WITHDRAWAL, new BigDecimal("10.00"));
		assertThat(account.getBalance()).isEqualTo(new BigDecimal("79.67"));
		assertThat(accountService.getOperationsHistory(account).size()).isEqualTo(3);
	}

	/**
	 * Tests that an operation of withdrawl with insufficient account balance
	 * not saved and throws and exception with INSUFFICIENT_BALANCE message.
	 * 
	 * @throws BankException
	 */
	@Test
	public void testOperationNotSavedAfterIllegalWithdrawalOperation() throws BankException {
		try {
			operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("20.66"));
			operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("79.34"));
			
			assertThat(account.getBalance()).isEqualTo(new BigDecimal("100.00"));
			assertThat(account.getOperations().size()).isEqualTo(2);
			assertThat(account.getOperations().get(0).getBalance()).isEqualTo(new BigDecimal("20.66"));
			assertThat(account.getOperations().get(1).getBalance()).isEqualTo(new BigDecimal("100.00"));
			
			//illegal withdrawal operation
			operationService.addOperation(account, OperationType.WITHDRAWAL, new BigDecimal("200.00"));
			
		} catch (BankException e) {
			assertThat(account.getBalance()).isEqualTo(new BigDecimal("100.00"));
			// the operation of withdraw is not saved
			assertThat(account.getOperations().size()).isEqualTo(2);
			// thros an exception with INSUFFICIENT_BALANCE message
			assertThat(e.getCode()).isEqualTo(ErrorCode.INSUFFICIENT_BALANCE);
		}
	}

	/**
	 * Tests show the history of operation (Operation [Type = x, amount = x,
	 * balance=x, date = x]).
	 * 
	 * @throws BankException
	 */
	@Test
	public void testShowOperationHistory() throws BankException {
		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("350.250"));
		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("100"));
		operationService.addOperation(account, OperationType.WITHDRAWAL, new BigDecimal("200.00"));
		operationService.addOperation(account, OperationType.DEPOSIT, new BigDecimal("49.750"));
		accountService.showOperationsHistory(account);
	}

}
