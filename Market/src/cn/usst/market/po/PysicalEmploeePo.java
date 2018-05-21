package cn.usst.market.po;

public class PysicalEmploeePo {
	
	private int id;//自增id，无视之即可
	
	private int saller;//销售人员人数
	
	private int aftersale;//售后人员人数
	
	private int companyid;//公司id
	
	private int location;//销售点地址

	private int quater;//季度

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSaller() {
		return saller;
	}

	public void setSaller(int saller) {
		this.saller = saller;
	}

	public int getAftersale() {
		return aftersale;
	}

	public void setAftersale(int aftersale) {
		this.aftersale = aftersale;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getQuater() {
		return quater;
	}

	public void setQuater(int quater) {
		this.quater = quater;
	}

	@Override
	public String toString() {
		return "PysicalEmploeePo [id=" + id + ", saller=" + saller + ", aftersale=" + aftersale + ", companyid="
				+ companyid + ", location=" + location + ", quater=" + quater + "]";
	}

}
