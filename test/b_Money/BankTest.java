package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		//Checks if getName() returns proper name of Bank
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		//Checks if getCurrency() returns proper currency of Bank
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		//Checks if openAccount() creates new account in this Bank
		Nordea.openAccount("Tom");
		assertEquals("If account was oppened, it should initially have some balance equal to 0 ", 0, (int)Nordea.getBalance("Tom"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		//Checks if deposit() adds some money to account  
		SweBank.deposit("Bob", new Money(10000, SEK));
		assertEquals("After deposit balance should be 10000", 10000, (int)SweBank.getBalance("Bob"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		//Checks if withdraw() adds some money to account  
		SweBank.deposit("Bob", new Money(10000, SEK));
		assertEquals("After deposit balance should be 10000", 10000, (int)SweBank.getBalance("Bob"));
		SweBank.withdraw("Bob", new Money(10000, SEK));
		assertEquals("After withdraw balance should be 0", 0, (int)SweBank.getBalance("Bob"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		//Checks if getBalance() returns proper balance of an Account of this Bank
		assertEquals("Balance shoud be 0", 0, (int)SweBank.getBalance("Bob"));
		assertEquals("Balance shoud be 0", 0, (int)DanskeBank.getBalance("Gertrud"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		//Checks if transfer() properly transfers money from one account to another (in a same bank)
		SweBank.transfer("Ulrika", "Bob", new Money(10000, SEK));
		assertEquals("After transfer balance should be -10000", -10000, (int)SweBank.getBalance("Ulrika"));
		assertEquals("After transfer balance should be 10000", 10000, (int)SweBank.getBalance("Bob"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		//Checks if TimedPayment works properly, by withdrawing money from fromaccount and adding them to toaccount after specific amount of ticks
		SweBank.addTimedPayment("Bob", "first", 5, 5, new Money(10000, SEK), DanskeBank, "Gertrud");
		for(int i =0; i < 5; i++) {
			SweBank.tick();
		}
		assertEquals("After timed payment transfer balance should be -10000", -10000, (int)SweBank.getBalance("Bob"));
		assertEquals("After timed payment transfer balance should be 7500", 7500, (int)DanskeBank.getBalance("Gertrud"));
	}
}
