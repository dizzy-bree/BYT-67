package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		//Checks if getAmount() properly returns amount of given Money
		assertEquals(10000,(int)SEK100.getAmount());
		assertEquals(1000,(int)EUR10.getAmount());
		assertEquals(20000,(int)SEK200.getAmount());
		assertEquals(2000,(int)EUR20.getAmount());
		assertEquals(0,(int)SEK0.getAmount());
		assertEquals(0,(int)EUR0.getAmount());
		assertEquals(-10000,(int)SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency() {
		//Checks if getCurrency() returns proper currency of given Money
		assertEquals("Currency should be SEK", SEK, SEK100.getCurrency());
		assertEquals("Currency should be EUR", EUR, EUR10.getCurrency());
		assertEquals("Currency should be SEK", SEK, SEK200.getCurrency());
		assertEquals("Currency should be EUR", EUR, EUR20.getCurrency());
		assertEquals("Currency should be SEK", SEK, SEK0.getCurrency());
		assertEquals("Currency should be EUR", EUR, EUR0.getCurrency());
		assertEquals("Currency should be SEK", SEK, SEKn100.getCurrency());
	}

	@Test
	public void testToString() {
		//Checks if toString() properly prints out Money object
		assertEquals("100.00 SEK",SEK100.toString());
		assertEquals( "10.00 EUR",EUR10.toString());
		assertEquals("200.00 SEK",SEK200.toString());
		assertEquals("20.00 EUR",EUR20.toString());
		assertEquals("0.00 SEK",SEK0.toString());
		assertEquals("0.00 EUR",EUR0.toString());
		assertEquals("-100.00 SEK",SEKn100.toString());
	}

	@Test
	public void testGlobalValue() {
		//Checks if universalValue() properly converts Money value to universal one
		assertEquals(1500, (int)SEK100.universalValue());
		assertEquals(1500, (int)EUR10.universalValue());
		assertEquals(3000, (int)SEK200.universalValue());
		assertEquals(3000, (int)EUR20.universalValue());
		assertEquals(0,(int)SEK0.universalValue());
		assertEquals(0,(int)EUR0.universalValue());
		assertEquals(-1500,(int)SEKn100.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		//Checks if equals() is checking equality of values of different Money correctly
		assertTrue("SEK100 shoud have the same value as EUR10", SEK100.equals(EUR10));
		assertTrue("SEK200 should have the same value as EUR20", SEK200.equals(EUR20));
		assertFalse("EUR20 should have different value from SEK100", EUR20.equals(SEK100));
		assertFalse("SEKn100 should have different value from SEK100", SEKn100.equals(SEK100));
	}

	@Test
	public void testAdd() {
		//Checks if add() properly sums amount of Moneys, regardless of their Currency
		assertEquals("SEK100 + SEK200 should be 30000", 30000, (int)SEK100.add(SEK200).getAmount());
		assertEquals("SEK100 + EUR20 should be 30000", 30000, (int)SEK100.add(EUR20).getAmount());
		assertEquals("SEK0 + SEKn100 should be -10000", -10000, (int)SEK0.add(SEKn100).getAmount());
	}

	@Test
	public void testSub() {
		//Checks if sub() properly subtracts one Money from another, regardless of their Currency
		assertEquals("SEK100 - SEK200 should be -10000", -10000, (int)SEK100.sub(SEK200).getAmount());
		assertEquals("SEK100 - EUR10 should be 0", 0, (int)SEK100.sub(EUR10).getAmount());
		assertEquals("SEK0 - SEKn100 should be 10000", 10000, (int)SEK0.sub(SEKn100).getAmount());
	}

	@Test
	public void testIsZero() {
		//Checks if amount of the given Money is 0
		assertFalse("Should not be zero", SEK100.isZero());
		assertTrue("Should be zero", EUR0.isZero());
		assertFalse("Should not be zero", SEKn100.isZero());
	}

	@Test
	public void testNegate() {
		//Checks if negate properly negated amount of given Money
		assertEquals("Should be -10000", -10000, (int)SEK100.negate().getAmount());
		assertEquals("Should be zero", 0, (int)EUR0.negate().getAmount());
		assertEquals("Should be 10000", 10000, (int)SEKn100.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
		//Checks if compareTo() correctly compares values of different Money
		assertEquals("SEK100 shoud be as valiable as EUR10", 0, SEK100.compareTo(EUR10));
		assertEquals("SEK200 shoud be more valiable than EUR10", 1, SEK200.compareTo(EUR10));
		assertEquals("SEK0 shoud be less valiable than EUR10", -1, SEK0.compareTo(EUR10));
		assertEquals("SEKn100 shoud be less valiable than EUR0", -1, SEKn100.compareTo(EUR0));
	}
}
