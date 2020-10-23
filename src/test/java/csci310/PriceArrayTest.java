
package csci310;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

public class PriceArrayTest {
	
//	@Test
//	public void testpopulateStockPrice() throws ParseException, IOException {
//		PriceArray PA = new PriceArray("IBM", 1, "10/05/2020", "10/09/2020");
//		PA.populateStockPrice();
//		Double roundResult = Math.round(PA.stockPrice[PA.stockPrice.length-1]*100.0)/100.0;
//		Double BIMPriceOnOct9 = 127.79;
//		assertEquals(roundResult, BIMPriceOnOct9); 
//	}
	
	@Test
	public void testpopulateStockPrice2() throws ParseException, IOException {
		PriceArray PA = new PriceArray("IBM", 1, "10/03/2020", "10/03/2020");
		PA.populateStockPrice();
		//PA.printPriceArray();
		assertTrue(PA.isEmpty);
	}
	
	@Test
	public void predictFuturePrices() throws ParseException, IOException {
		PriceArray PA = new PriceArray("IBM", 1, "10/05/2020", "10/31/2020");
		PA.populateStockPrice();
		String tradingDate = PA.tradingDate[PA.tradingDate.length-1];
		PA.printPriceArray();
		assertEquals(tradingDate, "10/31/2020");
	}
	
	
	
}