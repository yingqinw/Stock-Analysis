package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
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
        String website = "https://finnhub.io/api/v1/stock/quote?symbol="+ ticker 
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
  		System.out.println(result);
  		if(result.contains("{}")) {
  			AddStockError ase = new AddStockError("Invalid ticker!");
  			PrintWriter out = response.getWriter();
  	        response.setContentType("application/json");
  	        response.setCharacterEncoding("UTF-8");
  	        out.print(this.gson.toJson(ase));
  	        out.flush(); 
  		}
  		else {
  			Stocks s = new Stocks(quantity, ticker, dayPurchase, daySold);
  			SQL.addStock(username, s);
  			JSONObject obj = new JSONObject(result);
  			double currentPrice = obj.getDouble("c");
  			System.out.println(currentPrice);
  			
  		}
		
		Stocks s = new Stocks(quantity, ticker, dayPurchase, daySold);
		SQL.addStock(username, s);
		

	}

}


