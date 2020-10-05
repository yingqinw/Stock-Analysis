package csci310;

public class Stocks {
	private int quantity;
	private String ticker;
	private String dayPurchase;
	private String daySold;
	
	public Stocks(int quantity, String ticker, String dayPurchase, String daySold) {
		this.quantity = quantity;
		this.ticker = ticker;
		this.daySold = daySold;
		this.dayPurchase = dayPurchase;
	}
	public int getQuantity() {
		return this.quantity;
		
	}
	public String getTicker() {
		return this.ticker;
		
	}
	public String getDayPurchase() {
		return this.dayPurchase;
		
	}
	public String getDaySold() {
		return this.daySold;
	}

}
