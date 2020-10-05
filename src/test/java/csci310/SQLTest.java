package csci310;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	@Test
	public void testAddStock() {
		Stocks s = new Stocks(1,"apple","1999/01/01","2020/01/01");
		SQL.register("Bigmonster","Abc123");
		SQL.addStock("Bigmonster",s);
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs=null;
		ResultSet rs2=null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:project.db");
			ps = conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1, "Bigmonster");
			rs = ps.executeQuery();
			if(rs.next()) {
				int userID = rs.getInt("userID");
				ps2=conn.prepareStatement("SELECT * FROM stocks WHERE userID=?");
				ps2.setInt(1, userID);
				rs2=ps2.executeQuery();
				String ticker =rs2.getString("ticker");
				assertEquals(ticker,"apple");
			}
			
		}catch(SQLException sqle) {
			System.out.println("sqle: "+sqle.getMessage());
		}
		try {
			if(rs!=null) {rs.close();}
			if(rs2!=null) {rs2.close();}
			if(ps!=null) {ps.close();}
			if(ps2!=null) {ps2.close();}
			if(conn!=null) {conn.close(); }
		}catch(SQLException sqle) {
			System.out.println("sqle closing stuff: "+sqle.getMessage());
		}
		
	}

}
