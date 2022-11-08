package junitlab.bank;

import junitlab.bank.impl.GreatSavingsBank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTestFixture {
	GreatSavingsBank greatSavingsBank;
	String account1, account2;

	@Before
	public void setUp() throws AccountNotExistsException {
		greatSavingsBank = new GreatSavingsBank();
		account1 = greatSavingsBank.openAccount();
		account2 = greatSavingsBank.openAccount();
		greatSavingsBank.deposit(account1, 1500);
		greatSavingsBank.deposit(account2, 12000);
	}

	@Test
	public void testTransfer() throws NotEnoughFundsException, AccountNotExistsException {
		greatSavingsBank.transfer(account2, account1, 3456);
		Assert.assertEquals(12000-3456, greatSavingsBank.getBalance(account2));
		Assert.assertEquals(1500+3456, greatSavingsBank.getBalance(account1));
	}

	@Test(expected = NotEnoughFundsException.class)
	public void testTransferWithoutEnoughFunds() throws NotEnoughFundsException, AccountNotExistsException {
		greatSavingsBank.transfer(account1, account2, 3456);
	}
}
