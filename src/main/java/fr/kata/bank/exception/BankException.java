package fr.kata.bank.exception;

import fr.kata.bank.utils.ErrorCode;

public class BankException extends Exception {

	private static final long serialVersionUID = 7874371951474065924L;
	
	private final ErrorCode code;

	public BankException() {
		super();
		code = null;
	}

	/**
	 * Constructor with message.
	 *
	 * @param message
	 *            .
	 */
	public BankException(String message) {
		super(message);
		code = null;
	}

	/**
	 * Constructor with the specified ErrorCode.
	 * 
	 * @param code
	 *            the error code.
	 */
	public BankException(final ErrorCode code) {
		super(code.getMsg(), null, true, false);
		this.code = code;
	}

	/**
	 * The code error.
	 *
	 * @return the code error.
	 */
	public ErrorCode getCode() {
		return code;
	}


}
