package cn.usst.market.po;

public class ProductMarketShare {
	private int id;
	private int count;

	private int competitionId;
	private int companyId;
	private int productId;
	private int quarter;
	private String productType;
	private double marketShare;//产品市场份额
	private int singaporeNeed;//产品需求量
	private int singaporeSale;
	private int hongkongNeed;
	private int hongkongSale;
	private int moscowNeed;
	private int moscowSale;
	private int newdelhiNeed;
	private int newdelhiSale;
	private int onlineNeed;
	private int onlineSale;
	
	private int stockoun;//产品脱销
	private int stock;//库存
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getCompetitionId() {
		return competitionId;
	}
	public void setCompetitionId(int competitionId) {
		this.competitionId = competitionId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public double getMarketShare() {
		return marketShare;
	}
	public void setMarketShare(double marketShare) {
		this.marketShare = marketShare;
	}

	public int getStockoun() {
		return stockoun;
	}
	public void setStockoun(int stockoun) {
		this.stockoun = stockoun;
	}
	public int getSingaporeNeed() {
		return singaporeNeed;
	}
	public void setSingaporeNeed(int singaporeNeed) {
		this.singaporeNeed = singaporeNeed;
	}
	public int getSingaporeSale() {
		return singaporeSale;
	}
	public void setSingaporeSale(int singaporeSale) {
		this.singaporeSale = singaporeSale;
	}
	public int getHongkongNeed() {
		return hongkongNeed;
	}
	public void setHongkongNeed(int hongkongNeed) {
		this.hongkongNeed = hongkongNeed;
	}
	public int getHongkongSale() {
		return hongkongSale;
	}
	public void setHongkongSale(int hongkongSale) {
		this.hongkongSale = hongkongSale;
	}
	public int getMoscowNeed() {
		return moscowNeed;
	}
	public void setMoscowNeed(int moscowNeed) {
		this.moscowNeed = moscowNeed;
	}
	public int getMoscowSale() {
		return moscowSale;
	}
	public void setMoscowSale(int moscowSale) {
		this.moscowSale = moscowSale;
	}
	public int getNewdelhiNeed() {
		return newdelhiNeed;
	}
	public void setNewdelhiNeed(int newdelhiNeed) {
		this.newdelhiNeed = newdelhiNeed;
	}
	public int getNewdelhiSale() {
		return newdelhiSale;
	}
	public void setNewdelhiSale(int newdelhiSale) {
		this.newdelhiSale = newdelhiSale;
	}
	public int getOnlineNeed() {
		return onlineNeed;
	}
	public void setOnlineNeed(int onlineNeed) {
		this.onlineNeed = onlineNeed;
	}
	public int getOnlineSale() {
		return onlineSale;
	}
	public void setOnlineSale(int onlineSale) {
		this.onlineSale = onlineSale;
	}

	
}
