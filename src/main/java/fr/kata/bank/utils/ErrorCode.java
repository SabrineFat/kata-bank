package fr.kata.bank.utils;

public enum ErrorCode {

	
	ACCOUNT_INEXISTENT("Le compte n'existe pas"),
	
	INSUFFICIENT_BALANCE("Le montant de votre compte est insuffisant pour l'opération"),
	
	AMOUNT_ERROR("Le montant ne doit pas être null"),
	
	DEPOSIT_AMOUNT_ERROR("Le montant à déposer doit être un nombre positif"),
	
	WITHDRAWAL_AMOUNT_ERROR("Le montant à retirer doit être un nombre positif");

	private String msg;

	/**
	 * Constructor with message.
	 *
	 * @param msg
	 *            .
	 */
	private ErrorCode(final String msg) {
		this.msg = msg;
	}

	/**
	 * The msg.
	 *
	 * @return the msg.
	 */
	public String getMsg() {
		return msg;
	}

}
