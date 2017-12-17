package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		//Checks if removeTimedPayment() removes timedpayment from account
		testAccount.addTimedPayment("first", 2, 5, new Money(10000, SEK), SweBank, "Alice");
		assertTrue("After adding new timed payment, result should be true", testAccount.timedPaymentExists("first"));
		testAccount.removeTimedPayment("first");
		assertFalse("After removing new timed payment, result should be false", testAccount.timedPaymentExists("first"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		//Checks if TimedPayment works properly, by withdrawing money from fromaccount and adding them to toaccount after specific amount of ticks
		testAccount.addTimedPayment("first", 5, 5, new Money(10000, SEK), SweBank, "Alice");
		for(int i=0; i < 5; i++) {
			testAccount.tick();
		}
		assertEquals("After TimedPayment balance of testAccount should be 9990000", 9990000, (int)testAccount.getBalance().getAmount());
		assertEquals("After TimedPayment balance of Alice Account should be 1010000", 1010000,  (int)SweBank.getBalance("Alice"));
	}

	@Test
	public void testAddWithdraw() {
		//Checks whether deposit() adds money to account properly and whether withdraw() withdraws money properly 
		testAccount.deposit(new Money(10000, SEK));
		assertEquals("After deposit balance should be 10010000", 10010000, (int)testAccount.getBalance().getAmount());
		testAccount.withdraw(new Money(10000, SEK));
		assertEquals("After withdraw balance should be 10000000", 10000000, (int)testAccount.getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
		//Checks whether getBalance() of Account class returns proper balance of the given account
		assertEquals("Should return 10000000", 10000000, (int)testAccount.getBalance().getAmount());
	}
}
