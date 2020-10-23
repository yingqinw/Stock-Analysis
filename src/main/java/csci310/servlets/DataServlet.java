package csci310.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


@WebServlet("/data")
public class DataServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ticker = "AAPL";
		String APIKey = "btjeu1f48v6tfmo5erv0";
        String startDate = "06/11/2020";
        String endDate = "09/21/2020";
        
        PrintWriter out = response.getWriter();
        //response.setContentType("application/json");
        //response.setCharacterEncoding("UTF-8");
        //out.print(this.gson.toJson(success));
        //out.flush();  
        
		//time ASSUMING THE TIME IN A DAY IS 5
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
  		
		//parse json
		JSONObject obj = new JSONObject(result);
		JSONArray c = obj.getJSONArray("c");
		JSONArray t = obj.getJSONArray("t");
		int length = c.length();
		Double[] closingPrice = new Double[length];
		String[] time = new String[length];
		JSONArray price = new JSONArray();
		JSONArray date = new JSONArray();
		for(int i=0; i<length; i++) {
			closingPrice[i] = c.getDouble(i);
			price.put(c.getDouble(i));
			long timeEpoch = t.getLong(i);
			time[i] = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (timeEpoch*1000));
			date.put(time[i]);
			System.out.println("Closing Price: " + closingPrice[i] + " time: " + time[i]);
			out.print("Closing Price: " + closingPrice[i] + " time: " + time[i]);
		}
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(price);
        out.print(date);
        out.flush();
		
	}
}
