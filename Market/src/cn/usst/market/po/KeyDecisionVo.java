package cn.usst.market.po;

import java.util.List;

public class KeyDecisionVo {
	
	private Company company;
	
	private CompanyStrategy companyStrategy;
	
	private List<CompanyProduct> companyProductList;
	
	private int productNumber;
	private int minPrice;
	private int maxPrice;
	private int avgPrice;
	
	private List<CompanyAdvertise> companyAdvertiseList;
	
	private int mediaNumber;
	
	private SalesSalary salesSalary;
	private WorkersSalary workersSalary;


	public SalesSalary getSalesSalary() {
		return salesSalary;
	}


	public void setSalesSalary(SalesSalary salesSalary) {
		this.salesSalary = salesSalary;
	}


	public WorkersSalary getWorkersSalary() {
		return workersSalary;
	}


	public void setWorkersSalary(WorkersSalary workersSalary) {
		this.workersSalary = workersSalary;
	}


	public int getMediaNumber() {
		return mediaNumber;
	}


	public void setMediaNumber(int mediaNumber) {
		this.mediaNumber = mediaNumber;
	}


	public List<CompanyAdvertise> getCompanyAdvertiseList() {
		return companyAdvertiseList;
	}


	public void setCompanyAdvertiseList(List<CompanyAdvertise> companyAdvertiseList) {
		this.companyAdvertiseList = companyAdvertiseList;
	}


	public int getProductNumber() {
		return productNumber;
	}


	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}


	public int getMinPrice() {
		return minPrice;
	}


	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}


	public int getMaxPrice() {
		return maxPrice;
	}


	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}


	public int getAvgPrice() {
		return avgPrice;
	}


	public void setAvgPrice(int avgPrice) {
		this.avgPrice = avgPrice;
	}

	private List<MarketInfo2> phyMarketList;
	
	private CompanyMarket netMarket;
	
	private int memberCount;
	
	private int phySalerNum;
	private int netSalerNum;
	

	public int getPhySalerNum() {
		return phySalerNum;
	}


	public void setPhySalerNum(int phySalerNum) {
		this.phySalerNum = phySalerNum;
	}


	public int getNetSalerNum() {
		return netSalerNum;
	}


	public void setNetSalerNum(int netSalerNum) {
		this.netSalerNum = netSalerNum;
	}

	private CompanyCapacity companyCapacity;
	
	private float companyStockSum;
	
	private float fixedDeposit;
	//private int fixedCapacity;
	//private int addCapacity;


	public float getCompanyStockSum() {
		return companyStockSum;
	}


	public void setCompanyStockSum(float companyStockSum) {
		this.companyStockSum = companyStockSum;
	}


	public float getFixedDeposit() {
		return fixedDeposit;
	}


	public void setFixedDeposit(float fixedDeposit) {
		this.fixedDeposit = fixedDeposit;
	}


	public List<MarketInfo2> getPhyMarketList() {
		return phyMarketList;
	}


	public void setPhyMarketList(List<MarketInfo2> phyMarketList) {
		this.phyMarketList = phyMarketList;
	}




	public CompanyMarket getNetMarket() {
		return netMarket;
	}


	public void setNetMarket(CompanyMarket netMarket) {
		this.netMarket = netMarket;
	}


	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyStrategy getCompanyStrategy() {
		return companyStrategy;
	}

	public void setCompanyStrategy(CompanyStrategy companyStrategy) {
		this.companyStrategy = companyStrategy;
	}

	public List<CompanyProduct> getCompanyProductList() {
		return companyProductList;
	}

	public void setCompanyProductList(List<CompanyProduct> companyProductList) {
		this.companyProductList = companyProductList;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public CompanyCapacity getCompanyCapacity() {
		return companyCapacity;
	}

	public void setCompanyCapacity(CompanyCapacity companyCapacity) {
		this.companyCapacity = companyCapacity;
	}

}
