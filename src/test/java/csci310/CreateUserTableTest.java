package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreateUserTableTest {

	@Test
	public void testCreateUserTable() {
		CreateUserTable c = new CreateUserTable();
		SQL.register("nillamstrong","Firstman");
		boolean user1 = SQL.userExist("nillamstrong");
		assertTrue(user1);
	}

}
