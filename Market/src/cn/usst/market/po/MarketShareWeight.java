package cn.usst.market.po;

public class MarketShareWeight {
	int id;
	String type;
	float adWeight;
	float marketWeight;
	float designWeight;
	float priceWeight;
	float salemanWeight;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getAdWeight() {
		return adWeight;
	}
	public void setAdWeight(float adWeight) {
		this.adWeight = adWeight;
	}
	public float getMarketWeight() {
		return marketWeight;
	}
	public void setMarketWeight(float marketWeight) {
		this.marketWeight = marketWeight;
	}
	public float getDesignWeight() {
		return designWeight;
	}
	public void setDesignWeight(float designWeight) {
		this.designWeight = designWeight;
	}
	public float getPriceWeight() {
		return priceWeight;
	}
	public void setPriceWeight(float priceWeight) {
		this.priceWeight = priceWeight;
	}
	public float getSalemanWeight() {
		return salemanWeight;
	}
	public void setSalemanWeight(float salemanWeight) {
		this.salemanWeight = salemanWeight;
	}
	
}
