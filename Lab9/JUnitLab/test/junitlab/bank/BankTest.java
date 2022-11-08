package junitlab.bank;

import junitlab.bank.impl.GreatSavingsBank;
import org.junit.Assert;
import org.junit.Test;

public class BankTest {
	GreatSavingsBank greatSavingsBank = new GreatSavingsBank();

	@Test
	public void testOpenAccount() throws AccountNotExistsException {
		String account = greatSavingsBank.openAccount();
		Assert.assertEquals(0, greatSavingsBank.getBalance(account));
	}

	@Test
	public void testUniqueAccount() {
		String account1 = greatSavingsBank.openAccount();
		String account2 = greatSavingsBank.openAccount();
		Assert.assertNotEquals(account1, account2);
	}

	@Test(expected = AccountNotExistsException.class)
	public void testInvalidAccount() throws AccountNotExistsException {
		greatSavingsBank.getBalance("");
	}

	@Test
	public void testDeposit() throws AccountNotExistsException {
		String account = greatSavingsBank.openAccount();
		greatSavingsBank.deposit(account, 2000);
		Assert.assertEquals(2000, greatSavingsBank.getBalance(account));
	}
}
