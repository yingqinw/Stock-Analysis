package csci310;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(DriverManager.class)

public class SQLTest {

	@Test
	public void testUserExist() throws SQLException {
		boolean user = SQL.userExist("");
		assertTrue(!user);
		SQL.register("trojan","Password123");
		boolean user1 = SQL.userExist("trojan");
		assertTrue(user1);
		
		//PowerMockito.mockStatic(DriverManager.class);
		//Mockito.when(DriverManager.getConnection("jdbc:sqlite:project.db")).thenThrow(new SQLException());
		//boolean user2 = SQL.userExist("trojan");
		//PowerMockito.verifyStatic();
        //DriverManager.getConnection("jdbc:sqlite:project.db");
        
        //PowerMockito.mockStatic(DriverManager.class);
        //Mockito.when(DriverManager.getConnection("jdbc:sqlite:project.db")).thenThrow(new SQLException());
        //boolean user2 = SQL.userExist("trojan");
        //PowerMockito.verifyStatic();
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
	}
	
	@Test
	public void testRegister() {
		SQL.register("yingqinw","Abc123");
		boolean user = SQL.userExist("yingqinw");
		assertTrue(user);
	}

}
