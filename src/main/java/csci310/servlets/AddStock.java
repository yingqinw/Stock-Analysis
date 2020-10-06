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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import csci310.Stocks;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import csci310.SQL;

	
@WebServlet("/AddStock")
public class AddStock extends HttpServlet {
	
	public class AddStockError{
		private String AddStockerr = "";
		public AddStockError(String errmesg) {
			AddStockerr = errmesg;
		}
	}
	
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String ticker = request.getParameter("ticker"); //throw error if ticker not exist
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String dayPurchase = request.getParameter("startdate");
		String daySold = request.getParameter("enddate");
		String username = request.getParameter("username");
		String APIKey = "btjeu1f48v6tfmo5erv0";
		
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		
        //connect to API
        String website = "https://finnhub.io/api/v1/quote?symbol="+ ticker 
        		+"&token=" + APIKey;
        URL url = new URL(website);
  		HttpURLConnection con = (HttpURLConnection) url.openConnection();
  		con.setRequestMethod("GET");
  		con.connect(); 
		
  		PrintWriter out = response.getWriter();
		//read json
  		Scanner sc = new Scanner(url.openStream());
  		String result = "";
  		while(sc.hasNext()) result += sc.nextLine();
  		sc.close();
  		System.out.println(result);
  		if(result.contains(":0}")) {
  			AddStockError ase = new AddStockError("Invalid ticker!");
  	        response.setContentType("application/json");
  	        response.setCharacterEncoding("UTF-8");
  	        out.print(this.gson.toJson(ase));
  	        out.flush(); 
  		}
  		else {
  			if(username.equals("fakeusername")) {
  				
  			}else {
  				Stocks s = new Stocks(quantity, ticker, dayPurchase, daySold);
  				SQL.addStock(username, s);
  			}
  			
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
  						String ticker1 =rs2.getString("ticker");
  						tickers.add(ticker1);
  					}
  				}
  				
  			}catch(SQLException sqle) {
  				out.print("Exception thrown");
  				//System.out.println("sqle: "+sqle.getMessage());
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
  				String ticker2 = i.next();
  				//connect to API
  		        String website1 = "https://finnhub.io/api/v1/quote?symbol="+ ticker2 
  		        		+"&token=" + APIKey;
  		        URL url1 = new URL(website1);
  		  		HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
  		  		con1.setRequestMethod("GET");
  		  		con1.connect(); 
  				
  				//read json
  		  		Scanner sc1 = new Scanner(url1.openStream());
  		  		String result1 = "";
  		  		while(sc1.hasNext()) result1 += sc1.nextLine();
  		  		sc1.close();
  		  		//System.out.println(result);
  		  		JSONObject obj = new JSONObject(result1);
  	  			double currentPrice = obj.getDouble("c");
  	  			System.out.println(ticker2 +" "+ currentPrice);
  	  			out.print(ticker2 + " " + currentPrice);
  	  			updatedPrices.put(ticker2,currentPrice);
  			}
  			
  	        response.setContentType("application/json");
  	        response.setCharacterEncoding("UTF-8");
  	        out.print(updatedPrices);
  	        out.flush(); 
  		}
		

	}

}


