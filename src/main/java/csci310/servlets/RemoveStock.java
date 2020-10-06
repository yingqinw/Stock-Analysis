package csci310.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveStock extends HttpServlet  {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ticker = request.getParameter("ticker");
		String username = request.getParameter("username");
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
