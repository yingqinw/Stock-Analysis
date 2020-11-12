package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import csci310.Portfolio;


@WebServlet("/ChangeDateGraph")
public class ChangeDateGraph extends HttpServlet{
	
	
	public class AddStockData{
		private JSONArray date = new JSONArray();
		private JSONObject prices = new JSONObject();
		private double currentPortfolioValue;
		private int prevPortfolioValue;
		public AddStockData(JSONArray labels, JSONObject price, double value, int value2) {
			date = labels;
			prices = price;
			currentPortfolioValue = value;
			prevPortfolioValue = value2;
		}
	}
	
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tickers_string = request.getParameter("tickers_graph");
		
		Type listType = new TypeToken<ArrayList<String>>(){}.getType();
		ArrayList<String> tickers = this.gson.fromJson(tickers_string, listType);
		tickers.add("SPY");

		String APIKey = "btjeu1f48v6tfmo5erv0";
		String username = request.getParameter("username");
        String startDate = request.getParameter("startdate_graph");
        String endDate = request.getParameter("enddate_graph");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        
        PrintWriter out = response.getWriter();

        long startDateEpoch = 0;
        long endDateEpoch = 0;
        try {
        	//currTime = System.currentTimeMillis()/1000;
        	startDateEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(startDate+" 22:00:00").getTime() / 1000;
        	endDateEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(endDate+" 22:00:00").getTime() / 1000;
		} catch (ParseException e) {
			//e.printStackTrace();
		}
        JSONArray date = new JSONArray();
        JSONObject prices = new JSONObject();
        boolean setdate = false;
        
        for(String ticker : tickers) {
	        //connect to API
	        String website = "https://finnhub.io/api/v1/stock/candle?symbol="+ ticker +
	        		"&resolution=D&from=" + (long)(startDateEpoch-86400) + 
	        		"&to=" + endDateEpoch + "&token=" + APIKey;
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
			JSONArray c = obj.getJSONArray("c");
			JSONArray t = obj.getJSONArray("t");
			int length = c.length();
			JSONArray price = new JSONArray();
			if(!setdate) {
				for(int i=0; i<length; i++) {
					long timeEpoch = t.getLong(i);
					date.put(new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (timeEpoch*1000)));
				}
				setdate = true;
			}
			for(int i=0; i<length; i++) {
				price.put(c.getDouble(i));
			}
			prices.put(ticker, price);
        }
        
        Portfolio p = new Portfolio(username, startDate, endDate);
        Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs=null;
		ResultSet rs2=null;
		try {
			if(username.equals("anusernamethatisobviouslyfake")) {
				conn = DriverManager.getConnection("trigger exception");
			}
			conn = DriverManager.getConnection("jdbc:sqlite:project.db");
			ps = conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1,username);
			rs = ps.executeQuery();
			if(rs.next()) {
				int userID = rs.getInt("userID");
				ps2=conn.prepareStatement("SELECT * FROM stocks WHERE userID=?");
				ps2.setInt(1, userID);
				rs2=ps2.executeQuery();
				while(rs2.next()) {
					String ticker =rs2.getString("ticker");
					String dayPurchase = rs2.getString("dayPurchase");
					String daySold = rs2.getString("daySold");
					int quantity = rs2.getInt("quantity");
					p.addStock(ticker, quantity, dayPurchase, daySold);
				}
			}
			p.populatePortfolioValue();
			JSONArray price = new JSONArray();
			for(int i =0;i<p.tradingDate.length;i++) {
				price.put(p.portfolioValue[i]);
			}
			prices.put("portfolio", price);
			if(!setdate) {
				//System.out.println("setdate triggered");
				for(int i =0;i<p.tradingDate.length;i++) {
					date.put(p.tradingDate[i]);
				}
				setdate = true;
			}
			double temp = p.getCurrPortfolioValue();
			AddStockData asd = new AddStockData(date,prices,temp,(temp==0)?0:(int)(temp*100/p.getPrevPortfolioValue())-100);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    out.print(this.gson.toJson(asd));
		    //System.out.println(this.gson.toJson(asd).toString());
		    out.flush(); 
			 
			
		}catch(SQLException sqle) {
			//System.out.println("sqle: "+sqle.getMessage());
		} catch (ParseException e) {
		}
		try {
			if(rs!=null) {rs.close();}
			if(rs2!=null) {rs2.close();}
			if(ps!=null) {ps.close();}
			if(ps2!=null) {ps2.close();}
			if(conn!=null) {conn.close(); }
		}catch(SQLException sqle) {
			//System.out.println("sqle closing stuff: "+sqle.getMessage());
		}
	}

        
        
        
        
       
}
