package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

public class InitializeUserTableTest {

	@Test
	public void testInitializeUserTable() {
		
		InitializeUserTable i = new InitializeUserTable();
		SQL s = new SQL();
		assertTrue(SQL.userExist("trojan"));
	}

}
