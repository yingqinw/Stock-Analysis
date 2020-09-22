package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreateUserTableTest {

	@Test
	public void testMain() {
		CreateUserTable c = new CreateUserTable();
		SQL.register("trojan","123");
		boolean user1 = SQL.userExist("trojan");
		assertTrue(user1);
		
	}

}
