package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

public class SQLTest {

	@Test
	public void testUserExist() {
		SQL s = new SQL();
		boolean user = SQL.userExist("");
		assertTrue(!user);
		SQL.register("trojan","Password123");
		boolean user1 = SQL.userExist("trojan");
		assertTrue(user1);
		
		//trigger exception branches
		boolean user2 = SQL.userExist("ausernamethatisobviouslyillegal");
		assertTrue(!user2);
	}
	
	@Test
	public void testLogin() {
		SQL.register("narmstrong11","Firstman123");
		boolean user = SQL.login("","");
		assertTrue(!user);
		boolean user1 = SQL.login("narmstrong11","Firstman123");
		assertTrue(user1);
		boolean user2 = SQL.login("narmstrong11","firstma");
		assertTrue(!user2);
		
		boolean user3 = SQL.login("ausernamethatisobviouslyillegal","something");
		assertTrue(!user3);
	}
	
	@Test
	public void testRegister() {
		SQL.register("yingqinw","Abc123");
		boolean user = SQL.userExist("yingqinw");
		assertTrue(user);
		
		SQL.register("ausernamethatisobviouslyillegal","something");
	}

}
