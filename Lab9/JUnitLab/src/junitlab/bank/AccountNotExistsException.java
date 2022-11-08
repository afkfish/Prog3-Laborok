package junitlab.bank;

/**
 * Ez a kivétel jelzi, ha érvénytelen vagy nem létezõ bankszámlaszámot
 * adtunk meg valamelyik tranzakció során.
 */
public class AccountNotExistsException extends BankException {
	
	/**
	 * A kivétel létrehozása.
	 * @param accountNumber A nem létezõ számlaszám.
	 */
	public AccountNotExistsException(String accountNumber) {
		super(accountNumber, "Account does not exist: " + accountNumber);
	}
}
