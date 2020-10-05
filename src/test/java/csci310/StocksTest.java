package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

public class StocksTest {

	@Test
	public void testStocks() {
		Stocks s = new Stocks(1,"apple","1999/01/01","2020/01/01");
		assertTrue(true);
	}

	@Test
	public void testGetQuantity() {
		Stocks s = new Stocks(1,"apple","1999/01/01","2020/01/01");
		assertEquals(s.getQuantity(),1);
	}

	@Test
	public void testGetTicker() {
		Stocks s = new Stocks(1,"apple","1999/01/01","2020/01/01");
		assertEquals(s.getTicker(),"apple");
	}

	@Test
	public void testGetDayPurchase() {
		Stocks s = new Stocks(1,"apple","1999/01/01","2020/01/01");
		assertEquals(s.getDayPurchase(),"1999/01/01");
	}

	@Test
	public void testGetDaySold() {
		Stocks s = new Stocks(1,"apple","1999/01/01","2020/01/01");
		assertEquals(s.getDaySold(),"2020/01/01");
	}

}
