package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

public class SQLTest {

	@Test
	public void testUserExist() {
		boolean user = SQL.userExist("");
		assertTrue(user);
	}
	
	@Test
	public void testLogin() {
		boolean user = SQL.login("","");
		assertTrue(!user);
	}

}
