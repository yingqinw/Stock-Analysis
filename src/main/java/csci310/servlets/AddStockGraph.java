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

@WebServlet("/AddStockGraph")
public class AddStockGraph extends HttpServlet {
	
	public class AddStockError{
		private String AddStockerr = "";
		public AddStockError(String errmesg) {
			AddStockerr = errmesg;
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
        		"&resolution=D&from=" + startDateEpoch + 
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
  		System.out.println(result);
  		if(result.contains(":0}")) {
  			AddStockError ase = new AddStockError("Invalid ticker!");
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
		}
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(price);
        out.print(date);
        out.flush();
	}

}
