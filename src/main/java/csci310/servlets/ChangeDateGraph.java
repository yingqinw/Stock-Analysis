package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
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


@WebServlet("/ChangeDateGraph")
public class ChangeDateGraph extends HttpServlet{
	
	
	public class AddStockData{
		private JSONArray date = new JSONArray();
		private JSONObject prices = new JSONObject();
		public AddStockData(JSONArray labels, JSONObject price) {
			date = labels;
			prices = price;
		}
	}
	
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tickers_string = request.getParameter("tickers_graph");
		
		Type listType = new TypeToken<ArrayList<String>>(){}.getType();
		ArrayList<String> tickers = this.gson.fromJson(tickers_string, listType);

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
        JSONArray date = new JSONArray();
        JSONObject prices = new JSONObject();
        boolean setdate = false;
        
        for(String ticker : tickers) {
	        //connect to API
	        String website = "https://finnhub.io/api/v1/stock/candle?symbol="+ ticker +
	        		"&resolution=D&from=" + startDateEpoch + 
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
        AddStockData asd = new AddStockData(date,prices);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    out.print(this.gson.toJson(asd));
	    System.out.println(this.gson.toJson(asd).toString());
	    out.flush(); 
	}

}
