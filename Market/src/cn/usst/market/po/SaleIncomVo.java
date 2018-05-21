package cn.usst.market.po;

public class SaleIncomVo {
	/**
	 * mapper只初始化了：
	 * CP.cost
	 * PMS.companyId
	 * PMS.productId
	 * PMS.sale
	 * PP.youji
	 * PP.price
	 */
	private ProductPrice PP;//计算售价，邮寄返款。
	private ProductMarketShare PMS;//计算销量
	private CompanyProduct CP;//计算生产成本
	private int SaleIncomSum;
	private int YoujiSum;
	private int SaleCostSum;
	private int meedssum;
	private int salesum;
	
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
	public ProductPrice getPP() {
		return PP;
	}
	public void setPP(ProductPrice pP) {
		PP = pP;
	}
	public ProductMarketShare getPMS() {
		return PMS;
	}
	public void setPMS(ProductMarketShare pMS) {
		PMS = pMS;
	}
	public CompanyProduct getCP() {
		return CP;
	}
	public void setCP(CompanyProduct cP) {
		CP = cP;
	}
	public int getSaleIncomSum() {
		return SaleIncomSum;
	}
	public void setSaleIncomSum(int saleIncomSum) {
		SaleIncomSum = saleIncomSum;
	}
	public int getYoujiSum() {
		return YoujiSum;
	}
	public void setYoujiSum(int youjiSum) {
		YoujiSum = youjiSum;
	}
	public int getSaleCostSum() {
		return SaleCostSum;
	}
	public void setSaleCostSum(int saleCostSum) {
		SaleCostSum = saleCostSum;
	}
	
	
}
