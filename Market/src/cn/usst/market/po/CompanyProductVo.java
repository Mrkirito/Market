package cn.usst.market.po;

public class CompanyProductVo extends CompanyProduct {

	private Integer demand;
	
	private Integer priceFlag;
	
	private Integer isSale;
	
	

	public Integer getPriceFlag() {
		return priceFlag;
	}

	public void setPriceFlag(Integer priceFlag) {
		this.priceFlag = priceFlag;
	}

	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	public Integer getDemand() {
		return demand;
	}

	public void setDemand(Integer demand) {
		this.demand = demand;
	}

	
	
}
