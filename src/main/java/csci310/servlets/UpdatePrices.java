package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import csci310.Portfolio;

@WebServlet("/UpdatePrices")
public class UpdatePrices extends HttpServlet{
	
	public class AddStockData{
		private JSONArray date = new JSONArray();
		private JSONArray price = new JSONArray();
		private JSONObject update = new JSONObject();
		public AddStockData(JSONArray labels, JSONArray prices, JSONObject updates) {
			date = labels;
			price = prices;
			update = updates;
		}
	}
	
	private Gson gson = new Gson();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String startDate = request.getParameter("startdate_graph");
        String endDate = request.getParameter("enddate_graph");
		String APIKey = "btjeu1f48v6tfmo5erv0";
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs=null;
		ResultSet rs2=null;
		HashSet<String> tickers =new HashSet<String>();
		try {
			if(username.equals("fakeusername")) {
				conn = DriverManager.getConnection("exception trigger test");
			}
			conn = DriverManager.getConnection("jdbc:sqlite:project.db");
			ps = conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1, username);
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
			if(username.equals("fakeusername")) {
				conn = DriverManager.getConnection("exception trigger test");
			}
		}catch(SQLException sqle) {
			System.out.println("sqle closing stuff: "+sqle.getMessage());
		}
		Iterator<String> i = tickers.iterator();
		JSONObject updatedPrices = new JSONObject();
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
  			//System.out.println(ticker +" "+ currentPrice);
  			updatedPrices.put(ticker,currentPrice);
		}
		PrintWriter out = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        out.print(updatedPrices);
//        out.flush(); 
		Portfolio p = new Portfolio(username, startDate, endDate);
        if(!username.equals("fakeusername")) {
  		try {
  			ps = conn.prepareStatement("SELECT * FROM users WHERE username=?");
  			ps.setString(1,username);
  			rs = ps.executeQuery();
  			if(rs.next()) {
  				int userID = rs.getInt("userID");
  				ps2=conn.prepareStatement("SELECT * FROM stocks WHERE userID=?");
  				ps2.setInt(1, userID);
  				rs2=ps2.executeQuery();
  				while(rs2.next()) {
  					String ticker2 =rs2.getString("ticker");
  					String dayPurchase2 = rs2.getString("dayPurchase");
  					String daySold2 = rs2.getString("daySold");
  					int quantity2 = rs2.getInt("quantity");
  					p.addStock(ticker2, quantity2, dayPurchase2, daySold2);
  				}
  			}
  			p.populatePortfolioValue();
  			JSONArray price = new JSONArray();
  			JSONArray date = new JSONArray();
  			for(int j =0;j<p.tradingDate.length;j++) {
  				price.put(p.portfolioValue[j]);
  				date.put(p.tradingDate[j]);
  			}
  			AddStockData asd = new AddStockData(date,price,updatedPrices);
  		    response.setContentType("application/json");
  		    response.setCharacterEncoding("UTF-8");
  		    
  		    out.print(this.gson.toJson(asd));
  		    System.out.println(this.gson.toJson(asd).toString());
  		    out.flush(); 
  			
  		}catch(SQLException sqle) {
  			System.out.println("sqle: "+sqle.getMessage());
  		} catch (ParseException e) {}
        }
		try {
			if(rs!=null) {rs.close();}
			if(rs2!=null) {rs2.close();}
			if(ps!=null) {ps.close();}
			if(ps2!=null) {ps2.close();}
			if(conn!=null) {conn.close(); }
			if(username.equals("fakeusername")) {
				conn = DriverManager.getConnection("exception trigger test");
			}
		}catch(SQLException sqle) {
			System.out.println("sqle closing stuff: "+sqle.getMessage());
		}

	}

}
