package csci310;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

public class PortfolioTest {
	
	@Test
	public void testAddStock() throws ParseException {
		Portfolio p = new Portfolio("ken", "10/05/2020", "10/09/2020");
		p.addStock("IBM", 1, "10/07/2020", "10/08/2020");
		assertEquals(p.stocks.size(), 1);
	}
	
	@Test
	public void testDeleteStock() throws ParseException {
		Portfolio p2 = new Portfolio("ken", "10/05/2020", "10/09/2020");
		p2.addStock("IBM", 1, "10/07/2020", "10/08/2020");
		p2.deleteStock("IBM");
		assertEquals(p2.stocks.size(), 0);
	}
	
	@Test
	public void testDeleteStock2() throws ParseException {
		Portfolio p2 = new Portfolio("ken", "10/05/2020", "10/09/2020");
		p2.addStock("IBM", 1, "10/07/2020", "10/08/2020");
		p2.deleteStock("RACE");
		assertEquals(p2.stocks.size(), 1);
	}

	@Test
	public void testPopulatePortfolioValue1() throws ParseException, IOException {
		Portfolio p3 = new Portfolio("ken", "10/05/2020", "10/09/2020");
		p3.addStock("IBM", 1, "10/07/2020", "10/08/2020");
		p3.addStock("RACE", 1, "10/01/2020", "10/10/2020");
		p3.populatePortfolioValue();
		p3.printPortfolio();
		boolean check = false;
		int count = 0;
		for(int i=0; i<p3.tradingDate.length; i++) {
			if(p3.portfolioValue[i] > 300) count++;
		}
		if(count == 2) check = true;
		assertTrue(check);
	}
	@Test
	public void testPopulatePortfolioValue2() throws ParseException, IOException {
		Portfolio p4 = new Portfolio("ken", "10/05/2020", "10/09/2020");
		p4.addStock("IBM", 1, "10/09/2020", "10/16/2020");
		p4.addStock("RACE", 1, "10/01/2020", "10/06/2020");
		p4.populatePortfolioValue();
		p4.printPortfolio();
		boolean check = false;
		int count = 0;
		for(int i=0; i<p4.tradingDate.length; i++) {
			if(p4.portfolioValue[i] == 0) count++;
		}
		if(count == 2) check = true;
		assertTrue(check);
	}

	@Test
	public void testPopulatePortfolioValue3() throws ParseException, IOException {
		Portfolio p5 = new Portfolio("ken", "10/05/2020", "10/09/2020");
		p5.addStock("RACE", 1, "10/05/2020", "10/05/2020");
		p5.addStock("RACE", 1, "10/07/2020", "10/07/2020");
		p5.addStock("RACE", 1, "10/09/2020", "10/09/2020");
		p5.populatePortfolioValue();
		p5.printPortfolio();
		boolean check = false;
		int count = 0;
		for(int i=0; i<p5.tradingDate.length; i++) {
			if(p5.portfolioValue[i] == 0) count++;
		}
		if(count == 2) check = true;
		assertTrue(check);
	}
	@Test
	public void testPopulatePortfolioValue4() throws ParseException, IOException {
		Portfolio p6 = new Portfolio("ken", "10/05/2020", "10/05/2020");
		p6.addStock("RACE", 1, "10/05/2020", "10/05/2020");
		p6.populatePortfolioValue();
		//p6.printPortfolio();
		Double before = p6.portfolioValue[0];
		
		p6.addStock("RACE", 2, "10/05/2020", "10/05/2020");
		p6.populatePortfolioValue();
		Double after = p6.portfolioValue[0];
		//p6.printPortfolio();
		assertTrue((Double)before*3.0 == (Double)after);
	}
	
	@Test
	public void testPopulatePortfolioValue5() throws ParseException, IOException {
		Portfolio p6 = new Portfolio("ken", "10/04/2020", "10/09/2020");
		p6.populatePortfolioValue();
		//p6.printPortfolio();
		
		p6.addStock("IBM", 1, "9/01/2020", "9/30/2020");
		p6.populatePortfolioValue();
		//p6.printPortfolio();
		
		boolean check = false;
		int count = 0;
		for(int i=0; i<p6.tradingDate.length; i++) {
			if(p6.portfolioValue[i] == 0) count++;
		}
		if(count == 5) check = true;
		assertTrue(check);
	}
}


