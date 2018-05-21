package cn.usst.market.po;

public class HirePeople {
    private Integer id;

    private Integer companyId;

    private Integer marketId;

    private Integer quarter;

    private Integer saleman;

    private Integer afterSale;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getMarketId() {
		return marketId;
	}

	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Integer getSaleman() {
		return saleman;
	}

	public void setSaleman(Integer saleman) {
		this.saleman = saleman;
	}

	public Integer getAfterSale() {
		return afterSale;
	}

	public void setAfterSale(Integer afterSale) {
		this.afterSale = afterSale;
	}

    }