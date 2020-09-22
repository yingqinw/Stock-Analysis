package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

public class SQLTest {

	@Test
	public void testUserExist() {
		boolean user = SQL.userExist("");
		assertTrue(!user);
		SQL.register("trojan","123");
		boolean user1 = SQL.userExist("trojan");
		assertTrue(user1);
	}
	
	@Test
	public void testLogin() {
		SQL.register("narmstrong11","firstman");
		boolean user = SQL.login("","");
		assertTrue(!user);
		boolean user1 = SQL.login("narmstrong11","firstman");
		assertTrue(user1);
		boolean user2 = SQL.login("narmstrong11","firstma");
		assertTrue(!user2);
	}
	
	@Test
	public void testRegister() {
		SQL.register("yingqinw","123");
		boolean user = SQL.userExist("yingqinw");
		assertTrue(user);
	}

}
