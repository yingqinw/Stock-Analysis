package csci310.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		String website = "https://finnhub.io/api/v1/quote?symbol=" + ticker + "&token=" + APIKey;
		
		//connect to API
		URL url = new URL(website);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		int respondCode = con.getResponseCode(); //need to check the code via api docs
		if(respondCode != 200) throw new RuntimeException("HttpResponseCode:  "+respondCode);
		
		//read Gson
		Scanner sc = new Scanner(url.openStream());
		//StringBuffer result = new StringBuffer();
		String result = "";
		while(sc.hasNext()) result += sc.nextLine();
		sc.close();
		System.out.println(result);
		
		//parse json
		JSONObject obj = new JSONObject(result);
		double currPrice = obj.getDouble("c");
        System.out.println(currPrice);
        /*
        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
            System.out.println(post_id);
        }
        */
        
      //time ASSUMING THE TIME IN A DAY IS 5
        String startDate = "09/11/2020";
        String endDate = "09/21/2020";
        long startDateEpoch = 0;
        long endDateEpoch = 0;
        try {
        	//currTime = System.currentTimeMillis()/1000;
        	startDateEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").
        			parse(startDate+" 22:00:00").getTime() / 1000;
        	endDateEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").
        			parse(endDate+" 22:00:00").getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
        System.out.println(startDateEpoch);
        System.out.println(endDateEpoch);
        
        
        String website2 = "https://finnhub.io/api/v1/stock/candle?symbol="+ ticker +
        		"&resolution=D&from=" + startDateEpoch + 
        		"&to=" + endDateEpoch + "&token=" + APIKey;
        
      //connect to API
  		URL url2 = new URL(website2);
  		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
  		con2.setRequestMethod("GET");
  		con2.connect();
  		int respondCode2 = con2.getResponseCode(); //need to check the code via api docs
  		if(respondCode2 != 200) throw new RuntimeException("HttpResponseCode:  "+respondCode2);
  		
  		//read Gson
  		Scanner sc2 = new Scanner(url2.openStream());
  		//StringBuffer result = new StringBuffer();
  		String result2 = "";
  		while(sc2.hasNext()) result2 += sc2.nextLine();
  		sc2.close();
  		System.out.println(result2);
	}
}
