package cn.usst.market.po;

public class EveryProductMarketShareVo {
	String productName;
	String companyName;
	String type;
	double marketShare;
	int singaporeNeed;
	int hongkongNeed;
	int moscowNeed;
	int newdelhiNeed;
	int onlineNeed;
	public int getSingaporeNeed() {
		return singaporeNeed;
	}
	public void setSingaporeNeed(int singaporeNeed) {
		this.singaporeNeed = singaporeNeed;
	}
	public int getHongkongNeed() {
		return hongkongNeed;
	}
	public void setHongkongNeed(int hongkongNeed) {
		this.hongkongNeed = hongkongNeed;
	}
	public int getMoscowNeed() {
		return moscowNeed;
	}
	public void setMoscowNeed(int moscowNeed) {
		this.moscowNeed = moscowNeed;
	}
	public int getNewdelhiNeed() {
		return newdelhiNeed;
	}
	public void setNewdelhiNeed(int newdelhiNeed) {
		this.newdelhiNeed = newdelhiNeed;
	}
	public int getOnlineNeed() {
		return onlineNeed;
	}
	public void setOnlineNeed(int onlineNeed) {
		this.onlineNeed = onlineNeed;
	}
	int price;
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getMarketShare() {
		return marketShare;
	}
	public void setMarketShare(double marketShare) {
		this.marketShare = marketShare;
	}
	
	
	
}
