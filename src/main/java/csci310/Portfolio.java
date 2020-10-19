

package csci310;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import csci310.PriceArray;

public class Portfolio {
	String username;
	Vector<PriceArray> stocks;
	String PFstartDate;
	String PFendDate;
	
	long PFstartDateEpoch;
	long PFendDateEpoch;
	public Double[] portfolioValue;
	public String[] tradingDate;
	private String APIKey = "btjeu1f48v6tfmo5erv0";
	boolean isEmpty;
	public Portfolio(String username, String startDate, String endDate) {
		this.username = username;
		this.PFstartDate = startDate;
		this.PFendDate = endDate;
		this.stocks = new Vector<PriceArray>();
		this.isEmpty = true;
	}
	public void addStock(String ticker, int quantity, String StockStartDate, String StockEndDate) throws ParseException {
		PriceArray PA = new PriceArray(ticker, quantity, StockStartDate, StockEndDate);
		stocks.add(PA);
	}
	public void deleteStock(String ticker) {
		boolean found = false;
		for(int i=0; i<stocks.size(); i++) {
			String temp = stocks.get(i).ticker;
			if(temp.equals(ticker)) {
				stocks.remove(i);
				found = true;
			}
		}
		if(found == false) System.out.println("Didn't find the to delete stock");
	}
	public void getPFTradingDate() throws IOException, ParseException {
		//Assuming every stock on NYSE or nasdaq has the same trading date as IBM
		PFstartDateEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(PFstartDate +" 22:00:00").getTime() / 1000;
		PFendDateEpoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(PFendDate +" 22:00:00").getTime() / 1000;
		
		String website = "https://finnhub.io/api/v1/stock/candle?symbol="+ "IBM" +
        		"&resolution=D&from=" + (long)(PFstartDateEpoch-86400) + 
        		"&to=" + PFendDateEpoch + "&token=" + APIKey;
        URL url = new URL(website);
  		HttpURLConnection con = (HttpURLConnection) url.openConnection();
  		con.setRequestMethod("GET");
  		con.connect();
		
		int respondCode = con.getResponseCode(); 
		if(respondCode != 200) throw new RuntimeException("HttpResponseCode:  "+ respondCode);
		//System.out.println("respond code: " + respondCode);

  		//read json
  		Scanner sc = new Scanner(url.openStream());
  		String result = "";
  		while(sc.hasNext()) {
  			result += sc.nextLine(); 
  		}
  		sc.close();
  		System.out.println(result);
  		System.out.println(PFstartDate);
  		System.out.println(PFendDate);
		//parse json 
		JSONObject obj = new JSONObject(result);
		String status = (String) obj.get("s"); 
		if(!status.equals("ok")) {
			System.out.println("bad API request input in portfolio value trading date");
			return;
		}
		isEmpty = false;
		JSONArray t = obj.getJSONArray("t");
		int length = t.length();
		tradingDate = new String[length];
		portfolioValue = new Double[length];
		for(int i=0; i<length; i++) {
			long timeEpoch = t.getLong(i);
			tradingDate[i] = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (timeEpoch*1000));
		}
	}
	public void printPortfolio() {
		System.out.println(">>>>Printing portfolio<<<<");
		if(isEmpty) {
			System.out.println("The portfolio is Empty due to input dates does not contain trading date.");
			return;
		}
		for(int z=0; z<tradingDate.length; z++) {
			System.out.println("Date: "+ tradingDate[z] + " dayPFvalue: " + portfolioValue[z]);
		}
	}
	public void populatePortfolioValue() throws IOException, ParseException {
		getPFTradingDate();
		//populate stock prices
		for(int i=0; i<stocks.size(); i++) {
			PriceArray temp = stocks.get(i);
			temp.populateStockPrice();
			//find the matching index
			temp.syncIndex = -10000000;
			for(int j=0; j<temp.tradingDate.length; j++) { //find PV start date in stock
				if(temp.tradingDate[j].equals(tradingDate[0])) temp.syncIndex = j; 
			}
			if(temp.syncIndex == -10000000){ //find stock start date in PV
				for(int z=0; z<tradingDate.length; z++) {
					if(tradingDate[z].equals(temp.tradingDate[0])) temp.syncIndex = -z;
				}
			}
			System.out.println(temp.ticker + "'s syncIndex: " + temp.syncIndex);
		}
		//loop thru everyday then every stock to calculate sum of stock prices
		if(tradingDate == null) return;
		int PFDayCount = tradingDate.length;
		for(int i= 0; i < PFDayCount; i++) { 
			Double dayPFvalue = 0.0;
			for(int j=0; j<stocks.size(); j++) {
				int currSyncIndex = stocks.get(j).syncIndex;
				//System.out.println("stock: " + stocks.get(j).ticker + " currSyncIndex: " + currSyncIndex + " stockprice length: " + stocks.get(j).stockPrice.length);
				if(currSyncIndex >= 0 && currSyncIndex < stocks.get(j).stockPrice.length) {
					dayPFvalue += stocks.get(j).stockPrice[currSyncIndex]*stocks.get(j).quantity;
					System.out.println("on date: " + tradingDate[i] + " day["+ i +"], adding day[" + (int)(currSyncIndex) + "]'s value of " + stocks.get(j).ticker + " to portfolio.");
				}
				stocks.get(j).syncIndex++; 
			}
			portfolioValue[i] = dayPFvalue;
		}
	}
}
