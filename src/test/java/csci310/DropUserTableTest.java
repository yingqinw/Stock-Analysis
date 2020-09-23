package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

public class DropUserTableTest {

	@Test
	public void test() {
		SQL.register("nillamstrong","Firstman");
		boolean user1 = SQL.userExist("nillamstrong");
		assertTrue(user1);
		
		DropUserTable d = new DropUserTable();
		boolean user2 = SQL.userExist("nillamstrong");
		assertTrue(!user2);
	}

}
