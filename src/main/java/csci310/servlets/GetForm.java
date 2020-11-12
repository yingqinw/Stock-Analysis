package csci310.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import csci310.Portfolio;
import csci310.SQL;
import csci310.Stocks;

@WebServlet("/GetForm")
@MultipartConfig
public class GetForm extends HttpServlet {
	
	public class AddStockError{
		private String AddStockerr = "";
		public AddStockError(String errmesg) {
			AddStockerr = errmesg;
		}
	}
	public class AddStockData{
		private JSONArray date = new JSONArray();
		private JSONArray price = new JSONArray();
		private JSONObject update = new JSONObject();
		private double currentPortfolioValue;
		private int prevPortfolioValue;
		public AddStockData(JSONArray labels, JSONArray prices, JSONObject updates, double value, int value2) {
			date = labels;
			price = prices;
			update = updates;
			currentPortfolioValue = value;
			prevPortfolioValue = value2;
		}
	}
	
	private Gson gson = new Gson();
 
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  request.setCharacterEncoding("UTF-8");
  //System.out.println("post here");
  boolean errorExists = false;
  response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        String username = "t";
        String APIKey = "buhnb0f48v6pkjomnafg";
        PrintWriter out = response.getWriter();
        String startDate = "s";
        String endDate = "e";
        
        
  try {
         List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
         System.out.println(items.size());
         for (FileItem item : items) {
        	 //System.out.println("One Item");
             if (item.isFormField()) {
                 // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                 String fieldName = item.getFieldName();
                 String fieldValue = item.getString();
                 System.out.println(fieldName + " is " + fieldValue);
                 
                 
                 if(fieldName.equals("username")) {
                	 username = fieldValue;
                 }else if(fieldName.equals("startDate")) {
                	 startDate = fieldValue;
                 }else if(fieldName.equals("endDate")) {
                	 endDate = fieldValue;
                 }
                 // ... (do your job here)
             } else {
                 // Process form file field (input type="file").
                 String fieldName = item.getFieldName();
                 //System.out.println(fieldName);
                 String fileName = FilenameUtils.getName(item.getName());
                 InputStream fileContent = item.getInputStream();
                 //System.out.println(fileName);
                 //System.out.println("adding stocks");
                 try (BufferedReader br = new BufferedReader(new InputStreamReader(
                         fileContent, StandardCharsets.UTF_8));) {

                     String line;
                     //get all lines
                     ArrayList<String> lines = new ArrayList<String>();
                     ArrayList<Integer> errorLines = new ArrayList<Integer>();
                     //int i = 0;
                     while ((line = br.readLine()) != null) {
                    	 System.out.println(line); 
                    	lines.add(line);
                     }
                     
                     for(int i=0;i<lines.size();i++) {
                    	String[] values = lines.get(i).split(",");
                    	if(values.length != 4) {
                    		errorLines.add(i);
                    		continue;
                    	}
                     	String ticker = values[0];
                  		int quantity = Integer.parseInt(values[1]);
                  		String dayPurchase = values[2];
                  		String daySold = values[3];
                  		if(ticker.length()<=0 || ticker.length()>=5) {
                  			errorLines.add(i);
                    		continue;
                  		}
                  		if(quantity<=0) {
                  			errorLines.add(i);
                    		continue;
                  		}
                  		SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy");
                  		
                  		
                        
						try {
							Date d1 = sdformat.parse(dayPurchase);
							Date d2 = sdformat.parse(daySold);
							if(d2.compareTo(d1) < 0) {
								errorLines.add(i);
								continue;
							}
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							errorLines.add(i);
							continue;
						}
                        
                  		
                  		
                     }
                     
                     if(errorLines.size() > 0) {
                    	String errorMessage = "There are errors on the following line(s):";
                    	for(int i=0;i<errorLines.size();i++) {
                    		errorMessage += " ";
                    		errorMessage += errorLines.get(i)+1;
                    	}
                    	
                    	
                    	AddStockError ase = new AddStockError(errorMessage);
               	        response.setContentType("application/json");
               	        response.setCharacterEncoding("UTF-8");
               	        out.print(this.gson.toJson(ase));
               	        out.flush();
               	        return;
                     }
                     
                     
                     for(int i=0;i<lines.size();i++) {
                        
                    	String[] values = lines.get(i).split(",");

                         
                    	String ticker = values[0];
                 		int quantity = Integer.parseInt(values[1]);
                 		String dayPurchase = values[2];
                 		String daySold = values[3];
                 		
                 		//add to portfolio
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
                  		if(result.contains(":0}")) {
                  			errorExists = true;
                  		}
                  		else {
                  			if(username.equals("fakeusername")) {
                  
                  			}
                  			else {
                  				Stocks s = new Stocks(quantity, ticker, dayPurchase, daySold);
                  				SQL.addStock(username, s);
                  			}
                  		}
                         
                     }//end of while loop
                     
                     
                     //updated price
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
           				//out.print("Exception thrown");
           				//System.out.println("sqle: "+sqle.getMessage());
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
           		  		JSONObject obj = new JSONObject(result1);
           	  			double currentPrice = obj.getDouble("c");
           	  			updatedPrices.put(ticker2,currentPrice);
           			}
           			// end of updated prices
           			
           			//output error when invalid ticker
           			/*
           			if(errorExists) {
           				AddStockError ase = new AddStockError("Some info in the file is wrong");
           	  	        response.setContentType("application/json");
           	  	        response.setCharacterEncoding("UTF-8");
           	  	        out.print(this.gson.toJson(ase));
           	  	        out.flush(); 
           			}*/
           			
           			//start of portfolio update
           			//System.out.println(username + startDate + endDate);
           			Portfolio p = new Portfolio(username, startDate, endDate);
           			//System.out.println("portfolio starting");
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
						double temp = p.getCurrPortfolioValue();
						AddStockData asd = new AddStockData(date,price,updatedPrices,temp,(temp==0)?0:(int)(temp*100/p.getPrevPortfolioValue())-100);
        		  		    response.setContentType("application/json");
        		  		    response.setCharacterEncoding("UTF-8");
        		  		    out.print(this.gson.toJson(asd));
        		  		    //System.out.println(this.gson.toJson(asd).toString());
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
           			//end of portfolio update
           			
           			
                     
                     
                     
                     
                 }

                 // ... (do your job here)
             }
         }
     } catch (FileUploadException e) {
         throw new ServletException("Cannot parse multipart request.", e);
     }
  
 
 
 }

}
