package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		/* Checks if getName() returns correct output */
		assertEquals(SEK.getName(),"SEK");
		assertEquals(DKK.getName(),"DKK");
		assertEquals(EUR.getName(),"EUR");
	}
	
	@Test
	public void testGetRate() {
		/* Checks if getRate() returns correct output */
		assertEquals(new Double(0.15),SEK.getRate());
		assertEquals(new Double(0.20),DKK.getRate());
		assertEquals(new Double(1.5),EUR.getRate());
	}
	
	@Test
	public void testSetRate() {
		/* Checks if setRate() works properly, by setting a new rate and the returning it with getRate() method */
		SEK.setRate(0.89);
		assertEquals("New rate of SEK should be 0.89",new Double(0.89),SEK.getRate());
		DKK.setRate(5.1);
		assertEquals("New rate of DKK should be 5.1",new Double(5.1),DKK.getRate());
		EUR.setRate(0.8);
		assertEquals("New rate of EUR should be 0.8",new Double(0.8),EUR.getRate());
	}
	
	@Test
	public void testGlobalValue() {
		/* Checks if calculating of global value of currency is done properly*/
		assertEquals("Global value of SEK should be 11835", 11835,(int)SEK.universalValue(78900));
		assertEquals("Global value of DKK should be 200", 200,(int) DKK.universalValue(1000));
		assertEquals("Global value of EUR should be 1500", 1500,(int)EUR.universalValue(1000));
	}
	
	@Test
	public void testValueInThisCurrency() {
		/* Checks if converting from one currency to another was done properly*/
		assertEquals("DKK converted to SEK should be 20800",20800,(int)SEK.valueInThisCurrency(15600, DKK));
		assertEquals("SEK converted to EUR should be 7440",7440,(int)EUR.valueInThisCurrency(74400, SEK));
		assertEquals("EUR converted to DKK should be 750",750,(int)DKK.valueInThisCurrency(100, EUR));
	}

}
