package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import csci310.servlets.AddStock.AddStockError;

@WebServlet("/AddStockGraph")
public class AddStockGraph extends HttpServlet {
	
	public class AddStockError{
		private String AddStockerr = "";
		public AddStockError(String errmesg) {
			AddStockerr = errmesg;
		}
	}
	
	
	public class AddStockData{
		private JSONArray date = new JSONArray();
		private JSONArray price = new JSONArray();
		private JSONArray SPV = new JSONArray();
		public AddStockData(JSONArray labels, JSONArray prices, JSONArray SPVP) {
			date = labels;
			price = prices;
			SPV = SPVP;
		}
	}
	
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ticker = request.getParameter("ticker_graph");
		String APIKey = "btjeu1f48v6tfmo5erv0";
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
       
        //connect to API
        String website = "https://finnhub.io/api/v1/stock/candle?symbol="+ ticker +
        		"&resolution=D&from=" + (long)(startDateEpoch-86400) + 
        		"&to=" + endDateEpoch + "&token=" + APIKey;
        URL url = new URL(website);
  		HttpURLConnection con = (HttpURLConnection) url.openConnection();
  		con.setRequestMethod("GET");
  		con.connect();
		
		//int respondCode = con.getResponseCode(); 
		//if(respondCode != 200) throw new RuntimeException("HttpResponseCode:  "+ respondCode);
		//System.out.println("respond code: " + respondCode);

  		//read json
  		Scanner sc = new Scanner(url.openStream());
  		String result = "";
  		while(sc.hasNext()) result += sc.nextLine();
  		sc.close();
  		
  		String website2 = "https://finnhub.io/api/v1/profile2?symbol="+ ticker 
        		+"&token=" + APIKey;
        URL url2 = new URL(website2);
  		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
  		con2.setRequestMethod("GET");
  		con2.connect(); 
		//read json
  		Scanner sc2 = new Scanner(url2.openStream());
  		String result2 = "";
  		while(sc2.hasNext()) result2 += sc2.nextLine();
  		sc2.close();
  		
  		if(result.contains("{\"s\":\"no_data\"}")) {
  			AddStockError ase = new AddStockError("Invalid ticker!");
  	        response.setContentType("application/json");
  	        response.setCharacterEncoding("UTF-8");
  	        out.print(this.gson.toJson(ase));
  	        out.flush(); 
  	        return;
  		}
  		
  		if(!result2.contains("NASDAQ")&&!result2.contains("NYSE")) {
  			AddStockError ase = new AddStockError("Ticker not listed in NASDAQ or NYSE!");
  	        response.setContentType("application/json");
  	        response.setCharacterEncoding("UTF-8");
  	        out.print(this.gson.toJson(ase));
  	        out.flush(); 
  	        return;
  		}
  		
		//parse json
		JSONObject obj = new JSONObject(result);
		JSONArray c = obj.getJSONArray("c");
		JSONArray t = obj.getJSONArray("t");
		int length = c.length();
		String[] time = new String[length];
		JSONArray price = new JSONArray();
		JSONArray date = new JSONArray();
		for(int i=0; i<length; i++) {
			price.put(c.getDouble(i));
			long timeEpoch = t.getLong(i);
			time[i] = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (timeEpoch*1000));
			date.put(time[i]);
			//pricesAndDate.put(time[i],c.getDouble(i));
		}
		
		String website1 = "https://finnhub.io/api/v1/stock/candle?symbol=SPY&resolution=D&from=" + (long)(startDateEpoch-86400) + 
        		"&to=" + endDateEpoch + "&token=" + APIKey;
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
  		JSONObject obj1 = new JSONObject(result1);
  		JSONArray c1 = obj.getJSONArray("c");
		int length1 = c1.length();
		JSONArray price1 = new JSONArray();
		for(int i=0; i<length1; i++) {
			price1.put(c1.getDouble(i));
		}
        
        AddStockData asd = new AddStockData(date,price,price1);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    out.print(this.gson.toJson(asd));
	    //System.out.print(this.gson.toJson(asd).toString());
	    out.flush(); 
	}

}
