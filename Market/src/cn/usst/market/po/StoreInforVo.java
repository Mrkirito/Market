package cn.usst.market.po;

public class StoreInforVo {
	
	/**
	 *   	<result column="id" property="c.id"/>
  			<result column="mi" property="CM.market_id"/>
  			<result	column="isphy" property="CM.is_phy"/>
  			<result column="name" property="c.name"/>
	 * @param po
	 * @return
	 */
	
	private CompanyMarket CM;
	
	private Company c;
	
	private int[] storeList;
	
	private String isphy;
	
	private String[] mStr;
	
	public String getIsphy() {
		return isphy;
	}
	public void setIsphy(String isphy) {
		this.isphy = isphy;
	}
	public String[] getmStr() {
		return mStr;
	}
	public void setmStr(String[] mStr) {
		this.mStr = mStr;
	}
	public int[] getStoreList() {
		return storeList;
	}
	public void setStoreList(int[] storeList) {
		this.storeList = storeList;
	}
	public CompanyMarket getCM() {
		return CM;
	}
	public void setCM(CompanyMarket cM) {
		CM = cM;
	}
	public Company getC() {
		return c;
	}
	public void setC(Company c) {
		this.c = c;
	}

}
