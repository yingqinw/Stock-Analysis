package csci310;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class PriceArray {
	public String ticker;
	public int quantity;
	public String startDate;
	public long startDateEpoch;
	public long endDateEpoch;
	public int syncIndex;
	public String[] tradingDate;
	public Double[] stockPrice;
	private String APIKey = "btjeu1f48v6tfmo5erv0";
	public boolean isEmpty;
	public PriceArray(String ticker, int quantity, String startDate, String endDate) throws ParseException {
		this.ticker = ticker;
		this.quantity = quantity;
		this.startDate = startDate;
		this.startDateEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(startDate+" 22:00:00").getTime() / 1000;
		this.endDateEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(endDate+" 22:00:00").getTime() / 1000;
		this.isEmpty = true;
		//this.endDateEpoch += 86400;
	}
	public void populateStockPrice() throws IOException, ParseException {
		String website = "https://finnhub.io/api/v1/stock/candle?symbol="+ ticker +
        		"&resolution=D&from=" + (long)(startDateEpoch-86400) + 
        		"&to=" + (long)(endDateEpoch) + "&token=" + APIKey;
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
  		while(sc.hasNext()) {
  			result += sc.nextLine();
  		}
  		sc.close();
  		//System.out.println(result);
  		
		//parse json 
		JSONObject obj = new JSONObject(result);
		String status = (String) obj.get("s"); 
		if(!status.equals("ok")) {
			System.out.println("bad API request input");
			return;
		}
		isEmpty = false;
		JSONArray c = obj.getJSONArray("c");
		JSONArray t = obj.getJSONArray("t");
		int length = c.length();
		stockPrice = new Double[length];
		tradingDate = new String[length];
		for(int i=0; i<length; i++) {
			stockPrice[i] = c.getDouble(i);
			long timeEpoch = t.getLong(i);
			tradingDate[i] = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (timeEpoch*1000));
		}
		predictFuturePrices();
	}
	public void printPriceArray() {
		System.out.println(">>>Printing PriceArray of " + ticker);
		if(isEmpty) {
			System.out.println("The PriceArray is Empty");
			return;
		}
		for(int i=0; i<tradingDate.length; i++) {
			System.out.println("Time: " + tradingDate[i] + " Price: " + stockPrice[i]); 
		}
	}
	public void predictFuturePrices() throws ParseException{
		int historialDayCount = tradingDate.length;
		String currentDate = tradingDate[historialDayCount-1];
		long currentDateEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(currentDate+" 22:00:00").getTime() / 1000;
		int futureDayCount = (int) ((endDateEpoch - currentDateEpoch)/86400);
		if(futureDayCount <= 0) return;
		
		String[] tradingDate2 = new String[historialDayCount + futureDayCount];
		Double[] stockPrice2 = new Double[historialDayCount + futureDayCount];
		//copy historial to new array
		for(int i=0; i<historialDayCount; i++) {
			tradingDate2[i] = tradingDate[i];
			stockPrice2[i] = stockPrice[i];
		} 
		//predict future prices with 5-MA
		for(int i=0; i<futureDayCount; i++) {
			Double currPrice = 0.0;
			int MovingAvgDays = 5;
			for(int j=0; j<MovingAvgDays; j++) {
				currPrice += stockPrice2[historialDayCount + i - MovingAvgDays + j];
			}
			long timeEpoch = currentDateEpoch + 86400*(i+1);
			tradingDate2[historialDayCount + i] = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (timeEpoch*1000));
			stockPrice2[historialDayCount + i] = currPrice/MovingAvgDays;
		}
		tradingDate = tradingDate2;
		stockPrice = stockPrice2;
	}
}

