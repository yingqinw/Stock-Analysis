package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.gson.Gson;

import csci310.Portfolio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/AddPortfolioGraph")
public class AddPortfolioGraph extends HttpServlet{
	
	public class AddStockData{
		private JSONArray date = new JSONArray();
		private JSONArray price = new JSONArray();
		public AddStockData(JSONArray labels, JSONArray prices) {
			date = labels;
			price = prices;
		}
	}
	
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
        String startDate = request.getParameter("startdate_graph");
        String endDate = request.getParameter("enddate_graph");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        
        PrintWriter out = response.getWriter();
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
			JSONArray date = new JSONArray();
			for(int i =0;i<p.tradingDate.length;i++) {
				price.put(p.portfolioValue[i]);
				date.put(p.tradingDate[i]);
			}
			AddStockData asd = new AddStockData(date,price);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    
		    out.print(this.gson.toJson(asd));
		    //System.out.print(this.gson.toJson(asd).toString());
		    out.flush(); 
			
		}catch(SQLException sqle) {
			System.out.println("sqle: "+sqle.getMessage());
		} catch (ParseException e) {
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
