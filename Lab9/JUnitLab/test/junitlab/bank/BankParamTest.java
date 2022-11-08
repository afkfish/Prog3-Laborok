package junitlab.bank;

// import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BankParamTest {
	private final int amount;
	private final int rounded;

	public BankParamTest(int am, int ro) {
		this.amount = am;
		this.rounded = ro;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {{1100, 1100}, {1101, 1100}, {1149, 1100}, {1150, 1200}, {1151, 1200}, {1199, 1200}, {1200, 1200}});
	}

	@Test
	public void testWithdrawRounding() throws AccountNotExistsException, NotEnoughFundsException {
		GreatSavingsBank greatSavingsBank = new GreatSavingsBank();
		String account = greatSavingsBank.openAccount();
		greatSavingsBank.deposit(account, 10000);
		greatSavingsBank.withdraw(account, amount);
		Assert.assertEquals(10000-rounded, greatSavingsBank.getBalance(account));
	}
}
