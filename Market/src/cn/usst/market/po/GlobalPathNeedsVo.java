package cn.usst.market.po;

public class GlobalPathNeedsVo {

	private Company c;
	
	private ProductMarketShare pms;
	
	private CompanyProduct cp;
	private int meedssum;
	private int salesum;
	private int demandssum;
	
	

	public int getDemandssum() {
		return demandssum;
	}

	public void setDemandssum(int demandssum) {
		this.demandssum = demandssum;
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

	private float rate;

	public CompanyProduct getCp() {
		return cp;
	}

	public void setCp(CompanyProduct cp) {
		this.cp = cp;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public Company getC() {
		return c;
	}

	public void setC(Company c) {
		this.c = c;
	}

	public ProductMarketShare getPms() {
		return pms;
	}

	public void setPms(ProductMarketShare pms) {
		this.pms = pms;
	}
}
