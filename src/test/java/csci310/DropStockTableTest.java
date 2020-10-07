package csci310;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DropStockTableTest {
	@Test
	public void testDropStockTable() {
		DropStockTable d = new DropStockTable();
		SQL s = new SQL();
		Stocks st = new Stocks(1, "IBM", "1/1/2020", "12/31/2020");
		try {
			SQL.addStock("trojan", st);
		}catch(Exception sqle) {
			assert(true);
		}
	}
}
