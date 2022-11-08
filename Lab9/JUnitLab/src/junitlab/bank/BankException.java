package junitlab.bank;

/**
 * A bankkal kapcsolatos kivételek õsosztálya.
 */
public abstract class BankException extends Exception {

	/**
	 * A számlaszám, amelynél a hiba fellépett.
	 */
	private String accountNumber;

	/**
	 * A kivétel létrehozása.
	 * @param accountNumber A számlaszám, amelynél a hiba fellépett.
	 */
	public BankException(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}
	
	/**
	 * A kivétel létrehozása az adott hibaüzenettel.
	 * @param accountNumber A számlaszám, amelynél a hiba fellépett.
	 * @param message A hibaüzenet szövege.
	 */
	public BankException(String accountNumber, String message) {
		super(message);
		this.accountNumber = accountNumber;
	}
	
	/**
	 * A hibát okozó számlaszám lekérdezése.
	 * @return A számlaszám, amelynél a kivétel fellépett.
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
}
