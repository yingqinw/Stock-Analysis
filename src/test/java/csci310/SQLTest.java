package csci310;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONObject;
import org.junit.Test;

import csci310.servlets.AddStock.AddStockError;

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
	@Test
	public void testAdd()throws IOException {
		Stocks s = new Stocks(1,"AAPL","1999/01/01","2020/01/01");
		Stocks s1 = new Stocks(1,"MSFT","1999/01/01","2020/01/01");
		Stocks s2 = new Stocks(1,"AAPL","1999/01/01","2020/01/01");
		Stocks s3 = new Stocks(1,"IBM","1999/01/01","2020/01/01");
		SQL.register("Bigmonste","Abc123");
		SQL.addStock("Bigmonste",s);
		SQL.addStock("Bigmonste",s1);
		SQL.addStock("Bigmonste",s2);
		SQL.addStock("Bigmonste",s3);
		String APIKey = "btjeu1f48v6tfmo5erv0";
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs=null;
		ResultSet rs2=null;
		HashSet<String> tickers =new HashSet<String>();
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:project.db");
			ps = conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1,"Bigmonste");
			rs = ps.executeQuery();
			if(rs.next()) {
				int userID = rs.getInt("userID");
				ps2=conn.prepareStatement("SELECT * FROM stocks WHERE userID=?");
				ps2.setInt(1, userID);
				rs2=ps2.executeQuery();
				while(rs2.next()) {
					String ticker =rs2.getString("ticker");
					tickers.add(ticker);
				}
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
		Iterator<String> i = tickers.iterator();
		while (i.hasNext()) {
			String ticker = i.next();
			//connect to API
	        String website = "https://finnhub.io/api/v1/quote?symbol="+ ticker 
	        		+"&token=" + APIKey;
	        URL url = new URL(website);
	  		HttpURLConnection con = (HttpURLConnection) url.openConnection();
	  		con.setRequestMethod("GET");
	  		con.connect(); 
			
			//read json
	  		Scanner sc = new Scanner(url.openStream());
	  		String result = "";
	  		while(sc.hasNext()) result += sc.nextLine();
	  		sc.close();
	  		//System.out.println(result);
	  		JSONObject obj = new JSONObject(result);
  			double currentPrice = obj.getDouble("c");
  			System.out.println(ticker +" "+ currentPrice);
		}
	}
	
	@Test
	public void testRemoveStock() {
		Stocks s = new Stocks(1,"AAPL","1999/01/01","2020/01/01");
		Stocks s1 = new Stocks(1,"MSFT","1999/01/01","2020/01/01");
		SQL.register("steven","Abc123");
		SQL.addStock("steven",s);
		SQL.addStock("steven",s1);
		SQL.removeStock("steven","AAPL");
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs=null;
		ResultSet rs2=null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:project.db");
			ps = conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1, "steven");
			rs = ps.executeQuery();
			if(rs.next()) {
				int userID = rs.getInt("userID");
				ps2=conn.prepareStatement("SELECT * FROM stocks WHERE userID=? AND ticker=?");
				ps2.setInt(1, userID);
				ps2.setString(2, "AAPL");
				rs2=ps2.executeQuery();
				assertTrue(!rs2.next());
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
