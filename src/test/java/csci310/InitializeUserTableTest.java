package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

import io.cucumber.java.Before;

public class InitializeUserTableTest {
	@Before
	public void dropUserTable() {
		DropUserTable du = new DropUserTable();
	}
	public void initializeUserTable() {
		CreateUserTable cu = new CreateUserTable();
	}
	
	@Test
	public void testInitializeUserTable() {
		
		InitializeUserTable i = new InitializeUserTable();
		SQL s = new SQL();
		assertTrue(SQL.userExist("trojan"));
	}

}
