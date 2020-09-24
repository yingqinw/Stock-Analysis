package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

public class DropUserTableTest {

	@Test
	public void testDropUserTable() {
		DropUserTable d = new DropUserTable();
		SQL s = new SQL();
		assertTrue(!(SQL.userExist("trojan")));
	}

}
