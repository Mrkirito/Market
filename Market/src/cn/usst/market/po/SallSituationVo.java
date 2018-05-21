package cn.usst.market.po;

public class SallSituationVo {
	private ProductMarketShare PMS;
	private ProductPrice PP;
	private int saleIncom;
	private int saleLost;
	private int meedssum;
	private int salesum;
	private int demandsum;
	
	public int getDemandsum() {
		return demandsum;
	}
	public void setDemandsum(int demandsum) {
		this.demandsum = demandsum;
	}
	public ProductMarketShare getPMS() {
		return PMS;
	}
	public void setPMS(ProductMarketShare pMS) {
		PMS = pMS;
	}
	public ProductPrice getPP() {
		return PP;
	}
	public void setPP(ProductPrice pP) {
		PP = pP;
	}
	public int getSaleIncom() {
		return saleIncom;
	}
	public void setSaleIncom(int saleIncom) {
		this.saleIncom = saleIncom;
	}
	public int getSaleLost() {
		return saleLost;
	}
	public void setSaleLost(int saleLost) {
		this.saleLost = saleLost;
	}
	public int getMeedssum() {
		return meedssum;
	}
	public void setMeedssum(int meedssum) {
		this.meedssum = meedssum;
	}
	public int getSalesum() {
		return salesum;
	}
	public void setSalesum(int salesum) {
		this.salesum = salesum;
	}
	
}
